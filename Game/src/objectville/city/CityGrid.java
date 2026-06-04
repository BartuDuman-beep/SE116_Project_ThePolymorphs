package objectville.city;

import objectville.utilities.UtilityBuilding;
import objectville.zones.Commercial;
import objectville.zones.Housing;
import objectville.zones.Industrial;
import objectville.zones.Zone;

import java.util.LinkedList;
import java.util.Queue;

public class CityGrid {
    public static final char POWER_PLANT = 'P';
    public static final char WATER_PUMPING_STATION = 'W';
    public static final char INTERNET_HUB = 'T';

    public Cell[][] grid;

    public CityGrid(Cell[][] grid) {
        this.grid = grid;
    }

    public Cell[][] getGrid() {
        return grid;
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

                if (grid[i][j] instanceof Zone sourceZone) { //Checks whether the current cell is empty or not

                    char symbol = sourceZone.getSymbol();
                    int radius = 0;

                    if (symbol == 'F') radius = 5; // Police station (security)
                    if (symbol == 'D') radius = 3; // services.objectville.services.Hospital (health)
                    if (symbol == 'S') radius = 4; // services.objectville.services.School (education)

                    if (radius > 0) {
                        for (int x = 0; x < grid.length; x++) { //x coordinate for targetZone
                            for (int y = 0; y < grid[x].length; y++) { //y coordinate for targetZone

                                if (grid[x][y] instanceof Zone targetZone) {

                                    int distance = Math.abs(i - x) + Math.abs(j - y); // Manhattan distance
                                    String zoneName = sourceZone.getClass().getSimpleName();
                                    if(zoneName.equals("Housing")) zoneName = "House";
                                    if (distance <= radius) {
                                        switch (symbol) {
                                            case 'F':
                                                targetZone.setSecurity(true);
                                                System.out.println(zoneName+" at ("+x+","+y+") received security service");
                                                break;
                                            case 'D':
                                                targetZone.setHealth(true);
                                                System.out.println(zoneName+" at ("+x+","+y+") received health service");
                                                break;
                                            case 'S':
                                                targetZone.setEducation(true);
                                                System.out.println(zoneName+" at ("+x+","+y+") received education service");
                                                break;
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

    public void distributeUtilities() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {

                if (grid[i][j] instanceof UtilityBuilding station) { // this block checks if the grid is equal to a station object

                    char symbol = station.getSymbol();

                    if (symbol == POWER_PLANT) {
                        runBFS(i, j, "electricity", station);
                    } else if (symbol == WATER_PUMPING_STATION) {
                        runBFS(i, j, "water", station);
                    } else if (symbol == INTERNET_HUB) {
                        runBFS(i, j, "internet", station);
                    }
                }
            }
        }
    }

    public void addNeighbor(int nextX, int nextY, Queue<Cell> queue, boolean[][] visited) {
        if (nextX >= 0 && nextX < grid.length && nextY >= 0 && nextY < grid[0].length) {

            if (!visited[nextX][nextY] && grid[nextX][nextY] instanceof Zone) {

                queue.add(grid[nextX][nextY]);
                visited[nextX][nextY] = true;
            }
        }
    }

    public void runBFS(int startX, int startY, String utilityType, UtilityBuilding provider) {

        boolean[][] visited = new boolean[grid.length][grid[0].length];
        Queue <Cell> queue = new LinkedList<>();

        queue.add(grid[startX][startY]); //each utility is added
        visited[startX][startY] = true; //i and j are now invalid so we use new variables in their place

        while (!queue.isEmpty()) {

            if (provider.getRemainingCapacity() <= 0) {
                break; //exit the loop immediately
            }
            Cell currentCell = queue.poll();
            //currentCell is defined here because it must become an old cell that received utility after being a currentCell, this means that we should remove it from the queue after having utility

            if (currentCell instanceof Zone targetZone) {
                int demand = Math.max(1, targetZone.getUtilityDemand());

                if (demand > 0 && provider.getRemainingCapacity() > 0) {
                    int assignedUtility = Math.min(demand, provider.getRemainingCapacity());

                    String zoneName = targetZone.getClass().getSimpleName();
                    if(zoneName.equals("Housing")) zoneName = "House";

                    if (utilityType.equals("electricity")) {
                        targetZone.receiveElectricity(assignedUtility);
                        System.out.println(zoneName+" at ("+targetZone.getX()+","+targetZone.getY()+") received "+assignedUtility+" electricity");
                    } else if (utilityType.equals("water")) {
                        targetZone.receiveWater(assignedUtility);
                        System.out.println(zoneName+" at ("+targetZone.getX()+","+targetZone.getY()+") received "+assignedUtility+" water");
                    } else if (utilityType.equals("internet")) {
                        targetZone.receiveInternet(assignedUtility);
                        System.out.println(zoneName+" at ("+targetZone.getX()+","+targetZone.getY()+") received "+assignedUtility+" internet");

                        provider.consume(assignedUtility);
                    }
                }
                addNeighbor(currentCell.getX() - 1, currentCell.getY(), queue, visited);
                addNeighbor(currentCell.getX() + 1, currentCell.getY(), queue, visited);
                addNeighbor(currentCell.getX(), currentCell.getY() - 1, queue, visited);
                addNeighbor(currentCell.getX(), currentCell.getY() + 1, queue, visited);
            }
        }
    }
        public void accumulateProduction () {
            int totalPopulation = 0;
            int totalGoods = 0;
            int totalLifestyle = 0;

            int houseCount = 0;
            int industrialCount = 0;
            int commercialCount = 0;

            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[i].length; j++) {
                    Cell cell = grid[i][j];

                    if (cell instanceof Housing house) {
                        totalPopulation += house.getOutput();
                        houseCount++;
                    } else if (cell instanceof Industrial industrial) {
                        totalGoods += industrial.getOutput();
                        industrialCount++;
                    } else if (cell instanceof Commercial commercial) {
                        totalLifestyle += commercial.getOutput();
                        commercialCount++;
                    }
                }
                distributeResources(totalPopulation, totalGoods, totalLifestyle, houseCount, industrialCount, commercialCount);
            }
        }
        public void distributeResources ( int totalPopulation, int totalGoods, int totalLifestyle, int houseCount,
        int industrialCount, int commercialCount){

            int distributePopulation = (industrialCount + commercialCount > 0) ? (totalPopulation / (industrialCount + commercialCount)) : 0;
            // in case of not having any house zone, result will be zero, to prevent denominator being zero
            int distributeGoods = (commercialCount > 0) ? (totalGoods / commercialCount) : 0;
            int distributeLifestyle = (houseCount > 0) ? (totalLifestyle / houseCount) : 0;

            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[i].length; j++) {
                    Cell cell = grid[i][j]; //this cell object provides to hold the current matrix cell

                    if (cell instanceof Industrial industrial) {
                        industrial.receivePopulation(distributePopulation);
                        // set function tells industrial building to update its current data with the given reference which is distributePopulation here
                    }
                    if (cell instanceof Commercial commercial) {
                        commercial.receivePopulation(distributePopulation);
                        commercial.receiveGoods(distributeGoods);
                    }
                    if (cell instanceof Housing house) {
                        house.receiveLifestyle(distributeLifestyle);
                    }
                }
            }
    }
}


