public class GreenPacball implements Tile{
    final int x;
    final int y;
    public GreenPacball(int x, int y) {
        this.x = x;
        this.y = y;
    }
    @Override
    public boolean isSolidForPacman() {
        return false;
    }

    @Override
    public boolean isSolidForGhosts() {
        return false;
    }

    // TODO: increase by 1000 game points, change labyrinth structure
    @Override
    public void onPacmanInterract() {

    }

    @Override
    public int getX() {
        int temp_x = this.x;
        return temp_x;
    }

    @Override
    public int getY() {
        int temp_y = this.y;
        return temp_y;
    }
}
/*
|__ GreenPacball
        |__ final int x, final int y;
        |__ OrangePacball(int x, int y);
        |__ getX() → x
        |__ getY() → Y
        |__ isSolidForPacman() → false
        |__ isSolidForGhosts() → false
        |__ onPacmanInterract(): effet des pacballs vertes;
 */
