package Pokedex;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        //Declare and initialize scanner, user input variable, create pokemonCounter
        Scanner scan = new Scanner(System.in);
        char userInput;
        boolean fileReadOK = false;

        //Create pokedex
        Pokedex pokedex = new Pokedex();

        //Print ascii art greeting
        printPokeAscii();

        //Print menu
        printGreeting();

        //Create prompt and accept user input
        do {
            //Print possible commands and prompt for input
            System.out.println("\nEnter (T)est, (G)reeting, (F)ile input, (S)ort Pokedex into .csv files, (P)rint Pokemon objects, or (Q)uit");
            System.out.print("~>");

            //Accept user input
            userInput = scan.next().charAt(0);

            //Menu system
            switch (Character.toLowerCase(userInput)) {

                case 't':                                 //test
                    test(pokedex);
                    break;

                case 'g':                                 //greeting
                    printGreeting();
                    break;

                case 'f':                                 //file input
                    //Prompt user to input file name
                    System.out.println("\nPlease enter the name of the file, including the extension (\".csv\", etc.), or 'Q' to  quit.");
                    System.out.print("~>");

                    File inputFileName = new File(scan.next());

                    try {
                        fileInputHelper(inputFileName, pokedex);
                        System.out.println(pokedex.getNumPokemon() + " Pokemon built from " + inputFileName + ".");
                        fileReadOK = true;

                    } catch (FileNotFoundException e) {
                        System.out.println("Please enter a valid filename with .csv extension.");
                    }
                    break;

                case 's':                                  //sort pokedex to files

                    if (fileReadOK) {
                        pokedex.sortToFiles();
                        System.out.println("Pokedex sorted successfully! Water and grass Pokemon written to .csv " +
                                "files in present working directory.");
                    } else {
                        System.out.println("Please input a valid file to scan, using the (F)ile command.");
                    }
                    break;

                case 'p':                                   //print all Pokemon to console
                    printInfoHeader();
                    // System.out.println(pokedex);
                    for (int i = 0; i < pokedex.getNumPokemon(); i++) {

                        System.out.println(pokedex.getPokedexElement(i));


                        if (((i % 20) == 0) && (i != 1)) {
                            Scanner scanner = new Scanner(System.in);
                            System.out.println("Press return key to continue....");
                            scanner.nextLine();
                            printInfoHeader();


                        }
                    }
            }
        } while (userInput != 'q');                        //quit

        //Close scanner - not deprecated!
        scan.close();

        System.out.println("Goodbye!");
    }

    //Test method, creates four pokemons and prints them to console
    private static void test(Pokedex pokedex) {

        // Create pokemon objects for testing
        Pokemon bulbaSaur = new Pokemon(1, "Bulbasaur", "Grass", 45);
        Pokemon ivySaur = new Pokemon(2, "Ivysaur", "Grass", 60);
        Pokemon venuSaur = new Pokemon(3, "Venosaur", "Grass", 80);
        Pokemon charMander = new Pokemon(4, "Charmander", "Fire", 39);

        pokedex.add(bulbaSaur);
        pokedex.add(ivySaur);
        pokedex.add(venuSaur);
        pokedex.add(charMander);

        //Test pokemon objects, print to console
        System.out.println("\nTesting pokemon objects.....");
        printInfoHeader();

        //Print pokemon name and info, using toString
        System.out.print(pokedex);

        //Print number of sorted Pokemon to console
        System.out.println("\nYou have created " + pokedex.getNumPokemon() + " Pokemon objects!");

        pokedex.sortToFiles();
        System.out.println("\nTest Pokemon objects sorted and written to .csv files in present working directory.");
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
                "*     Use the (F)ile option to parse a pokemon CSV file, create       *\n" +
                "*     Pokemon objects from it, and load them into your Pokedex.       *\n" +
                "*     The (S)ort option will sort the Pokemon into Water and Grass    *\n" +
                "*     types, each in their own files.                                 *\n" +
                "*     The (P)rint option will display all the Pokemon objects in the  *\n" +
                "*     Pokedex.                                       *\n" +
                "*                                                                     *\n" +
                "***********************************************************************\n");
    }

    private static void fileInputHelper(File inputFileName, Pokedex pokedex) throws FileNotFoundException {

        try {

            Scanner fileReader = new Scanner(inputFileName);
            fileReader.nextLine();
            while (fileReader.hasNextLine()) {

                String[] pokemonInfoArray = generatePokemonInfoArray(fileReader);
                buildPokemon(pokemonInfoArray, pokedex);
            }

        } catch (FileNotFoundException e) {
            System.out.println("\nFile not found!");        //whoops
            throw e;
        }

    }

    private static String[] generatePokemonInfoArray(Scanner fileReader) {

        String pokemonInfo = fileReader.nextLine();
        String[] pokemonInfoArray = pokemonInfo.split(",");
        return pokemonInfoArray;
    }

    private static void buildPokemon(String[] pokemonInfoArray, Pokedex pokedex) {

        Pokemon pokemon = new Pokemon();

        try {
            pokemon.setPokeNumber(Integer.parseInt(pokemonInfoArray[0]));
            pokemon.setPokeName(pokemonInfoArray[1]);
            pokemon.setPokeType_1(pokemonInfoArray[2]);
            pokemon.setPokeType_2(pokemonInfoArray[3]);
            pokemon.setTotal(Integer.parseInt(pokemonInfoArray[4]));
            pokemon.setHitPoints(Integer.parseInt(pokemonInfoArray[5]));
            pokemon.setAttackPoints(Integer.parseInt(pokemonInfoArray[6]));
            pokemon.setDefensePoints(Integer.parseInt(pokemonInfoArray[7]));
            pokemon.setSpecAtkPoints(Integer.parseInt(pokemonInfoArray[8]));
            pokemon.setSpecDefPoints(Integer.parseInt(pokemonInfoArray[9]));
            pokemon.setPokeSpeed(Integer.parseInt(pokemonInfoArray[10]));
            pokemon.setPokeGeneration(Integer.parseInt(pokemonInfoArray[11]));
            pokemon.setLegendaryStatus(Boolean.parseBoolean(pokemonInfoArray[12]));

            pokedex.add(pokemon);

        } catch (Exception e) {
            System.out.println("Please check value fields in .csv file.");
        }

    }

    private static void printInfoHeader() {
        System.out.printf("\n%-4s %-35s %-20s %-20s\n", "#", "Name", "Type", "Hit Points");
        System.out.println("==========================================================================");
    }
}




