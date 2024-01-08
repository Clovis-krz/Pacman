package com.pacman;

import com.pacman.entities.Entity;
import com.pacman.entities.Ghost;
import com.pacman.entities.Pacman;
import com.pacman.powerups.NormalState;
import com.pacman.powerups.State;
import com.pacman.renderers.GuiRenderer;
import com.pacman.tiles.*;

import java.util.ArrayList;


public class Game {
    // 20x20 grid composed of 20x20 objects.
    public static final int GRID_WIDTH = 20;
    public static final int GRID_HEIGHT = 20;
    public static final int ELEMENT_SIZE = 20;

    // How much score is needed to gain an additional life
    public static final int LIFE_COST = 5000;

    // Game variables
    private static int lives; // Amount of lives that currently pacman has
    private static long nextLifeGoal; // The next score value required to gain a new life.
    private static long score; // Current plauer score

    private static int remainingPacballs; // Amount of pacballs remaining before the player wins
    private static int remainingSpecialPacballs; // Amount of superpacballs remaining before the player wins

    private static boolean shouldRestart; // Set to true to make the game restart, used for thread safety to stay on the main thread.

    // Powerup state
    private static State powerupState;
    private static int powerupTimer; // Amount of time remaining for pacman's powerup

    // Entities
    private static Pacman pacman; // The pacman entity
    private static ArrayList<Ghost> ghosts; // List holding all the Ghost entities in one.

    // Game board, holding a flattened grid of static Tile objects.
    private static Tile[] board;

    // Game GUI object
    private static GuiRenderer guiRenderer;

    /**
     Initialises a new game
    */
    public static void init() throws InterruptedException {
        // Resets the window renderers
        Main.resetRenderers();

        // Resets game values
        lives = 3;
        nextLifeGoal = LIFE_COST;
        score = 0;

        shouldRestart = false;

        powerupState = new NormalState();
        powerupTimer = 0;

        remainingPacballs = 0;
        remainingSpecialPacballs = 0;

        // Initialises pacman
        pacman = new Pacman(180, 100);

        // Initialises the ghost
        ghosts = new ArrayList<>();
        ghosts.add(new Ghost(180, 180));
        ghosts.add(new Ghost(200, 180));
        ghosts.add(new Ghost(200, 200));
        ghosts.add(new Ghost(180, 200));

        // Initialises the maze
        board = new Tile[400];
        regenerateMaze();

        // Eats the pacball at pacman's position
        consumePacball(9, 5);

        // Creates the GUI
        guiRenderer = new GuiRenderer();

        // Starts the main game loop
        Game.loop();
    }

    /**
     Used to trigger the game logic loop, fired 20 times per second.
    */
    private static void loop() throws InterruptedException {
        // While there's still some pacballs remaining and the player isn't dead yet and the game wasn't set to restart.
        while ((remainingPacballs > 0 || remainingSpecialPacballs > 0) && lives > 0 && !shouldRestart) {
            // Entity movement
            pacman.move();
            for (Ghost ghost : ghosts) ghost.move();

            // Entity collisions
            handleEntityCollisions();

            // Powerup timer
            if (powerupTimer > 0) powerupTimer -= 1;
            else {
                powerupTimer = 0;
                setPowerupState(new NormalState());
            }

            // Update only 20 times per second
            Thread.sleep(50);
        }

        // End of the game, show whether the player won or lost, unless the game restarted.
        if (!shouldRestart) {
            if (lives > 0) guiRenderer.setType(GuiRenderer.Type.WIN);
            else guiRenderer.setType(GuiRenderer.Type.LOSE);
        }

        // Loop used to allow the player to press Enter to restart
        while (!shouldRestart) {Thread.sleep(1);}

        // Resets the game
        init();
    }

    /**
     Resets the game, used to ensure thread-safety with Java Swing's rendering thread.
    */
    public static void prepareToReset() {
        shouldRestart = true;
    }

