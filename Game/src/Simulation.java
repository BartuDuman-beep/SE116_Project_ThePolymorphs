public class Simulation {
    private Cell[][] grid;
    private int currentTick = 1;
    private CityGrid citygrid;
    //to call methods that are in citygrid, we should define it as an object here!

    public Simulation(Cell[][] grid, CityGrid citygrid) {
        this.grid = grid;
        this.citygrid = citygrid;
    }

    public void nextTick() {

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {

                if (grid[i][j] instanceof Zone zone) {

                    zone.resetReceivedValues();
                    //reseting the values that come from the previous tour
                }
            }
        }
    }
}
