package Pokedex;

public class PokedexTest {
    public static void main(String[] args) {

        Pokedex pokedex = new Pokedex();

        Pokemon bulbaSaur = new Pokemon(1, "Bulbasaur", "Grass", 45);
        Pokemon ivySaur = new Pokemon(2, "Ivysaur", "Grass", 60);
        Pokemon venuSaur = new Pokemon(3, "Venosaur", "Grass", 80);
        Pokemon charMander = new Pokemon(4, "Charmander", "Fire", 39);

        Pokemon venuSaur2 = new Pokemon(3, "Venosaur", "Grass", 80);
        Pokemon charMander2 = new Pokemon(4, "Charmander", "Fire", 39);

        //Test add
        pokedex.add(bulbaSaur);
        pokedex.add(ivySaur);
        pokedex.add(venuSaur);
        pokedex.add(charMander);
        pokedex.add(venuSaur2);
        pokedex.add(charMander2);
        System.out.println(pokedex);

        System.out.println("Number of charmander " + pokedex.getNumberOf("charmander"));


        pokedex.remove("charmander");
        System.out.println(pokedex);
        pokedex.removeAll();
        System.out.println(pokedex);
        System.out.println(pokedex.removeAll());

        System.out.println("Number of charmander " + pokedex.getNumberOf("charmander"));


        Pokemon bulbaSaur2 = new Pokemon(1, "Bulbasaur", "Grass", 45);
        Pokemon ivySaur3 = new Pokemon(2, "Ivysaur", "Grass", 60);
        Pokemon venuSaur3 = new Pokemon(3, "Venosaur", "Grass", 80);
        Pokemon charMander3 = new Pokemon(4, "Charmander", "Fire", 39);

        Pokemon venuSaur233 = new Pokemon(3, "Venosaur", "Grass", 80);
        Pokemon charMander23 = new Pokemon(4, "Charmander", "Fire", 39);

        pokedex.add(bulbaSaur2);
        pokedex.add(ivySaur3);
        pokedex.add(venuSaur3);
        pokedex.add(charMander3);
        pokedex.add(venuSaur233);
        pokedex.add(charMander23);

        Pokedex sortedPokedex = pokedex.getSortedPokedex("grass", pokedex);
        System.out.println(sortedPokedex);

        System.out.println(sortedPokedex + "OK");

        sortedPokedex.printToCSVFile();

        pokedex.printToCSVFile();


    }
}
