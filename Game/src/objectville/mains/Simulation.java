package objectville.mains;

import objectville.city.Cell;
import objectville.city.CityGrid;
import objectville.utilities.UtilityBuilding;
import objectville.zones.Zone;

public class Simulation {
    private Cell[][] grid;
    private CityGrid citygrid;

    private int TotalPopulation;
    private int TotalGoods;
    private int TotalLifestyle;
    private int HouseCount;
    private int CommercialCount;
    private int IndustrialCount;

    public Simulation(Cell[][] grid, CityGrid citygrid) {
        this.grid = grid;
        this.citygrid = citygrid;
    }

    public void runTick() {

        for(int i = 0; i < grid.length; i++ ){
            for(int j = 0; j < grid[i].length; j++ ){
                if (grid[i][j] instanceof UtilityBuilding utility) {
                        utility.reset();
                    }
                }
            }
            citygrid.distributeServices();

            citygrid.distributeUtilities();

            citygrid.accumulateProduction();

            citygrid.distributeResources(TotalPopulation, TotalGoods, TotalLifestyle, HouseCount, IndustrialCount, CommercialCount);

            for (int x = 0; x < grid.length; x++) {
                for (int y = 0; y < grid[0].length; y++) {

                    if (grid[x][y] instanceof Zone zone) {

                        zone.updateLevel();

                        zone.calculateOutput();

                        zone.resetReceivedValues();
                        //reseting the values that come from the previous tour
                    }
                }
            }
        }
    }


