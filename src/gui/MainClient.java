package gui;

import client.ClientConsole;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;


/**
 *  Main class that implements the client side
 * @author Yaad Nahshon
 */
public class MainClient extends Application implements Initializable
{
    final public static int DEFAULT_PORT = 5555;
    private static String host = "";

    @FXML
    public TextField serverIPTextField;

    public static void main(String[] args)
    {

        int port = 0;  //The port number

        try
        {
            host = args[0];
        }
        catch(ArrayIndexOutOfBoundsException e)
        {
            host = "localhost";
        }
        System.out.println("Host: " + host);
        //  chat.accept();  //Wait for console data
        launch(args);
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1)
    {
        serverIPTextField.setText(host);
    }


    public static String getHost()
    {
        return host;
    }

    @Override
     public void start(Stage primaryStage) throws Exception
    {
        primaryStage.setResizable(false);
        primaryStage.setTitle("Connection Configuration");
        Pane root = FXMLLoader.load(getClass().getResource("fxml/client-connection.fxml"));    //TODO change root fxml
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();

//        primaryStage.setTitle("GCM Main");
//        primaryStage.setScene(new Scene(root));
//        primaryStage.show();
    }

    public void submitButton(ActionEvent actionEvent) throws Exception
    {
        host = serverIPTextField.getText();
        ClientConsole chat = new ClientConsole(host, DEFAULT_PORT);

        ((Node)actionEvent.getSource()).getScene().getWindow().hide();
        Stage primaryStage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        primaryStage.setTitle("GCM Main Screen");
        primaryStage.setOnCloseRequest(e ->
        {
            e.consume();
            System.out.print("");
        });

        Pane root = loader.load(getClass().getResource("/fxml/main.fxml").openStream());
//        MainController mainController = (MainController) loader.getController();
        Scene mainScene = new Scene(root);
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }
}
