package Pokedex;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class Pokedex {

    private int numPokemon = 0;
    private Pokemon[] pokemonArray;

    public Pokedex() {
        pokemonArray = new Pokemon[0];
    }

    public Pokedex(int size) {
        pokemonArray = new Pokemon[size];
    }

    public void add(Pokemon pokemon) {

        this.pokemonArray = growArray();
        pokemonArray[pokemonArray.length - 1] = pokemon;
        this.numPokemon++;

    }

    public Pokemon[] growArray() {

        Pokemon[] newPokemonArray = new Pokemon[this.pokemonArray.length + 1];

        for (int i = 0; i < this.pokemonArray.length; i++) {
            newPokemonArray[i] = this.pokemonArray[i];
        }
        pokemonArray = null;
        return newPokemonArray;

    }

    public int getNumPokemon() {
        return numPokemon;
    }

    public void setNumPokemon(int numPokemon) {
        this.numPokemon = numPokemon;
    }

    public Pokemon getPokedexElement(int i) {
        return pokemonArray[i];
    }

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

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < numPokemon; i++) {
            sb.append(pokemonArray[i].toString());
        }

        return sb.toString();
    }
}
