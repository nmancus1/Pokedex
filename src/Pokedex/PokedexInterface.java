package Pokedex;

public interface PokedexInterface {

    /**
     * Get current number of elements in PokeDex
     *
     * @return an integer indicating the number of elements
     */
    int getCurrentSize();

    /**
     * Checks if Pokedex is empty
     *
     * @return true if Pokedex is empty
     */
    boolean isEmpty();

    /**
     * Add new entry to Pokedex
     *
     * @param pokemon to be added to bag
     * @return true if successful
     */
    void add(Pokemon pokemon);

    /**
     * Removes one Pokemon from Pokedex
     *
     * @return true if successful
     */
    boolean remove(String pokeName);

    /**
     * Removes all items from Pokedex
     *
     * @returns true if successful
     */
    boolean removeAll();

    /**
     * Checks how many of a given pokemon is in Pokedex
     *
     * @return int indicating number of given pokemon
     */
    int getNumberOf(String pokeName);


}
