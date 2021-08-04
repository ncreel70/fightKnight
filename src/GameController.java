public class GameController {
    private GameData data;
    private GameView view;
    private CombatEngine engine;

    public GameController(GameData data, GameView view, CombatEngine engine){
        this.data = data;
        this.view = view;
        this.engine = engine;
    }

    public void start(){
        view.splashScreen();

        while(processCommand(view.displayMainMenu())){
        }

        view.endGame();

    }
    protected boolean processCommand(String command) {
        switch (command) {
            case ("exit"):
            case ("bye"):
                return false;
            case ("ls"):
            case ("list all"):
                view.listKnights(data.getKnights());
                break;
            case ("list active"):
                view.listKnights(data.getActiveKnights());
                break;
            case("explore"):
            case("adventure"):
            case("quest"):
                engine.initialize();
                engine.runCombat();
                engine.clear();
                break;
            default:
                String knightSubstring = command.substring(command.indexOf(" ", command.indexOf("active")) + 1);
                if (command.contains("show")) {
                    System.out.println(data.getKnight(command.substring(command.indexOf(" ") + 1)));
                } else if (command.contains("set active")) {
                    Knight knight = data.getKnight(knightSubstring);
                    if (knight != null) {
                        if(!data.setActive(knight)){
                            view.setActiveFailed();
                        }
                    }
                } else if (command.contains("remove")) {
                    Knight knight = data.getKnight(knightSubstring);
                    if (knight != null) {
                        data.removeActive(knight);
                    }
                } else if (command.contains("save")) {

                    if (command.split(" ").length > 1) {
                        System.out.println("Save file: " + command.split(" ")[1]);
                        data.save(command.split(" ")[1]);
                    }
                    else{
                        System.out.println("Save file: " + "saveData.csv");
                        data.save("saveData.csv");
                    }

                } else {
                    view.printHelp();
                }
        }
        return true;
    }

    public static void main(String[] args){
        GameData testData = new CSVGameData("gamedata.csv","knights.csv");
        ConsoleView testView = new ConsoleView();
        CombatEngine testEngine = new CombatEngine(testData, testView);
        GameController test = new GameController(testData, testView, testEngine);

        test.start();
    }
}
