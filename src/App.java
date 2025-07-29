import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("View/Main.fxml"));

        Parent root = loader.load();
        primaryStage.setTitle("Modelo Rutherford");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }

}
