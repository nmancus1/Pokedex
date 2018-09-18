package Pokedex;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class Pokedex {

    //Data Fields
    private int numberOfPokemon;
    private Pokemon[] pokemonArray;
    private String type = "all";

    //Constructor
    public Pokedex(Pokemon[] pokemonArray) {
        //Check if array is empty, if so, instantiate array
        if (pokemonArray == null) {
            this.pokemonArray = new Pokemon[1];
            this.numberOfPokemon = 0;

            //if not, count number of Pokemon
        } else {
            this.pokemonArray = pokemonArray;
            for (Pokemon p : pokemonArray) {
                if (p != null) {
                    this.numberOfPokemon++;
                }
            }
        }
    }

    public Pokedex() {
        this.pokemonArray = new Pokemon[0];
        this.numberOfPokemon = 0;
    }

    public Pokedex(String type) {
        this.pokemonArray = new Pokemon[0];
        this.numberOfPokemon = 0;
        this.type = type;
    }

    /**
     * Returns type of pokemon in index, if sorted
     */
    public String getPokedexType() {
        return type;
    }

    /**
     * Get current number of elements in PokeDex
     *
     * @return an integer indicating the number of elements
     */
    public int getCurrentSize() {
        return numberOfPokemon;
    }

    /**
     * Checks if Pokedex is empty
     *
     * @return true if Pokedex is empty
     */
    public boolean isEmpty() {

        return numberOfPokemon == 0;
    }

    /**
     * Add new entry to Pokedex
     *
     * @param pokemon to be added to pokedex
     */
    public void add(Pokemon pokemon) {


        //Check size of current array
        if (pokemonArray.length == numberOfPokemon) {
            this.pokemonArray = Pokedex.growArray(this.pokemonArray);
        }

        //Add new pokemon to array
        if (pokemonArray.length != 1) {
            pokemonArray[numberOfPokemon] = pokemon;
        } else {
            pokemonArray[0] = pokemon;
        }
        //Increment counter
        numberOfPokemon++;
    }

    /**
     * Removes one Pokemon from Pokedex
     *
     * @param pokeName name of pokemon to remove
     * @return true if successful
     */
    public boolean remove(String pokeName) {

        //Find correct pokemon to remove
        for (int i = 0; i < this.numberOfPokemon; i++) {
            if (pokemonArray[i].getPokeName().equalsIgnoreCase(pokeName)) {

                //If found, set pokemon to null
                pokemonArray[i] = null;
                numberOfPokemon--;


                //Shift elements left to fill gap
                for (int j = i; j < pokemonArray.length - 1; j++) {
                    pokemonArray[j] = pokemonArray[j + 1];
                }
            }

        }
        return false;
    }

    /**
     * Removes all items from Pokedex
     *
     * @returns true if successful
     */
    public boolean removeAll() {

        //Variable to hold number of pokemon to delete
        int numPokemonToDelete = this.numberOfPokemon;

        //Set all pokemon to null, update counter.
        for (int i = 0; i < numPokemonToDelete; i++) {
            pokemonArray[i] = null;
            this.numberOfPokemon--;
        }
        //Return true if all are removed
        return this.numberOfPokemon == 0;
        //Problem removing, return false
    }

    /**
     * Checks how many of a given pokemon is in Pokedex
     *
     * @return int indicating number of given pokemon
     */
    public int getNumberOf(String pokeName) {

        int counter = 0;
        for (int i = 0; i < this.numberOfPokemon; i++) {
            if (pokemonArray[i].getPokeName().equalsIgnoreCase(pokeName)) {
                counter++;
            }
        }
        return counter;
    }

    /**
     * Grows array by ten elements
     */
    private static Pokemon[] growArray(Pokemon[] pokemonArray) {

        //Make new array
        Pokemon[] largerPokemonArray = new Pokemon[pokemonArray.length + 10];
        //Copy elements
        for (int i = 0; i < pokemonArray.length; i++) {
            largerPokemonArray[i] = pokemonArray[i];
        }
        return largerPokemonArray;

    }

    /**
     * Builds a list of Pokedex elements and returns string, needs to be paginated if using > 20 pokemon or so
     *
     * @returns formatted String of pokemon
     */
    @Override
    public String toString() {

        //Create new Stringbuilder
        StringBuilder sb = new StringBuilder();

        //Build string of all pokemon
        for (Pokemon p : pokemonArray) {
            if (p != null) {
                sb.append(p);
            }
        }

        return sb.toString();
    }

    /**
     * Creates a sorted pokedex, by first type
     *
     * @param pokeType_1
     * @return sorted pokedex for writing to file or printing
     */
    public Pokedex getSortedPokedex(String pokeType_1, Pokedex pokedex) {

        //Create new pokedex, constructing with "type" data field
        Pokedex sortedPokedex = new Pokedex(pokeType_1);

        //Sort by type
        for (int i = 0; i < pokedex.getCurrentSize(); i++) {
            if (this.pokemonArray[i].getPokeType_1().equalsIgnoreCase(pokeType_1)) {
                sortedPokedex.add(this.pokemonArray[i]);
            }
        }
        //return sorted pokedex
        return sortedPokedex;
    }

    /**
     * This method prints sorted and unsorted monsters to a .csv file in the pwd
     *
     * @return
     */
    public boolean printToCSVFile() {

        String type = this.getPokedexType() + "_monsters.csv";
        try {
            //Create printwriter, use pokemon type for filename.
            PrintWriter pw = new PrintWriter(new FileOutputStream(type, true));

            //Use current size of pokedex for loop
            int index = this.getCurrentSize();

            //Write each sorted monster to file
            for (int i = 0; i < index; i++) {
                pw.println(this.pokemonAt(i).toCSVString());

            }
            //Close printwriter and send success message to console
            pw.flush();
            pw.close();
            System.out.println("File " + type + " successfully written to disk.");
            return true;

        } catch (IOException e) {
            System.out.println("Error writing to file!");       //whoops
        }
        return false;

    }

    /**
     * Use this method to iterate through pokedex
     *
     * @param index
     * @return
     */
    public Pokemon pokemonAt(int index) {
        return pokemonArray[index];
    }




}
