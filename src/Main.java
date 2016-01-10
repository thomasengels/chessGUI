import GUI.SetupBoardRefactor;
import game.Game;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application{
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("GUI/sample.fxml"));
        primaryStage.setTitle("Chess Glenn & Thomas");
        primaryStage.getIcons().add(new Image("GUI/Chess-Game.png"));
        SetupBoardRefactor setup = new SetupBoardRefactor();
        Game game = new Game();
        game.getAiPlayer().addObserver(setup);
        setup.setup(game);
        primaryStage.setScene(new Scene(setup.getTotalplayboard(), setup.getDimensions()[0], setup.getDimensions()[1]));
        primaryStage.show();
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
