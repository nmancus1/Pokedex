package Pokedex;

public class Pokemon {

    private int pokeNumber;                 //pokemon number in list
    private String pokeName;                //pokemon name
    private String pokeType_1;              //first type
    private String pokeType_2;              //second type
    private int total;                      //total points
    private int hitPoints;                  //hit points
    private int attackPoints;               //attack points
    private int defensePoints;              //defense points
    private int specAtkPoints;              //special attack points
    private int specDefPoints;              //special defense points
    private int pokeSpeed;                  //pokemon speed
    private int pokeGeneration;             //what generation
    private boolean legendaryStatus;        //is this pokemon legendary?

    //Default constructor
    public Pokemon() {
    }

    public Pokemon(int pokeNumber, String pokeName, String pokeType_1, String pokeType_2, int total,
                   int hitPoints, int attackPoints, int defensePoints, int specAtkPoints,
                   int specDefPoints, int pokeSpeed, int pokeGeneration, boolean legendaryStatus) {
        this.pokeNumber = pokeNumber;
        this.pokeName = pokeName;
        this.pokeType_1 = pokeType_1;
        this.pokeType_2 = pokeType_2;
        this.total = total;
        this.hitPoints = hitPoints;
        this.attackPoints = attackPoints;
        this.defensePoints = defensePoints;
        this.specAtkPoints = specAtkPoints;
        this.specDefPoints = specDefPoints;
        this.pokeSpeed = pokeSpeed;
        this.pokeGeneration = pokeGeneration;
        this.legendaryStatus = legendaryStatus;
    }

    //Constructor for hardcoded testing
    public Pokemon(int pokeNumber, String pokeName, String pokeType_1, int hitPoints) {
        this.pokeNumber = pokeNumber;
        this.pokeName = pokeName;
        this.pokeType_1 = pokeType_1;
        this.hitPoints = hitPoints;
    }

    public int getPokeNumber() {
        return pokeNumber;
    }

    public void setPokeNumber(int pokeNumber) {
        this.pokeNumber = pokeNumber;
    }

    public String getPokeName() {
        return pokeName;
    }

    public void setPokeName(String pokeName) {
        this.pokeName = pokeName;
    }

    public String getPokeType_1() {
        return pokeType_1;
    }

    public void setPokeType_1(String pokeType_1) {
        this.pokeType_1 = pokeType_1;
    }

    public String getPokeType_2() {
        return pokeType_2;
    }

    public void setPokeType_2(String pokeType_2) {
        this.pokeType_2 = pokeType_2;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    public int getAttackPoints() {
        return attackPoints;
    }

    public void setAttackPoints(int attackPoints) {
        this.attackPoints = attackPoints;
    }

    public int getDefensePoints() {
        return defensePoints;
    }

    public void setDefensePoints(int defensePoints) {
        this.defensePoints = defensePoints;
    }

    public int getSpecAtkPoints() {
        return specAtkPoints;
    }

    public void setSpecAtkPoints(int specAtkPoints) {
        this.specAtkPoints = specAtkPoints;
    }

    public int getSpecDefPoints() {
        return specDefPoints;
    }

    public void setSpecDefPoints(int specDefPoints) {
        this.specDefPoints = specDefPoints;
    }

    public int getPokeSpeed() {
        return pokeSpeed;
    }

    public void setPokeSpeed(int pokeSpeed) {
        this.pokeSpeed = pokeSpeed;
    }

    public int getPokeGeneration() {
        return pokeGeneration;
    }

    public void setPokeGeneration(int pokeGeneration) {
        this.pokeGeneration = pokeGeneration;
    }

    public boolean isLegendaryStatus() {
        return legendaryStatus;
    }

    public void setLegendaryStatus(boolean legendaryStatus) {
        this.legendaryStatus = legendaryStatus;
    }

    //toString to be used for console printing, corresponds to printHeaderInfo() formatting
    @Override
    public String toString() {
        return String.format("%-4s %-35s %-20s %-20s\n", this.pokeNumber, this.pokeName,
                this.pokeType_1, this.hitPoints);
    }


    //toString for CSV file output
    public String toCSVString() {
        return pokeNumber + "," +
                pokeName + "," +
                pokeType_1 + "," +
                pokeType_2 + "," +
                total + "," +
                hitPoints + "," +
                attackPoints + "," +
                defensePoints + "," +
                specAtkPoints + "," +
                specDefPoints + "," +
                pokeSpeed + "," +
                pokeGeneration + "," +
                legendaryStatus;
    }

    //Prints all info
    public String fullToString() {
        return "\n\n# " + pokeNumber +
                " " + pokeName + '\'' +
                ", Type 1= '" + pokeType_1 + '\'' +
                ", Type 2= '" + pokeType_2 + '\'' +
                ", \ntotal= " + total +
                ", HP= " + hitPoints +
                ", Attack Points= " + attackPoints +
                ", Defense Points= " + defensePoints +
                ", Special Attack Points= " + specAtkPoints +
                ", \nSpecial Defense Points= " + specDefPoints +
                ", pokeSpeed= " + pokeSpeed +
                ", pokeGeneration= " + pokeGeneration +
                ", legendaryStatus= " + legendaryStatus + "\n";
    }


}
