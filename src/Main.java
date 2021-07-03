import javafx.application.Application;
import javafx.stage.Stage;
import utility.MyStagesShower;
import utility.enums.WPATH;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        new MyStagesShower().showFXML_CenterAndTransparent(WPATH.login);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
