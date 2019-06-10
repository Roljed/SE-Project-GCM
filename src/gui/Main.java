package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application
{
    private Stage activeWindow;

    @Override
     public void start(Stage primaryStage) throws Exception
    {
        activeWindow = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("fxml/main.fxml"));

        activeWindow.setTitle("GCM Main");
        activeWindow.setScene(new Scene(root));
        activeWindow.show();


//        URL mainUrl =getClass().getResource("MainScreen.fxml");
//        AnchorPane mainPane = FXMLLoader.load(mainUrl);
//        Scene mainScene = new Scene(mainPane);
//
//        primaryStage.setTitle("Main");
//        primaryStage.setScene(mainScene);
//        primaryStage.show();

//        VBox vbox = new VBox();
//
//        MainController mainController = new MainController();
//        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("main.fxml"));
//        fxmlLoader.setRoot(vbox);
//        fxmlLoader.setController(fxmlLoader);
//        fxmlLoader.load();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
