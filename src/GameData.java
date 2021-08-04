import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class GameData {
    protected final List<Knight> activeKnights = new ArrayList<>();
    protected final List<Fortune> fortunes = new ArrayList<>();
    protected final List<Knight> knights = new ArrayList<>();
    protected final List<MOB> monsters = new ArrayList<>();
    protected static final Random random = new Random();

    public List<Knight> getKnights(){
        return knights;
    }

    public List<Knight> getActiveKnights(){
        return activeKnights;
    }

    public Knight getActive(String nameOrId){
        return findKnight(nameOrId, getActiveKnights());
    }

    public Knight getKnight(String nameOrId){
        return findKnight(nameOrId, getKnights());
    }

    protected Knight findKnight(String nameOrId, List<Knight> list){
        for(Knight knight : list){
            if(knight.getName().toLowerCase().contains(nameOrId.toLowerCase())){
                return knight;
            }
            else if(Integer.toString(knight.getId()).contains(nameOrId)){
                return knight;
            }
        }
        return null;
    }

    public boolean setActive(Knight kt){
        if(activeKnights.size() < 4){
            activeKnights.add(kt);
            return true;
        }
        return false;
    }

    public void removeActive(Knight kt){
        kt.resetDamage();
        activeKnights.remove(kt);
    }

    public Fortune getRandomFortune(){
        return fortunes.get(random.nextInt(fortunes.size()));
    }

    public List<MOB> getRandomMonsters(){
        List<MOB> returnMonsters = new ArrayList<>();
        for(int i = 0; i < activeKnights.size(); i++){
            returnMonsters.add(monsters.get(random.nextInt(monsters.size())).copy());
        }
        return returnMonsters;
    }

    public List<MOB> getRandomMonsters(int number){
        List<MOB> returnMonsters = new ArrayList<>();

        for(int i = 0; i < number; i++){
            returnMonsters.add(monsters.get(random.nextInt(monsters.size())).copy());
        }
        return returnMonsters;
    }

    public abstract void save(String filename);

}
