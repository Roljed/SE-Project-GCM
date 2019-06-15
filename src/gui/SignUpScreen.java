package gui;

import chat.ChatClient;
import chat.common.ChatIF;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import server.ClientServerStatus;
import user.User;

import java.io.IOException;
import java.io.Serializable;

/**
 *  For user, any user of GCM App, registration
 *
 * @author Yaad Nahshon
 */
public class SignUpScreen implements ChatIF, Serializable
{
    private String host = MainClient.getHost();
    private int port = MainClient.getPort();
    private ChatClient chat = MainClient.getChat();

    public TextField usernameText;
    public TextField passwordText;
    public TextField nameText;
    public TextField phoneText;
    public TextField emailText;
    public Label messageLabel;

    @FXML
    public void submitButton(ActionEvent actionEvent) throws IOException
    {
        if (usernameText.getText().isEmpty() || passwordText.getText().isEmpty() || nameText.getText().isEmpty() ||
            phoneText.getText().isEmpty() || emailText.getText().isEmpty())
        {
            messageLabel.setText("Please fill again, some fields are empty");
        }
        else {
            MainClient.result = null;

            if (this.chat == null)
            {
                this.chat = new ChatClient(host, port, this);
            }

            User user = new User(chat);
            user.signUp(chat, nameText.getText(), usernameText.getText(), passwordText.getText(), Integer.parseInt(phoneText.getText()), emailText.getText());

            while (MainClient.result == null)
            {
                System.out.print("");
            }
            Object res = MainClient.result;

            if (res instanceof Boolean)
            {
                boolean signedUp = ((boolean) res);
                if (signedUp)
                {
                    ((Node)actionEvent.getSource()).getScene().getWindow().hide();
                    Stage signInStage = new Stage();

                    signInStage.setTitle("Sign In");
                    signInStage.setOnCloseRequest(e -> {
                        e.consume();
                        System.out.print("SignUp screen closed. Moving to Sign In screen.");
                    });

                    Pane root = FXMLLoader.load(getClass().getResource("fxml/sign-in.fxml"));
                    Scene signInScene = new Scene(root);
                    signInStage.setScene(signInScene);
                    signInStage.show();
                }
                else {
                    messageLabel.setText("Try different username");
                }
            }
            else {
                messageLabel.setText("Server error, please try again");
            }
        }
    }

    @FXML
    public void backButton(ActionEvent actionEvent) throws IOException
    {
        Parent mainView = FXMLLoader.load(getClass().getResource("fxml/main.fxml"));
        Scene mainScene = new Scene(mainView);

        Stage mainStage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        mainStage.setScene(mainScene);
        mainStage.show();
    }

    @Override
    public void display(String message) {

    }
}
