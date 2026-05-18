package com.objectville.game;

public class Main {
    public static void main(String[] args) {
        System.out.println("ObjectVille II - SE116 Project");
        if (args.length < 2){
            System.out.println("Usage: java -jar ObjectVilleGame.jar <mapfile.txt> <ticks>");
            System.out.println("Example: java -jar ObjectVilleGame.jar map.txt 10");
            return;
        }

        String mapFileName = args[0];
        int ticks;

        try {
            ticks = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            throw new SE116ConfigurationException("Invalid tick count: " + args[1]);
        }

        System.out.println("Map file: " + mapFileName);
        System.out.println("Number of ticks: " + ticks);
    }
}
