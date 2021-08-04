public class Knight extends MOB{
    protected final int id;
    protected int xp;
    private Fortune fortune;

    public Knight(int id, String name, int hp, int armor, int hitModifier, DiceType damageDie){
        this(id, name, hp, armor, hitModifier, damageDie, 0);
    }

    public Knight(int id, String name, int hp, int armor, int hitModifier, DiceType damageDie, int xp){
        super(name, hp, armor, hitModifier, damageDie);
        this.id = id;
        this.xp = xp;
    }

    //Getters
    public int getId(){ return id; }

    public int getXP(){ return xp; }

    public Fortune getActiveFortune(){ return fortune; }

    @Override
    public int getMaxHP(){
        if(getActiveFortune() != null){
            return fortune.getMaxHP() + super.getMaxHP();

        }
        return super.getMaxHP();
    }

    @Override
    public int getHP(){
        return getMaxHP() - damage;
    }

    @Override
    public int getArmor(){
        if(getActiveFortune() != null){
            return fortune.getArmor() + super.getArmor();
        }
        return super.getArmor();
    }

    @Override
    public int getHitModifier(){
        if(getActiveFortune() != null){
            return fortune.getHitModifier() + super.getHitModifier();
        }
        return super.getHitModifier();
    }

    @Override
    public DiceType getDamageDie(){
        if(getActiveFortune() != null){
            if(getActiveFortune().getDamageDie() != null){
                return fortune.getDamageDie();
            }
        }
        return super.getDamageDie();
    }
    //Setters
    public void setActiveFortune(Fortune activeFortune){ fortune = activeFortune; }

    public void addXP(int xp){ this.xp += xp; }

    public String toCSV(){
        return String.format("%s,%d,%d,%d,%s,%d",
                getName(), getMaxHP(), getArmor(), getHitModifier(), getDamageDie(), getXP());
    }

    public String toString(){
        return String.format(
                "+============================+\n" +
                "| %-27s  |\n" +
                "| id: %-23d  |\n" +
                "|                              |\n" +
                "| Health: %-6d  XP: %-7d  |\n" +
                "|  Power: %-6s  Armor: %-4d  |\n" +
                "|                              |\n" +
                "+============================+\n",
                getName(),getId(), getMaxHP(), getXP(), getDamageDie().toString(), getArmor());
    }

    public static void main(String[] args){
        Knight test = new Knight(11, "John", 10, 10, 5, DiceType.D6, 0);

        System.out.println(test);
        Fortune testFortune = new Fortune("Tensman", 10, 10, 10);
        test.setActiveFortune(testFortune);
        System.out.println(test);
        Fortune testFortuneDD = new Fortune("Tensman", 10, 10, 10, DiceType.D10);
        test.setActiveFortune(testFortuneDD);
        System.out.println(test);
        System.out.println("TEST: test.toCSV should return John,20,20,15,D10,15 - " +
                test.toCSV().equals("John,20,20,15,D10,15"));
        System.out.println(test.getHP());

        System.out.println(test.getXP());
        test.addXP(2);
        System.out.println(test.getXP());

    }
}
