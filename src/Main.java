import GUI.SetupBoard;
import game.AI.AiPlayer;
import game.AI.Evaluation;
import game.Game;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("GUI/sample.fxml"));
        primaryStage.setTitle("Hello World");
        SetupBoard setup = new SetupBoard();
        Game game = new Game();
        setup.setup(game);
        primaryStage.setScene(new Scene(setup.getTotalplayboard(), setup.getDimensions()[0], setup.getDimensions()[1]));
        primaryStage.show();
        Evaluation ev = new Evaluation();
        AiPlayer aiPlayer = new AiPlayer();
        aiPlayer.Move(game);

    }
    public static void main(String[] args) {
        launch(args);
    }

    /*
    public static void main(String[] args) {
        Game game = new Game();
        SetupBoard setup = new SetupBoard();
        setup.setup(game);
        System.out.printf(game.getBoard().toString());
    }
    */
}
