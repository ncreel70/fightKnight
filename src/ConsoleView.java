import java.util.List;
import java.util.Scanner;

public class ConsoleView implements GameView{
    Scanner scnr = new Scanner(System.in);
    @Override
    public void splashScreen(){
        System.out.println("SPLASH SCREEN");
    }

    @Override
    public void endGame(){
        System.out.println("LATER, END GAME");
    }

    @Override
    public String displayMainMenu(){

        System.out.println("What would you like to do?");
        return scnr.nextLine();
    }

    @Override
    public void printHelp(){
        System.out.println("Unsure what to do, here are some options:\n" +
                "\tls or list all  - listing the knights\n" +
                "\tlist active  - list the active knights knights only\n" +
                "\tshow name or id - show the knight details card\n" +
                "\tset active name or id - set knight as active (note: only 4 knights can be active) \n" +
                "\tremove active name or id - remove a knight from active status (heals knight)\n" +
                "\texplore or adventure or quest - find random monsters to fight\n" +
                "\tsave filename - save the game to the file name (default: saveData.csv)\n" +
                "\texit or goodbye - to leave the game\n" +
                "\n" +
                " Game rules: You can have four active knights. As long as they are active, they won't heal, \n" +
                " but they can gain XP by going on adventures.\n" +
                " When you make a knight inactive, they will heal. How many monsters can you defeat \n" +
                " before, you have to heal?");
    }

    @Override
    public void listKnights(List<Knight> knights){
        if(knights.isEmpty()){
            System.out.println("No knights to list");
        }
        else{
            for(Knight knight : knights){
                System.out.println(String.format("%d: %s", knight.getId(), knight.getName()));
            }
            System.out.println("");
        }
    }

    @Override
    public void knightNotFound(){
        System.out.println("Knight not found!");
    }

    @Override
    public void showKnight(Knight knight){
        System.out.println(knight.toString());
    }

    @Override
    public void setActiveFailed(){
        System.out.println("Unable to set active knight. Only four can be active at a time.");
    }


    @Override
    public void printBattleText(List<MOB> monsters, List<Knight> activeKnights){
        StringBuilder list = new StringBuilder();
        list.append("Our heroes come across the following monsters. Prepare for battle!\n");
        if(monsters.size() > activeKnights.size()){
            tableBuilder(monsters, activeKnights, list, monsters.size(), activeKnights.size());
        }
        else {
            tableBuilder(monsters, activeKnights, list, activeKnights.size(), monsters.size());
        }
        System.out.println(list);
    }

    @Override
    public void printBattleText(MOB dead){
        System.out.println(String.format("%s was defeated!", dead.getName()));
    }

    private void tableBuilder(List<MOB> monsters, List<Knight> activeKnights, StringBuilder list, int size, int size2) {
        for(int i = 0; i < size; i++){
            if(i < size2){
                list.append(String.format("%-15s   %-15s\n", activeKnights.get(i).getName(), monsters.get(i).getName()));
            }
            else {
                list.append(String.format("%-15s   %-15s\n", activeKnights.get(0).getName(), ""));
            }
        }
    }

    @Override
    public void printFortunes(List<Knight> activeKnights){
        StringBuilder fortunes = new StringBuilder();
        fortunes.append("For this quest, our knights drew the following fortunes!\n");

        for(Knight knight : activeKnights){
            fortunes.append(String.format("%s drew\n", knight.getName()));
            fortunes.append(knight.getActiveFortune() + "\n");
        }
        System.out.println(fortunes);
    }

    @Override
    public boolean checkContinue(){
        System.out.println("Would you like to continue on your quest (y/n)?");
        String nextLine = scnr.nextLine();
        if(nextLine.equals("y") || nextLine.equals("yes")){
            return true;

        }
        return false;

    }

    @Override
    public void printDefeated(){
        System.out.println("All active knights have been defeated!");
    }
}
