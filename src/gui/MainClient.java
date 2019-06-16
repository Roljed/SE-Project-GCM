package gui;

import chat.ChatClient;
import chat.common.ChatIF;
import command.catalog.Catalog;
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
import user.Permission;
import user.member.MemberCard;

import java.net.URL;
import java.util.ResourceBundle;


/**
 *  Main class that implements the client side
 * @author Yaad Nahshon
 */
public class MainClient extends Application implements Initializable, ChatIF
{
    public static String personalName;
    public static Permission permission;
    public static boolean memberReportActivity;
    private static ChatClient chat = null;
    final public static int DEFAULT_PORT = 5555;
    private static String host = "";

//    public static Stack<Scene> sceneStack = new Stack<>(); TODO after all scenes are build
    public static Object result = null;    // holds server return message
    static MemberCard memberSignedIn = null;
    public static Catalog catalog = null;

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

    public static int getPort()
    {
        return DEFAULT_PORT;
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
        Pane root = FXMLLoader.load(getClass().getResource("fxml/client-connection.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("GCM Main");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void submitButton(ActionEvent actionEvent) throws Exception
    {
        host = serverIPTextField.getText();
        chat = new ChatClient(host, DEFAULT_PORT, this);

        ((Node)actionEvent.getSource()).getScene().getWindow().hide();
        Stage mainScreenStage = new Stage();
        mainScreenStage.setTitle("GCM Main Screen");
        mainScreenStage.setOnCloseRequest(e ->
        {
            e.consume();
            System.out.print("");
        });

        Pane root = FXMLLoader.load(getClass().getResource("fxml/main.fxml"));
        Scene mainScreenScene = new Scene(root);
        mainScreenStage.setScene(mainScreenScene);
        mainScreenStage.show();
    }

    @Override
    public void display(String message) {}
}
