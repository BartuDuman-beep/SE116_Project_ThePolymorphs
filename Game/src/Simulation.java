public class Simulation {
    private Cell[][] grid;
    private int totalTicks;
    private CityGrid citygrid;
    //to call methods that are in citygrid, we should define it as an object here!

    public Simulation(Cell[][] grid, CityGrid citygrid) {
        this.grid = grid;
        this.citygrid = citygrid;
    }


    public void runTick() {
        for (int i = 0; i < totalTicks; i++) {
            System.out.println("Tick" + (i + 1));


            citygrid.distributeServices();

            citygrid.distributeUtilities();

            citygrid.accumulateProduction();

            //these functions are inside 2 for loops in the citygrid class so we do not need extra 2 for loops

            for (int x = 0; x < grid.length; x++) {
                for (int y = 0; y < grid[0].length; y++) {

                    if (grid[x][y] instanceof Zone zone) {

                        zone.updateLevel();

                        zone.resetReceivedValues();
                        //reseting the values that come from the previous tour
                    }
                }
            }
        }
    }
}

