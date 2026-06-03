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
    }
}