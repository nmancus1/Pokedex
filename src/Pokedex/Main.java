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

                    //Create new file object from input
                    File inputFileName = new File(scan.next());

                    //Attempt to parse file
                    try {
                        fileInputHelper(inputFileName, pokedex);                                //handle file input
                        System.out.println(pokedex.getNumPokemon() + " Pokemon built from " +
                                inputFileName + ".");                                           //success
                        fileReadOK = true;

                    } catch (FileNotFoundException e) {
                        System.out.println("Please enter a valid filename with .csv extension.");//file not found!
                    }
                    break;

                case 's':                                  //sort pokedex to files

                    //First make sure a file has been successfully read
                    if (fileReadOK) {

                        //Sort Pokemon based on type 1, confirm write successful
                        pokedex.sortToFiles();
                        System.out.println("Pokedex sorted successfully! Water and grass Pokemon written to .csv " +
                                "files in present working directory.");
                    } else {
                        System.out.println("Please input a valid file to scan, using the (F)ile command.");
                    }
                    break;

                case 'p':                                   //print all Pokemon to console
                    printInfoHeader();                      //print header

                    //Print paginated list to console
                    for (int i = 0; i < pokedex.getNumPokemon(); i++) {

                        System.out.println(pokedex.getPokedexElement(i));

                        //Wait for user OK after every 20 elements *pagination* with new header on every page
                        if (((i % 20) == 0) && (i != 0)) {
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
        System.exit(0);
    }

    //Test method, creates four pokemons and prints them to console
    private static void test(Pokedex pokedex) {

        // Create pokemon objects for testing
        Pokemon bulbaSaur = new Pokemon(1, "Bulbasaur", "Grass", 45);
        Pokemon ivySaur = new Pokemon(2, "Ivysaur", "Grass", 60);
        Pokemon venuSaur = new Pokemon(3, "Venosaur", "Grass", 80);
        Pokemon charMander = new Pokemon(4, "Charmander", "Fire", 39);

        //Test add
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
        System.out.println("\nPokemon objects sorted and written to .csv files in present working directory.");
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

    //Helper method to handle file input
    private static void fileInputHelper(File inputFileName, Pokedex pokedex) throws FileNotFoundException {

        //Read from input file using scanner
        try {
            Scanner fileReader = new Scanner(inputFileName);
            fileReader.nextLine();
            while (fileReader.hasNextLine()) {

                //Build string array from parsed line
                String[] pokemonInfoArray = generatePokemonInfoArray(fileReader);

                //Pass string array to build method
                buildPokemon(pokemonInfoArray, pokedex);
            }
        } catch (FileNotFoundException e) {
            System.out.println("\nFile not found!");        //whoops
            throw e;
        }

    }

    //Helper method for generating string array with Pokemon info
    private static String[] generatePokemonInfoArray(Scanner fileReader) {

        String pokemonInfo = fileReader.nextLine();
        String[] pokemonInfoArray = pokemonInfo.split(",");         //split string at commas into []
        return pokemonInfoArray;
    }

    //Build Pokemon object from String [], load into Pokedex, catch inputmismatch, etc.
    private static void buildPokemon(String[] pokemonInfoArray, Pokedex pokedex) {

        //Build new Pokemon
        try {
            Pokemon pokemon = new Pokemon(
                    Integer.parseInt(pokemonInfoArray[0]),
                    pokemonInfoArray[1],
                    pokemonInfoArray[2],
                    pokemonInfoArray[3],
                    Integer.parseInt(pokemonInfoArray[4]),
                    Integer.parseInt(pokemonInfoArray[5]),
                    Integer.parseInt(pokemonInfoArray[6]),
                    Integer.parseInt(pokemonInfoArray[7]),
                    Integer.parseInt(pokemonInfoArray[8]),
                    Integer.parseInt(pokemonInfoArray[9]),
                    Integer.parseInt(pokemonInfoArray[10]),
                    Integer.parseInt(pokemonInfoArray[11]),
                    Boolean.parseBoolean(pokemonInfoArray[12]));

            //Add to Pokedex
            pokedex.add(pokemon);

            //Catch input/parsing issues
        } catch (Exception e) {
            System.out.println("Possible input mismatch! Please check value fields in .csv file.");
        }

    }

    //Print info header and line
    private static void printInfoHeader() {
        System.out.printf("\n%-4s %-35s %-20s %-20s\n", "#", "Name", "Type", "Hit Points");
        System.out.println("==========================================================================");
    }
}




