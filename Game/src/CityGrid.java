public class CityGrid {
    private Cell[][] grid;

    public CityGrid(Cell[][] grid) {
        this.grid = grid;
    }

    public void resetZone() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = new EmptyCell(i, j);
            }
        }
    }

    public void distributeServices() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {

                if (grid[i][j] instanceof Zone sourceZone) { //Checks whether the current cell is related to Zone or not

                    char symbol = sourceZone.getSymbol();
                    int radius = 0;

                    if (symbol == 'F') radius = 5; // Police station (security)

                    if (symbol == 'D') radius = 3; // Hospital (health)

                    if (symbol == 'S') radius = 4; // School (education)

                    if (symbol == 'F' || symbol == 'D' || symbol == 'S') {
                        for (int x = 0; x < grid.length; x++) {
                            for (int y = 0; y < grid[x].length; y++) {
                                if (grid[x][y] instanceof Zone targetZone) {

                                    int distance = Math.abs (i-x) + Math.abs (j-y); // Manhattan distance

                                    if (distance < radius) {
                                        if (symbol == 'F') targetZone.security = true;
                                        if (symbol == 'D') targetZone.health = true;
                                        if (symbol == 'S') targetZone.education = true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
