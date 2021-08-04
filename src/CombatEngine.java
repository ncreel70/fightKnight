import java.util.List;
import java.util.Random;

public class CombatEngine {
    private GameData data;
    private GameView view;
    private boolean battle = true;
    private List <MOB> activeMonsters;

    public CombatEngine(GameData data, GameView view){
        this.data = data;
        this.view = view;
    }

    public void clear(){
        List <Knight> activeKnights = data.getActiveKnights();

        for(Knight knight : activeKnights) {
            knight.setActiveFortune(null);
        }
    }

    public void initialize(){
        List <Knight> activeKnights = data.getActiveKnights();

        for(Knight knight :  activeKnights){
            knight.setActiveFortune(data.getRandomFortune());
        }
        view.printFortunes(data.getActiveKnights());
    }

    public void runCombat(){
        setActiveMonsters();
        List <Knight> activeKnights = data.getActiveKnights();
        printBattleText(activeMonsters);

        while(battle){
            knightCombat(activeKnights, activeMonsters);
            checkVictory(activeMonsters);
            mobCombat(activeMonsters, activeKnights);
            checkDefeat(activeKnights);
        };
    }

    public void setActiveMonsters(){
        activeMonsters = data.getRandomMonsters();
    }

    public void printBattleText(List<MOB> activeMonsters){
        view.printBattleText(activeMonsters, data.getActiveKnights());
    }

    public void checkDefeat(List<Knight> activeKnights){
        if(activeKnights.size() <= 0){
            view.printDefeated();
            battle = false;
        }
    }

    public void checkVictory(List<MOB> activeMonsters){
        if(activeMonsters.size() <= 0){
            if(view.checkContinue()){
                setActiveMonsters();
                printBattleText(this.activeMonsters);
            }
            else{
                battle = false;
            }
        }
    }
    public void knightCombat(List <Knight> attackingParty, List <MOB> defendingParty){
        Random random = new Random();
        MOB defender = defendingParty.get(random.nextInt(defendingParty.size()));
        for(Knight attacker : attackingParty){
            if(defender.getHP() <= 0){
                continue;
            }
            if(hitSuccess(attacker.getHitModifier(), defender.getArmor())){
                defender.addDamage(rollDamage(attacker));
                reportMobCasualty(defender,defendingParty, attackingParty);
            }
        }
    }

    public void reportKnightCasualty(Knight knight, List<Knight> knights){
        if(knight.getHP() <= 0){
            view.printBattleText(knight);
            knights.remove(knight);
        }
    }

    public void mobCombat(List <MOB> attackingParty, List <Knight> defendingParty){
        Random random = new Random();
        Knight defender = defendingParty.get(random.nextInt(defendingParty.size()));
        for(MOB attacker : attackingParty){
            if(defender.getHP() <= 0){
                continue;
            }
            if(hitSuccess(attacker.getHitModifier(), defender.getArmor())){
                defender.addDamage(rollDamage(attacker));
                reportKnightCasualty(defender, defendingParty);
            }
        }
    }

    public void reportMobCasualty(MOB monster, List<MOB> monsters, List<Knight> knights){
        if(monster.getHP() <= 0){
            monsters.remove(monster);
            view.printBattleText(monster);
            for(Knight knight : knights){
                knight.addXP(1);
            }
        }
    }

    public boolean hitSuccess(int hitMod, int armor){
        DiceSet attackDie = new DiceSet();
        int attack = attackDie.roll(DiceType.D20) + hitMod;

        if(attack > armor){
            return true;
        }
        return false;
    }

    public int rollDamage(MOB attacker){
        DiceSet damageDie = new DiceSet();
        return damageDie.roll(attacker.getDamageDie());
    }

    public static void main(String[] args){
        GameData testData = new CSVGameData("gamedata.csv","knights.csv");
        ConsoleView testView = new ConsoleView();
        testData.setActive(testData.getKnight("0"));
        testData.setActive(testData.getKnight("2"));
        testData.setActive(testData.getKnight("10"));

        CombatEngine test = new CombatEngine(testData, testView);

        //testView.printFortunes(testData.getActiveKnights());
        test.initialize();

        //test.clear();
        //testView.printFortunes(testData.getActiveKnights());
        test.runCombat();

    }
}
