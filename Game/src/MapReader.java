import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class MapReader {
    private int rows;
    private int cols;
    private Cell[][] grid;

    public MapReader(String filename) throws IOException {
        readFile(filename);
    }

    private void readFile(String filename) throws IOException {
        File file = new File(filename);
        Scanner sc = new Scanner(file);
        ArrayList<String> lines = new ArrayList<>();

        while (sc.hasNextLine()) {
            String line = sc.nextLine();

            if (!line.trim().isEmpty()) {
                lines.add(line);
            }
        }
        sc.close();

        rows = lines.size();
        if (rows == 0) {
            throw new IOException("Map file is empty!");
        }

        cols = lines.get(0).length();

        grid = new Cell[rows][cols];

        for (int row = 0; row < rows; row++) {

            String line = lines.get(row);

            if (line.length() != cols) {
                throw new IOException(
                        "Line " + (row + 1) + "has a different length."
                );
            }

            for (int col = 0; col < cols; col++) {

                char symbol = line.charAt(col);
                grid[row][col] =
                        createCell(col, row, symbol);
            }
            }
        }
    private Cell createCell(int x, int y, char symbol) {

        switch (symbol) {

            case 'H':
                return new Housing(x, y);

            case 'I':
                return new Industrial(x, y);

            case 'C':
                return new Commercial(x, y);

            case 'P':
                return new PowerPlant(x, y);

            case 'W':
                return new WaterPumpingStation(x, y);

            case 'T':
                return new InternetHub(x, y);

            case 'F':
                return new PoliceStation(x, y);

            case 'D':
                return new Hospital(x, y);

            case 'S':
                return new School(x, y);

            case 'R':
                return new Road(x, y);

            case 'E':
                return new EmptyCell(x, y);

            default:
                throw new IllegalArgumentException(
                        "Invalid character in map: " + symbol
                );
        }
        }
    public Cell[][] getGrid() { return grid; }
    public int getRows() { return rows; }
    public int getCols() { return cols; }

    public void printGrid() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(grid[i][j].getSymbol());
            }
            System.out.println();
        }
    }
}
