package Pokedex;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        //Declare and itialize scanner and user input variable
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
            userInput  = scan.next().charAt(0);

            //Menu system
            switch (userInput) {
                case 't': test();                               //test
                    break;
                case 'g': printGreeting();                      //menu
                    break;
                case 'f': fileInput();
                    break;
            }
        } while (userInput != 'q');                             //quit

        System.out.println("Goodbye!");
    }

    public static void test() {

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

        System.out.println("Testing.....");
        for (Pokemon monster : pokeList) {

            System.out.println(monster.testToString());

        }
    }

    public static void printPokeAscii() {

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

    public static void printGreeting() {

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

    public static void fileInput () {

        String pokeInputFile;
        Scanner scan = new Scanner(System.in);

        System.out.print("Please enter the name of the file, including the extension (\".csv\", etc.): ");
        pokeInputFile = scan.nextLine();

        try {
            FileInputStream inputFile = new FileInputStream(pokeInputFile);
        } catch (IOException e) {
            System.out.println("File not found!");
        }


    }

    public static void fileSort () {

    }

}




