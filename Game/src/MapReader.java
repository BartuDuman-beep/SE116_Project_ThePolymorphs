import java.io.*;
import java.util.*;

public class MapReader {
    private int rows;
    private int cols;
    private Cell[][] grid;

    public MapReader(String filename) throws IOException{
        readFile(filename);
    }

    private void readFile(String filename) throws  IOException{
        File file = new File(filename);
        Scanner sc = new Scanner(file);
        ArrayList<String> lines = new ArrayList<>();

        while (sc.hasNextLine()){
            String  line= sc.nextLine();

            if (!line.trim().isEmpty()){
                lines.add(line);
            }
        } sc.close();

    }
}
