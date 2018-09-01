package Pokedex;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        //Declare and initialize scanner, user input variable
        Scanner scan = new Scanner(System.in);
        char userInput;

        //Print ascii art greeting
        printPokeAscii();

        //Print menu
        printGreeting();

        //Create prompt and accept user input
        do {
            //Print possible commands and prompt for input
            System.out.println("\nEnter (T)est, (G)reeting, (F)ile, or (Q)uit");
            System.out.print("~>");

            //Accept user input
            userInput = scan.next().charAt(0);

            //Menu system
            switch (Character.toLowerCase(userInput)) {

                case 't':                                 //test
                    test();
                    break;

                case 'g':                                 //greeting
                    printGreeting();
                    break;

                case 'f':                                 //file input
                    //call fileInput, store return value in monsterCount
                    int monsterCount = fileInput_monsterCounter();

                    //print number of monsters sorted to console
                    System.out.println("\n" + monsterCount + " monsters sorted!\n" +
                            "Output files generated: grass_monsters.csv, water_monsters.csv");
                    break;
            }
        } while (userInput != 'q');                        //quit

        //Close scanner - not deprecated!
        scan.close();

        System.out.println("Goodbye!");
    }

    //Test method, creates four monsters and prints them to console
    private static void test() {

        // Create pokemon objects for testing
        Pokemon bulbaSaur = new Pokemon(1, "Bulbasaur", "Grass", 45);
        Pokemon ivySaur = new Pokemon(2, "Ivysaur", "Grass", 60);
        Pokemon venuSaur = new Pokemon(3, "Venosaur", "Grass", 80);
        Pokemon charMander = new Pokemon(4, "Charmander", "Fire", 39);

        //Create and build out arrayList of pokemon
        ArrayList<Pokemon> pokeList = new ArrayList<>();

        pokeList.add(bulbaSaur);
        pokeList.add(ivySaur);
        pokeList.add(venuSaur);
        pokeList.add(charMander);

        //Test pokemon objects, print to console
        System.out.println("\nTesting pokemon objects.....");
        System.out.printf("\n%-4s %-20s %-20s %-20s\n", "#", "Name", "Type", "Hit Points");
        System.out.println("===============================================================");

        //Print monster name and info, using toString
        for (Pokemon monster : pokeList) {
            System.out.print(monster);
        }
    }

    //Prints ascii art
    private static void printPokeAscii() {

        System.out.print("                                  ,'\\\n" +
                "    _.----.        ____         ,'  _\\   ___    ___     ____\n" +
                "_,-'       `.     |    |  /`.   \\,-'    |   \\  /   |   |    \\  |`.\n" +
                "\\      __    \\    '-.  | /   `.  ___    |    \\/    |   '-.   \\ |  |\n" +
                " \\.    \\ \\   |  __  |  |/    ,','_  `.  |          | __  |    \\|  |\n" +
                "   \\    \\/   /,' _`.|      ,' / / / /   |          ,' _`.|     |  |\n" +
                "    \\     ,-'/  /   \\    ,'   | \\/ / ,`.|         /  /   \\  |     |\n" +
                "     \\    \\ |   \\_/  |   `-.  \\    `'  /|  |    ||   \\_/  | |\\    |\n" +
                "      \\    \\ \\      /       `-.`.___,-' |  |\\  /| \\      /  | |   |\n" +
                "       \\    \\ `.__,'|  |`-._    `|      |__| \\/ |  `.__,'|  | |   |\n" +
                "        \\_.-'       |__|    `-._ |              '-.|     '-.| |   |\n" +
                "                                `'                            '-._|\n");

    }

    //Prints greeting
    private static void printGreeting() {

        System.out.print("\n\n***********************************************************************\n" +
                "*                    WELCOME TO POKEDEX!!!                            *\n" +
                "*                                                                     *\n" +
                "*     Use the (F)ile option to automatically separate a pokemon CSV   *\n" +
                "*     file into two files, one comprised of Grass type pokemon, and   *\n" +
                "*     one of Water type pokemon.  The output files will be created    *\n" +
                "*     in the present working directory.                               *\n" +
                "*                                                                     *\n" +
                "***********************************************************************\n");
    }

    //Handles file input and counts number of monsters sorted
    private static int fileInput_monsterCounter() {
        //monster counting variable
        int monsterCount = 0;

        //Scanner to accept user input for filename
        Scanner scan = new Scanner(System.in);


        //Prompt user to input file name
        System.out.println("\nPlease enter the name of the file, including the extension (\".csv\", etc.), or 'Q' to  quit. ");
        System.out.print("~>");

        File inputFile = new File(scan.nextLine());

        //Attempt to read file
        try (Scanner fileReader = new Scanner(inputFile)) {
            while (fileReader.hasNextLine()) {
                monsterSorter(fileReader.nextLine());       //call monsterSorter
                monsterCount++;                             //increment monsterCounter
            }
        } catch (IOException e) {
            System.out.println("\nFile not found!");        //whoops
        }
        return monsterCount;
    }

    private static void monsterSorter(String monsterInfo) {

        //Split monsterInfo string in array, using commas as delimiter
        String[] monsterInfoArray = monsterInfo.split(",");

        try {
            //Create Printwriters for files
            PrintWriter grassMonsterFile = new PrintWriter(new FileOutputStream("grass_monsters.csv", true));
            PrintWriter waterMonsterFile = new PrintWriter(new FileOutputStream("water_monsters.csv", true));

            //If monster is grass element
            if (monsterInfoArray[2].equalsIgnoreCase("grass")) {
                grassMonsterFile.println(monsterInfo);
                grassMonsterFile.flush();
                grassMonsterFile.close();

                //If monster is water element
            } else if (monsterInfoArray[2].equalsIgnoreCase("water")) {
                waterMonsterFile.println(monsterInfo);
                waterMonsterFile.flush();
                waterMonsterFile.close();
            }
        } catch (IOException e) {
            System.out.println("Error writing to file!");       //whoops
        }
    }
}




