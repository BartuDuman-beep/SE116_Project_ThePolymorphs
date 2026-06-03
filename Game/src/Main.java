import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Pls enter file name and tick number.");
            return;
        }
        List<String> lines = new ArrayList<>();
        String fileName = args[0];
        int totalTicks = Integer.parseInt(args[1]);
        try {
            MapReader mapReader = new MapReader(fileName);

            int rows = mapReader.getRows();
            int cols = mapReader.getCols();
            MapReader city = new MapReader(fileName);

            Cell[][] grid = mapReader.getGrid();

            for (int y = 0; y < rows; y++) {
                for (int x = 0; x < cols; x++) {
                    //a
                }
            }
        } catch (IOException e) {
            System.out.println("ERROR: " + fileName + " A problem occurred while reading the file.");
            System.out.println("Details: " + e.getMessage());
        }
    }
}