    /**
     Generates a new maze layout and applies it to the game board.
     Assigns the remainingPacballs value to the amount of pacballs in the maze.
    */
    public static void regenerateMaze() {
        for (Tile tile : board) {
            if (tile != null) tile.delete();
        }

        resetEntityPositions();
        int[] pacballCounts;
        if (remainingPacballs == 0 && remainingSpecialPacballs == 0) pacballCounts = MazeGenerator.generateBoard(board);
        else pacballCounts = MazeGenerator.generateBoard(board, remainingPacballs, remainingSpecialPacballs);

        remainingPacballs = pacballCounts[0];
        remainingSpecialPacballs = pacballCounts[1];
    }

    /**
     Resets entity positions so the ghost and pacman return to the center.
    */
    public static void resetEntityPositions() {
        pacman.setDirection(Entity.Direction.RIGHT);
        pacman.teleport(180, 100);

        int[] xCoordinates = new int[]{180, 200, 200, 180};
        int[] yCoordinates = new int[]{180, 180, 200, 200};

        for (int i = 0; i < 4; i += 1) {
            ghosts.get(i).teleport(xCoordinates[i], yCoordinates[i]);
        }
    }

    /**
     Changes the plauer's direction to the provided one

     @param direction The direction to apply to pacman
    */
    public static void setPlayerDirection(Entity.Direction direction) {
        pacman.setDirection(direction);
    }

    /**
     @return The current powerup state object
    */
    public static State getPowerupState() {
        return powerupState;
    }


    /**
     Returns the Tile object located at the given grid coordinates.

     @param x The X Position of the tile
     @param y The Y Position of the tile
     @return The Tile object located at those coordinates if it exists, or null otherwise.
    */
    public static Tile getTileAtCoords(int x, int y) {
        int index = y * GRID_WIDTH + x;

        if (x < GRID_WIDTH && x >= 0 && y < GRID_HEIGHT && y >= 0) return board[y*GRID_WIDTH + x];
        else return null;
    }

    /**
     @return The amount of remaining lives for Pacman, used mainly for GUI rendering.
    */
    public static int getRemainingLives() {
        return lives;
    }

    /**
     Makes Pacman die and lose a life.
     Resets the entity positions too.
    */
    public static void consumeLife() {
        lives = Math.max(0, lives - 1);
        resetEntityPositions();
    }

    /**
     @return The current player score, used mainly for GUI rendering
    */
    public static long getScore() {
        return score;
    }

    /**
     Adds a given amount of points to the game score.
     Also gives the player an additional life if he reaches the goal.

     @param value The amount of points to add, can be negative to substract game score if needed.
    */
    public static void addPoints(int value) {
        score += value;

        if (score > nextLifeGoal) {
            lives += 1;
            nextLifeGoal += LIFE_COST;
        }
    }

