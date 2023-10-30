public class BluePacball implements Tile{
    final int x;
    final int y;
    public BluePacball(int x, int y) {
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

    // TODO: increase by 100 game points
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
|__ BluePacball
        |__ final int x, final int y;
        |__ BluePacball(int x, int y);
        |__ getX() → x
        |__ getY() → Y
        |__ isSolidForPacman() → false
        |__ isSolidForGhosts() → false
        |__ onPacmanInterract(): on ajoute les points;
 */
