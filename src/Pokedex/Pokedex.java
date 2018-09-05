/*Nick Mancuso
 *
 *This class is a Pokemon index for organizing Pokemon objects, as well as sorting and printing their information
 *
 */

package Pokedex;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class Pokedex {

    private int numPokemon = 0;
    private Pokemon[] pokemonArray;

    //Constructor
    public Pokedex() {
        pokemonArray = new Pokemon[0];
    }

    //Add Pokemon
    public void add(Pokemon pokemon) {

        //Grow array size
        this.pokemonArray = growArray();

        //Assign new pokemon
        pokemonArray[pokemonArray.length - 1] = pokemon;

        //Inc total Pokemon in Pokedex
        this.numPokemon++;
    }

    //Helper method to grow array size when adding a Pokemon
    public Pokemon[] growArray() {

        //Grow by 1
        Pokemon[] newPokemonArray = new Pokemon[this.pokemonArray.length + 1];

        //Copy array
        for (int i = 0; i < this.pokemonArray.length; i++) {
            newPokemonArray[i] = this.pokemonArray[i];
        }

        //Set old array to null for garbage collection
        pokemonArray = null;

        return newPokemonArray;

    }

    //Returns number of Pokemon in Pokedex
    public int getNumPokemon() {
        return numPokemon;
    }

    //Allows iteration through Pokedex
    public Pokemon getPokedexElement(int i) {
        return pokemonArray[i];
    }

    //Sort Pokemon by type, into two files.  If files exist, they will be appended.
    public void sortToFiles() {

        for (int i = 0; i < numPokemon; i++) {

            try {
                //Create Printwriters for files
                PrintWriter grassPokemonFile = new PrintWriter(new FileOutputStream("grass_pokemons.csv", true));
                PrintWriter waterPokemonFile = new PrintWriter(new FileOutputStream("water_pokemons.csv", true));

                //If pokemon is grass element
                if (pokemonArray[i].getPokeType_1().equalsIgnoreCase("grass")) {
                    grassPokemonFile.println(pokemonArray[i].toCSVString());
                    grassPokemonFile.flush();
                    grassPokemonFile.close();

                    //If pokemon is water element
                } else if (pokemonArray[i].getPokeType_1().equalsIgnoreCase("water")) {
                    waterPokemonFile.println(pokemonArray[i].toCSVString());
                    waterPokemonFile.flush();
                    waterPokemonFile.close();
                }
            } catch (IOException e) {
                System.out.println("Error writing to file!");       //whoops
            }
        }

    }

    //Generates a list of entire Pokedex using Stringbuilder, gets out of hand without pagination
    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < numPokemon; i++) {
            sb.append(pokemonArray[i].toString());
        }

        return sb.toString();
    }
}
