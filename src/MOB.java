public class MOB implements Attributes {
    protected int armor;
    protected int damage;
    protected DiceType damageDie;
    protected int hitModifier;
    protected int maxHP;
    private final String name;

    public MOB(String name, int maxHP, int armor, int hitModifier, DiceType damageDie){
        this.name = name;
        this.maxHP = maxHP;
        this.armor = armor;
        this.hitModifier = hitModifier;
        this.damageDie = damageDie;
    }

    public String getName(){ return name; }

    public void addDamage(int damage){
       this.damage += damage;
    }

    public int getHP(){
        return maxHP - damage;
    }

    public void resetDamage(){
        damage = 0;
    }

    public MOB copy(){
        return new MOB(this.name, this.maxHP, this.armor, this.hitModifier, this.damageDie);
    }

    @Override
    public String toString(){
        return String.format("[name = %s, max health = %d, damage = %d, current health = %d, armor = %d, hit modifier = %d, damage die = %s]",
                getName(), getMaxHP(), damage, getHP(), getArmor(), getHitModifier(), getDamageDie().toString());
    }

    @Override
    public int getArmor(){
        return armor;
    }

    @Override
    public int getMaxHP(){
        return maxHP;
    }


    public int getDamage() { return damage; }

    @Override
    public DiceType getDamageDie(){
        return damageDie;
    }

    @Override
    public int getHitModifier(){
        return hitModifier;
    }

    public static void main(String[] args){
        MOB test = new MOB("Link", 10, 5, 2, DiceType.D6);
        System.out.println("TEST initial test - " + test);

        test.addDamage(2);
        MOB test2 = test.copy();
        System.out.println("TEST addDamage() - " + test);
        System.out.println("TEST: copy() - " + test2);

        test.resetDamage();
        System.out.println("TEST resetDamage - " + test);



    }
}
