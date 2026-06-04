package objectville.mains;

import objectville.city.Cell;
import objectville.city.CityGrid;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Pls enter file name and tick number.");
            return;
        }
        String fileName = args[0];
        int totalTicks = Integer.parseInt(args[1]);
        try {
            MapReader mapReader = new MapReader(fileName);
            Cell[][] grid = mapReader.getGrid();
            CityGrid cityGrid = new CityGrid(grid);
            Simulation simulation = new Simulation(grid, cityGrid);
            for (int t = 0; t < totalTicks; t++) {
                simulation.nextTick();
            }

        } catch (IOException e) {
            System.out.println("ERROR: " + fileName + " A problem occurred while reading the file.");
            System.out.println("Details: " + e.getMessage());
        }
    }
}