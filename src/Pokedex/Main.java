package Pokedex;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        //Declare and initialize scanner, user input variable, create pokemonCounter
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
                    fileInputHandler();

                    //call fileInput, store return value in pokemonCount
                    //int pokemonCount = fileInput_pokemonCounter();

                    //print number of pokemons sorted to console
                    //System.out.println("\n" + pokemonCount + " pokemons sorted!\n" +
                            //"Output files generated: grass_pokemons.csv, water_pokemons.csv");
                    break;
            }
        } while (userInput != 'q');                        //quit

        //Close scanner - not deprecated!
        scan.close();

        System.out.println("Goodbye!");
    }

    //Test method, creates four pokemons and prints them to console
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

        //Print pokemon name and info, using toString
        for (Pokemon pokemon : pokeList) {
            System.out.print(pokemon);
        }

        System.out.println("\nYou have created " + Pokemon.getNumPokemon() + " Pokemon objects!");
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

    //Handles file input and counts number of pokemons sorted
    private static int fileInput_pokemonCounter() {
        //pokemon counting variable
        int pokemonCount = 0;

        //Scanner to accept user input for filename
        Scanner scan = new Scanner(System.in);


        //Prompt user to input file name
        System.out.println("\nPlease enter the name of the file, including the extension (\".csv\", etc.), or 'Q' to  quit. ");
        System.out.print("~>");

        File inputFile = new File(scan.nextLine());

        //Attempt to read file
        try (Scanner fileReader = new Scanner(inputFile)) {
            while (fileReader.hasNextLine()) {
                pokemonSorter(fileReader.nextLine());       //call pokemonSorter
                pokemonCount++;                             //increment pokemonCounter
            }
        } catch (IOException e) {
            System.out.println("\nFile not found!");        //whoops
        }
        return pokemonCount;
    }

    private static void pokemonSorter(String pokemonInfo) {

        //Split pokemonInfo string in array, using commas as delimiter
        String[] pokemonInfoArray = pokemonInfo.split(",");

        try {
            //Create Printwriters for files
            PrintWriter grasspokemonFile = new PrintWriter(new FileOutputStream("grass_pokemons.csv", true));
            PrintWriter waterpokemonFile = new PrintWriter(new FileOutputStream("water_pokemons.csv", true));

            //If pokemon is grass element
            if (pokemonInfoArray[2].equalsIgnoreCase("grass")) {
                grasspokemonFile.println(pokemonInfo);
                grasspokemonFile.flush();
                grasspokemonFile.close();

                //If pokemon is water element
            } else if (pokemonInfoArray[2].equalsIgnoreCase("water")) {
                waterpokemonFile.println(pokemonInfo);
                waterpokemonFile.flush();
                waterpokemonFile.close();
            }
        } catch (IOException e) {
            System.out.println("Error writing to file!");       //whoops
        }
    }

    private static void fileInputHandler() {


        ArrayList pokemonList;

        //Scanner to accept user input for filename
        Scanner scan = new Scanner(System.in);

        //Prompt user to input file name
        System.out.println("\nPlease enter the name of the file, including the extension (\".csv\", etc.), or 'Q' to  quit. ");
        System.out.print("~>");

        File inputFileName = new File(scan.nextLine());

        try {
            buildPokemonArray(inputFileName);

        } catch (Exception e) {
            System.out.println("\nFile not found!");        //whoops
        }

    }

    private static void buildPokemonArray(File inputFileName) {


        ArrayList<Pokemon> pokemonList = new ArrayList<Pokemon>();

        //Attempt to read file
        try (Scanner fileReader = new Scanner(inputFileName)) {
            while (fileReader.hasNextLine()) {

            }
            String pokemonInfo = fileReader.nextLine();
            String[] pokemonInfoArray = pokemonInfo.split(",");


            Pokemon pokemon = new Pokemon();
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

        } catch (IOException e) {
            System.out.println("Cannot read file!");

        }

        for (Pokemon pokemon : pokemonList) {

            pokemonObjSorter(pokemon);
        }
    }

            private static void pokemonObjSorter (Pokemon pokemon){

                try {
                    //Create Printwriters for files
                    PrintWriter grasspokemonFile = new PrintWriter(new FileOutputStream("grass_pokemons.csv", true));
                    PrintWriter waterpokemonFile = new PrintWriter(new FileOutputStream("water_pokemons.csv", true));

                    //If pokemon is grass element
                    if (pokemon.getPokeType_1().equalsIgnoreCase("grass")) {
                        grasspokemonFile.println(pokemon.toCSVString());
                        grasspokemonFile.flush();
                        grasspokemonFile.close();

                        //If pokemon is water element
                    } else if (pokemon.getPokeType_1().equalsIgnoreCase("water")) {
                        waterpokemonFile.println(pokemon.toCSVString());
                        waterpokemonFile.flush();
                        waterpokemonFile.close();
                    }
                } catch (IOException e) {
                    System.out.println("Error writing to file!");       //whoops
                }
            }

        }




