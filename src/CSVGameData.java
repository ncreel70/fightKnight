import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class CSVGameData extends GameData{
    private int id = 1;


    public CSVGameData(String gameData, String saveData){
        loadGameData(gameData);
        loadSaveData(saveData);
    }

    public void loadSaveData(String saveData){
        CSVReader reader = new CSVReader(saveData, false);

        while(reader.hasNext()){
            String[] nextKnight = reader.getNext();
            Knight savedKnight = new Knight(id, nextKnight[0], Integer.parseInt(nextKnight[1]),
                    Integer.parseInt(nextKnight[2]), Integer.parseInt(nextKnight[3]), DiceType.valueOf(nextKnight[4]),
                    Integer.parseInt(nextKnight[5]));
            knights.add(savedKnight);
            id++;
        }
    }

    public void loadGameData(String gameData){
        CSVReader reader = new CSVReader(gameData, false);


        while(reader.hasNext()){
            String[] nextMOB = reader.getNext();

            switch(nextMOB[0]){
                case("MOB"):
                    MOB newMOB = new MOB(nextMOB[1], Integer.parseInt(nextMOB[2]),
                            Integer.parseInt(nextMOB[3]), Integer.parseInt(nextMOB[4]), DiceType.valueOf(nextMOB[5]));
                    monsters.add(newMOB);
                    break;
                case("FORTUNE"):
                    Fortune newFortune;
                    if(nextMOB[5].equals("-")){
                        newFortune = new Fortune(nextMOB[1], Integer.parseInt(nextMOB[2]),
                                Integer.parseInt(nextMOB[3]), Integer.parseInt(nextMOB[4]));
                    }
                    else{
                        newFortune = new Fortune(nextMOB[1], Integer.parseInt(nextMOB[2]),
                                Integer.parseInt(nextMOB[3]), Integer.parseInt(nextMOB[4]), DiceType.valueOf(nextMOB[5]));
                    }
                    fortunes.add(newFortune);
                    break;
            }
        }
    }

    @Override
    public void save(String filename){
        try {
            PrintWriter output = new PrintWriter(filename);
            for(Knight knight : knights){
                output.println(knight.toCSV());
            }
            output.close();
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        CSVGameData test = new CSVGameData("gamedata.csv", "knights.csv");
        test.setActive(test.getKnight("1"));
        test.setActive(test.getKnight("15"));
        test.setActive(test.getKnight("12"));
        test.setActive(test.getKnight("10"));
        test.setActive(test.getKnight("11"));
        System.out.println(test.getActiveKnights());
        test.removeActive(test.getKnight("15"));
        System.out.println(test.getActiveKnights());

        System.out.println(test.getRandomMonsters());


       //System.out.println(test.getKnights());
       //test.save("test.csv");

    }
}
