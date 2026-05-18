public class CityGrid {
   private Zone [][] grid;

    public CityGrid(Zone[][] grid) {
        this.grid = grid;
    }
    public void resetZone () {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                [i][j] grid = new Zone ();
            }
        }

    }
}
