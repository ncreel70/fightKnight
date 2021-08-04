public class Fortune implements Attributes {
    private String name;
    private int hpBonus;
    private int armor;
    private DiceType type;
    private int hitModifier;

    public Fortune(String name, int hpBonus, int armor, int hitModifier){
        this.name = name;
        this.hpBonus = hpBonus;
        this.armor = armor;
        this.hitModifier = hitModifier;
    }

    public Fortune(String name, int hpBonus, int armor, int hitModifier, DiceType type){
        this.name = name;
        this.hpBonus = hpBonus;
        this.armor = armor;
        this.hitModifier = hitModifier;
        this.type = type;
    }

    @Override
    public int getArmor(){
        return armor;
    }

    public String getName() { return name; }

    @Override
    public int getMaxHP(){
        return hpBonus;
    }

    @Override
    public DiceType getDamageDie(){
        return type;
    }

    @Override
    public int getHitModifier(){
        return hitModifier;
    }

    @Override
    public String toString(){
        String damageAdj =  type == null ? "-" : type.toString() ;
        return String.format(
                "+======================+\n" +
                "|%-22s|\n" +
                "|HP Bonus: %+12d|\n" +
                "|AC Bonus: %+12d|\n" +
                "|Hit Bonus: %+11d|\n" +
                "|Damage Adj: %10s|\n" +
                "+======================+\n",
                name, hpBonus, armor, hitModifier, damageAdj
                );
    }

    public static void main(String[] args){
       Fortune test = new Fortune("Tensman", 10, 10, 10);
       System.out.println(test);

       Fortune test2 = new Fortune("Twelvesman", 12, 12, 12, DiceType.D12);
        System.out.println(test2);
    }
}
