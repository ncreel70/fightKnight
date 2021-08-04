import java.util.Random;

public class DiceSet {
    Random randGen = new Random();
    public int roll(DiceType dice){
        switch(dice){
            case D4:
                return randGen.nextInt(4) + 1;
            case D6:
                return randGen.nextInt(6) + 1;
            case D8:
                return randGen.nextInt(8) + 1;
            case D10:
                return randGen.nextInt(10) + 1;
            case D12:
                return randGen.nextInt(12) + 1;
            case D20:
                return randGen.nextInt(20) + 1;
        }
        return 0;
    }

    public static void main(String[] args){
        DiceSet test = new DiceSet();

        System.out.println("TEST roll DiceType.D4 should return a number between 1 and 4 - " + test.roll(DiceType.D4));
        System.out.println("TEST roll DiceType.D6 should return a number between 1 and 6 - " + test.roll(DiceType.D6));
        System.out.println("TEST roll DiceType.D8 should return a number between 1 and 8 - " + test.roll(DiceType.D8));
        System.out.println("TEST roll DiceType.D10 should return a number between 1 and 10 - " + test.roll(DiceType.D10));
        System.out.println("TEST roll DiceType.D12 should return a number between 1 and 12 - " + test.roll(DiceType.D12));
        System.out.println("TEST roll DiceType.D20 should return a number between 1 and 20 - " + test.roll(DiceType.D20));
    }
}
