package gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import server.ConnectionToDatabase;
import server.EchoServer;

import java.io.IOException;

/**
 *  Main class that implements the server side
 * @author Yaad Nahshon
 */
public class MainServer extends Application
{

    final public static int DEFAULT_PORT = 5555;
    public static EchoServer server = new EchoServer(DEFAULT_PORT);

    public TextField ipText;
    public TextField databaseUsernameText;
    public TextField databaseNameText;
    public TextField portText;
    public TextField databasePasswordText;
    public Button connectButton;
    public Button disconnectButton;
    public Button exitButton;
    public Label messageLabel;

    public static void main (String[] args)
    {
        int port = 0; //Port to listen on

        try
        {
            port = Integer.parseInt(args[0]); //Get port from command line
        }
        catch(Throwable t)
        {
            port = DEFAULT_PORT; //Set port to 5555
        }
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception
    {
        primaryStage.setTitle("GCM Server");
        primaryStage.setOnCloseRequest(e -> {
            e.consume();
            System.out.print("");
        });

        FXMLLoader loader = new FXMLLoader();
        Pane root = loader.load(getClass().getResource("fxml/server-connection.fxml").openStream());
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // TODO DELETE before submission
    public void initialize()
    {
        ipText.setText("127.0.0.1");
        portText.setText("5555");
        databaseNameText.setText("xdhLgvyRnN");
        databaseUsernameText.setText("xdhLgvyRnN");
        databasePasswordText.setText("uNtE7bXJvV");
    }

    public void connectAction(ActionEvent actionEvent)
    {
        connectButton.setDisable(true);
        disconnectButton.setDisable(false);
        exitButton.setDisable(false);
        ipText.setDisable(true);
        portText.setDisable(true);
        databaseNameText.setDisable(true);
        databaseUsernameText.setDisable(true);
        databasePasswordText.setDisable(true);

        server.setPort(Integer.parseInt(portText.getText()));
        ConnectionToDatabase.setDatabaseName(databaseNameText.getText());
        ConnectionToDatabase.setDatabaseUsername(databaseUsernameText.getText());
        ConnectionToDatabase.setDatabasePassword(databasePasswordText.getText());

        try {
            server.listen();
        } catch (IOException e) {
            this.messageLabel.setText("ERROR. Could not listen for clients.");
            e.printStackTrace();
        }

        try {
            if (server.isListening())
            {
                this.messageLabel.setText("Server Is Listening On Port " + server.getPort());
            }
            else
            {
                this.messageLabel.setText("Server Not Connected");
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void disconnectAction(ActionEvent actionEvent)
    {
        disconnectButton.setDisable(true);
        connectButton.setDisable(false);
        ipText.setDisable(false);
        portText.setDisable(false);
        databaseNameText.setDisable(false);
        databaseUsernameText.setDisable(false);
        databasePasswordText.setDisable(false);

        try {
            server.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        messageLabel.setText("Server Disconnected");

    }

    public void exitAction(ActionEvent actionEvent)
    {
        disconnectButton.setDisable(true);
        connectButton.setDisable(true);
        ipText.setDisable(true);
        portText.setDisable(true);
        databaseNameText.setDisable(true);
        databaseUsernameText.setDisable(true);
        databasePasswordText.setDisable(true);

        try {
            server.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ((Node)actionEvent.getSource()).getScene().getWindow().hide();
        System.exit(0);
    }
}
