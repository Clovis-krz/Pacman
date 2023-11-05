package com.pacman;

import com.pacman.entities.Entity;
import com.pacman.entities.Ghost;
import com.pacman.entities.Pacman;
import com.pacman.tiles.*;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;


public class Main {
    public static final int GRID_WIDTH = 20;
    public static final int GRID_HEIGHT = 20;
    public static final int ELEMENT_SIZE = 20;

    public static final int LIFE_COST = 5000;

    private static int lives = 3;
    private static long score = 0;
    private static long nextLifeGoal = LIFE_COST;

    private static Pacman pacman;
    private static ArrayList<Ghost> ghosts = new ArrayList<>();

    private static Tile[] board;
    private static int pacballsCounter;

    private static final JFrame frame = new JFrame();

    public static void main(String[] args) throws InterruptedException {
        // Initialisation phase
        initBoard();
        initEntities();

        // Window initialisation
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                frame.setSize(400, 400);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.getContentPane().setBackground(Color.BLACK);

                frame.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyPressed(KeyEvent e) {
                        if (pacman == null) return;

                        if (e.getKeyCode() == 38 || e.getKeyChar() == 'z') pacman.setDirection(Entity.Direction.UP);
                        else if (e.getKeyCode() == 39 || e.getKeyChar() == 'd') pacman.setDirection(Entity.Direction.RIGHT);
                        else if (e.getKeyCode() == 40 || e.getKeyChar() == 's') pacman.setDirection(Entity.Direction.DOWN);
                        else if (e.getKeyCode() == 37 || e.getKeyChar() == 'q') pacman.setDirection(Entity.Direction.LEFT);
                    }
                });

                frame.setVisible(true);
                frame.setFocusable(true);
                frame.requestFocus();

            }
        });

        // Render loop
        Timer renderTime = new Timer(1, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                draw();
            }
        });
        renderTime.start();

        // Game loop
        while(pacballsCounter > 0 && lives > 0) {
            // Entity movement
            pacman.move();
            for (Ghost ghost : ghosts) ghost.move();

            // Entity collisions
            handleEntityCollisions();

            // Update only 20 times per second
           Thread.sleep(50);
        }

        // End of the game, show whether the player won or lost
        if (lives > 0) System.out.println("You Won");
        else System.out.println("Game Over");
    }

    public static void addRenderer(JComponent renderer) {
        frame.add(renderer);
    }

    public static void removeRenderer(JComponent renderer) {
        frame.remove(renderer);
    }

    public static Tile getTileAtCoords(int x, int y) {
        int index = y * GRID_WIDTH + x;

        if (x < GRID_WIDTH && x >= 0 && y < GRID_HEIGHT && y >= 0) return board[y*GRID_WIDTH + x];
        else return null;
    }

    public static int getBoardLength() {
        return GRID_WIDTH * GRID_HEIGHT;
    }

    // TODO: Implement wrapping fully
    public static int[] getWrapAroundCoordinates(int x, int y) {
        return null;
    }

    public static int getRemainingLives() {
        return lives;
    }

    public static void consumeLife() {
        lives = Math.max(0, lives - 1);
    }

    public static long getScore() {
        return score;
    }

    public static void addPoints(int value) {
        score += value;

        if (score > nextLifeGoal) {
            lives += 1;
            nextLifeGoal += LIFE_COST;
        }
    }

    public static void setPowerupState(Pacman.State state) {
        switch (state) {
            case SUPER:
                // Pacman state
                pacman.setState(Pacman.State.SUPER);

                // Ghosts state
                for (Ghost ghost : ghosts) ghost.setState(Ghost.State.VULNERABLE);
                break;

            case INVISIBLE:
                // Pacman state
                pacman.setState(Pacman.State.INVISIBLE);
                break;

            case NORMAL:
                // Pacman state
                pacman.setState(Pacman.State.NORMAL);

                // Ghosts state
                for (Ghost ghost : ghosts) ghost.setState(Ghost.State.NORMAL);
                break;
        }
    }

    public static void consumePacball(int x, int y) {
        int index = y * GRID_WIDTH + x;

        Tile tile = getTileAtCoords(x, y);
        if (tile == null) return;

        tile.delete();

        board[index] = new Air(x, y);

        pacballsCounter -= 1;
    }

    //Check if there is a collision between a pacman and a ghost
    private static void handleEntityCollisions() {
        int pacmanX = Math.floorDiv(pacman.getX(), ELEMENT_SIZE);
        int pacmanY = Math.floorDiv(pacman.getY(), ELEMENT_SIZE);

        for (Ghost ghost: ghosts) {
            int ghostX = Math.floorDiv(ghost.getX(), ELEMENT_SIZE);
            int ghostY = Math.floorDiv(ghost.getY(), ELEMENT_SIZE);

            if (ghostX == pacmanX && ghostY == pacmanY) {
                switch (pacman.getState()) {
                    case SUPER:
                        ghost.teleport(GRID_WIDTH / 2, GRID_HEIGHT / 2);
                        break;

                    case NORMAL:
                        consumeLife();
                        break;
                }
            }
        }
    }


    // TODO: Replace with a function to generate the labyrinth procedurally
    //Init board
    private static void initBoard() {
        //W -> Wall
        //A -> Air
        //X -> Pacman Wall
        //B -> Blue Pacball
        //G -> Green Pacball
        //O -> Orange Pacball
        //P -> Purple Pacball
        String[] string_board = {
                "W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W",
                "W","A","A","B","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W",
                "W","B","W","B","W","W","W","A","A","P","A","A","A","A","B","W","W","W","W","W",
                "W","W","W","B","W","W","W","A","W","W","W","W","W","W","A","W","W","W","W","W",
                "A","A","A","B","W","W","W","A","W","W","W","W","W","W","A","A","A","A","A","A",
                "W","W","W","B","W","W","W","A","W","W","W","W","W","W","W","A","W","W","W","W",
                "W","W","W","B","W","W","W","A","W","W","W","W","W","W","W","A","W","W","W","W",
                "W","W","W","B","W","W","W","A","W","W","W","W","W","W","W","A","W","W","W","W",
                "W","W","W","B","A","A","A","A","X","B","A","A","A","A","A","A","W","W","W","W",
                "W","W","W","B","W","W","W","W","W","A","W","A","W","W","W","A","W","W","W","W",
                "W","W","W","B","W","W","W","W","W","W","W","A","W","W","W","A","W","W","W","W",
                "W","W","W","B","W","W","W","W","W","W","W","A","W","W","W","A","W","W","W","W",
                "W","W","A","B","W","W","W","W","W","W","W","A","W","W","W","A","W","W","W","W",
                "W","W","B","W","W","W","W","A","W","W","W","A","W","W","W","B","W","W","W","W",
                "W","W","A","W","W","W","W","A","W","W","W","A","W","W","W","A","W","W","W","W",
                "W","W","A","W","W","W","W","A","W","W","W","A","W","W","W","A","W","W","W","W",
                "W","W","G","W","A","A","A","A","A","O","A","A","W","W","W","A","B","A","A","W",
                "W","W","A","A","B","W","W","W","W","W","W","W","W","W","W","W","W","W","A","W",
                "W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","A","W",
                "W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W",
                };

        board = new Tile[400];
        pacballsCounter = 0;

        for (int i = 0; i < string_board.length; i++) {
            int x = i % GRID_WIDTH;
            int y = i / GRID_HEIGHT;

            switch (string_board[i]) {
                case "W":
                    board[i] = new Wall(x, y);
                    break;
                case "A":
                    board[i] = new Air(x, y);
                    break;
                case "X":
                    board[i] = new PacmanWall(x, y);
                    break;
                case "B":
                    board[i] = new BluePacball(x,y);
                    pacballsCounter++;
                    break;
                case "G":
                    board[i] = new GreenPacball(x,y);
                    pacballsCounter++;
                    break;
                case "O":
                    board[i] = new OrangePacball(x,y);
                    pacballsCounter++;
                    break;
                case "P":
                    board[i] = new PurplePacball(x,y);
                    pacballsCounter++;
                    break;
                default:
                    System.out.println("Error, unknown expression at coordinates x: " + x + ", y: " + y);
            }

        }

    }

    // TODO: Possibly, hardcode the entity positions to make the code base easier
    private static void initEntities() {

        // Pacman
        int[] pac_coord = FindAir();
        //pacman = new Pacman(pac_coord[0],pac_coord[1]);
        pacman = new Pacman(60, 40);
        consumePacball(3, 1);

        /*
        // Ghosts
        ghosts = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            int[] ghost_coord = FindAir();
            ghosts.add(new Ghost(ghost_coord[0],ghost_coord[1]));
        }*/
    }

    //Find Random Air
    public static int[] FindAir() {
        int index = -1;
        while(index < 0) {
            Random rand = new Random();
            int temp = rand.nextInt(400);
            if (board[temp] instanceof Air) {
                index = temp;
            }
        }
        return new int[]{index / GRID_WIDTH, index % GRID_HEIGHT};
    }

    private static void draw() {
        frame.repaint();
    }
}