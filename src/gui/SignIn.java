package gui;

import chat.ChatClient;
import chat.common.ChatIF;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import server.ClientServerStatus;
import user.User;
import user.member.MemberCard;

import java.io.IOException;

/**
 *  TODO explain class
 * @author Yaad Nahshon
 */
public class SignIn implements ChatIF
{
    static private final int SERVER_PORT = 5555;
    ChatClient chat = null;

    public TextField usernameText;
    public TextField passwordText;
    public Label messageLabel;

    @FXML
    public void submitButton(ActionEvent actionEvent) throws IOException
    {
        if (usernameText.getText().isEmpty() || passwordText.getText().isEmpty()) {
            messageLabel.setText("Please enter Username and Password");
        }
        else {
            String username = usernameText.getText();
            String password = passwordText.getText();

            if (this.chat == null)
            {
                this.chat = new ChatClient(MainClient.getHost(),SERVER_PORT, this);
            }
            User user = new User(chat);
            Object res = user.signIn(username, password);
            messageLabel.setText("Login information sent to Server.");
            if (res instanceof ClientServerStatus)
            {
                ClientServerStatus clientServerStatus = ((ClientServerStatus) res);
                switch (clientServerStatus)
                {
                    case WRONG_USERNAME_OR_PASSWORD:
                        messageLabel.setText("Wrong username or password.");
                        break;
                    case CONNECTED:
                        messageLabel.setText("User already connected");
                        break;

                }
                usernameText.setPromptText("Enter Username");
                passwordText.setPromptText("Enter Password");
            }

            else if (res instanceof MemberCard)
            {
                MemberCard member = ((MemberCard) res);
                FXMLLoader loader = new FXMLLoader();
                switch (member.getPermission())
                {
                    case MEMBER:
                        // TODO Member screen
                        ((Node)actionEvent.getSource()).getScene().getWindow().hide();
                        Stage memberStage = new Stage();
                        memberStage.setTitle("Member Page");
                        memberStage.setOnCloseRequest(e -> {
                            e.consume();
                            System.out.print("");
                        });
                        Pane rootMember = loader.load(getClass().getResource("fxml/member-screen.fxml").openStream());
                        Scene memberScene = new Scene(rootMember);
                        memberStage.setScene(memberScene);
                        memberStage.show();
                        break;

                    case CONTENT_WORKER:
                    case WORKER:
                        // TODO Worker screen
                        ((Node)actionEvent.getSource()).getScene().getWindow().hide();
                        Stage workerStage = new Stage();
                        workerStage.setTitle("Worker Page");
                        workerStage.setOnCloseRequest(e -> {
                            e.consume();
                            System.out.print("");
                        });
                        Pane rootWorker = loader.load(getClass().getResource("fxml/worker-screen.fxml").openStream());
                        Scene workerScene = new Scene(rootWorker);
                        workerStage.setScene(workerScene);
                        workerStage.show();
                        break;

                    case MANAGER:
                    case CONTENT_MANAGER:
                        // TODO Manager screen
                        ((Node)actionEvent.getSource()).getScene().getWindow().hide();
                        Stage managerStage = new Stage();
                        managerStage.setTitle("Manager Page");
                        managerStage.setOnCloseRequest(e -> {
                            e.consume();
                            System.out.print("");
                        });
                        Pane rootManager = loader.load(getClass().getResource("fxml/manager-screen.fxml").openStream());
                        Scene managerScene = new Scene(rootManager);
                        managerStage.setScene(managerScene);
                        managerStage.show();
                        break;
                }
            }
        }
    }

    @FXML
    public void backButton(ActionEvent actionEvent) throws IOException
    {
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
