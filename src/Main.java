public class Main {
    public static void main(String[] args){
        String dataFile = "gamedata.csv";
        String saveFile = "saveData.csv";


        if(args.length == 1){
            if(args[0].contains("--data")){
                dataFile = args[0].substring(args[0].indexOf("=") + 1);
            }
            else{
                saveFile = args[0];
            }
        }
        if(args.length == 2){
            dataFile = args[0].substring(args[0].indexOf("=") + 1);
            saveFile = args[1];
        }

        GameData data = new CSVGameData(dataFile, saveFile);
        GameView view = new ConsoleView();
        CombatEngine engine = new CombatEngine(data, view);
        GameController controller = new GameController(data, view, engine);
        controller.start();
    }

}