    /**
     Returns the corresponding wrapped-around coordinates, given the entrance coordinates.
     If the player entered on the 2nd entrance on the left, he should return on the 2nd entrance on the right.
     In case there was only one exit, the player should take that exit instead.

     @param x The X Position in the grid of the entrance
     @param y The Y Position in the grid of the entrance
     @param pacman Whether this function was called for Pacman or for a Ghost

     @return The corresponding exit coordinates for the wrap around in a 2 items array, if any, null otherwise.
    */
    public static int[] getWrapAroundCoordinates(int x, int y, boolean pacman) {
        // Horizontal wrapping
        if (x == 0 || x == GRID_WIDTH - 1) {
            // Finds the ordinal of the entrance from the top to the bottom.
            int entranceNumber = 0;
            for (int i = 0; i <= y; i += 1) {
                Tile tile = getTileAtCoords(x, i);

                if (tile == null || (!tile.isSolidForPacman() && pacman) || (!tile.isSolidForGhosts() && !pacman)) entranceNumber += 1;
            }

            // Count of the total possible exits
            int count = 0;

            // The last encountered exit.
            Tile lastExit = null;

            // The column (X position) to check on.
            int targetX = 0;
            if (x == 0) targetX = GRID_WIDTH - 1;

            // Finds the closest matching exit possible.
            for (int i = 0; i < GRID_HEIGHT; i += 1) {
                Tile tile = getTileAtCoords(targetX, i);

                // Ignore possible invalid tiles
                if (tile == null) continue;

                // If it is an exit, select it as the closer one.
                if ((!tile.isSolidForPacman() && pacman) || (!tile.isSolidForGhosts() && !pacman)) {
                    count += 1;
                    lastExit = tile;
                }

                // We've reached the corresponding exit already, no need to go further.
                if (count == entranceNumber) break;
            }

            // Returns the coordinates of the exit.
            if (count > 0) return new int[]{lastExit.getX(), lastExit.getY()};
        }

        // Vertical wrapping
        if (y == 0 || y == GRID_HEIGHT - 1) {
            // Finds the ordinal of the entrance from left to right.
            int entranceNumber = 0;
            for (int i = 0; i <= x; i += 1) {
                Tile tile = getTileAtCoords(i, y);

                if (tile == null || (!tile.isSolidForPacman() && pacman) || (!tile.isSolidForGhosts() && !pacman)) entranceNumber += 1;
            }

            // Count of the total possible exits
            int count = 0;

            // The last encountered exit.
            Tile lastExit = null;

            // The row (Y position) to check on.
            int targetY = 0;
            if (y == 0) targetY = GRID_HEIGHT - 1;

            // Finds the closest matching exit possible.
            for (int i = 0; i < GRID_WIDTH; i += 1) {
                Tile tile = getTileAtCoords(i, targetY);

                // Ignores possible invalid tiles
                if (tile == null) continue;

                // If it is an exit, select it as the closer one.
                if ((!tile.isSolidForPacman() && pacman) || (!tile.isSolidForGhosts() && !pacman)) {
                    count += 1;
                    lastExit = tile;
                }

                // We've reached the corresponding exit already, no need to go further.
                if (count == entranceNumber) break;
            }

            // Returns the coordinates of the exit.
            if (count > 0) return new int[]{lastExit.getX(), lastExit.getY()};
        }

        // No matching exits were found, meaning that the opposite side of where the entrance is had no exits.
        return null;
    }

    /**
     Changes the currently active powerup state, and sets the powerup timer appropriately.

     @param state The State to switch to.
    */
    public static void setPowerupState(State state) {
        powerupState = state;
        powerupTimer = state.getDuration();
    }

    /**
     Consumes a pacball at the given tile coordinates and replaces it with air.
     Assumed to always be a valid pacball, due to previous checks.

     @param x X Position of the Tile on the grid
     @param y Y Position of the Tile on the grid
    */
    public static void consumePacball(int x, int y) {
        int index = y * GRID_WIDTH + x;

        Tile tile = getTileAtCoords(x, y);
        if (tile == null || (tile.getType() != Tile.Type.PACBALL && tile.getType() != Tile.Type.SUPERPACBALL)) return;

        tile.delete();

        board[index] = new Air(x, y);

        if (tile.getType() == Tile.Type.PACBALL) remainingPacballs -= 1;
        else if (tile.getType() == Tile.Type.SUPERPACBALL) remainingSpecialPacballs -= 1;
    }

    /**
     Handles entity collisions between pacman and ghosts.
    */
    private static void handleEntityCollisions() {
        int pacmanX1 = pacman.getX();
        int pacmanX2 = pacmanX1 + ELEMENT_SIZE;
        int pacmanY1 = pacman.getY();
        int pacmanY2 = pacmanY1 + ELEMENT_SIZE;

        for (Ghost ghost: ghosts) {
            int ghostX1 = ghost.getX();
            int ghostX2 = ghostX1 + ELEMENT_SIZE;
            int ghostY1 = ghost.getY();
            int ghostY2 = ghostY1 + ELEMENT_SIZE;

            if (pacmanX1 < ghostX2 && pacmanX2 > ghostX1 && pacmanY1 < ghostY2 && pacmanY2 > ghostY1) {
                switch (powerupState.getInteractionType()) {
                    case GHOST_DEATH:
                        ghost.teleport(180, 180);
                        break;

                    case PACMAN_DEATH:
                        consumeLife();
                        break;
                }
            }
        }
    }

}