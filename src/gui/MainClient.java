package gui;

import chat.ChatClient;
import chat.ClientConsole;
import chat.common.ChatIF;
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
public class MainClient extends Application implements Initializable, ChatIF
{
    private static ChatClient chat = null;
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
        //  m_chat.accept();  //Wait for console data
        launch(args);
    }

    public static ChatClient getChat()
    {
        return chat;
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
        this.chat = new ChatClient(host, DEFAULT_PORT, this);

        ((Node)actionEvent.getSource()).getScene().getWindow().hide();
        Stage mainScreenStage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        mainScreenStage.setTitle("GCM Main Screen");
        mainScreenStage.setOnCloseRequest(e ->
        {
            e.consume();
            System.out.print("");
        });

        Pane root = loader.load(getClass().getResource("fxml/main.fxml").openStream());
        Scene mainScreenScene = new Scene(root);
        mainScreenStage.setScene(mainScreenScene);
        mainScreenStage.show();
    }

    @Override
    public void display(String message) {}
}
