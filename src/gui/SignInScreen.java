package gui;

import chat.ChatClient;
import chat.common.ChatIF;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import server.ClientServerStatus;
import user.User;
import user.manager.CompanyManager;
import user.manager.ContentManager;
import user.member.MemberCard;
import user.worker.ContentWorker;
import user.worker.Worker;

import java.io.IOException;
import java.io.Serializable;

import static gui.MainClient.memberSignedIn;

/**
 *  TODO explain class
 * @author Yaad Nahshon
 */
public class SignInScreen implements ChatIF, Serializable
{
    private String host = MainClient.getHost();
    private int port = MainClient.getPort();
    private ChatClient chat = MainClient.getChat();

    public TextField usernameText;
    public TextField passwordText;

    @FXML
    public Label messageLabel;

    @FXML
    private void initialize()
    {
        messageLabel.setText("");
    }


    @FXML
    public void submitButton(ActionEvent actionEvent) throws IOException
    {
        if (usernameText.getText().isEmpty() || passwordText.getText().isEmpty()) {
            messageLabel.setText("Please enter Username and Password");
        }
        else {
            String username = usernameText.getText();
            String password = passwordText.getText();

            MainClient.result = null;

            if (this.chat == null)
            {
                this.chat = new ChatClient(host, port, this);
            }

            User user = new User(chat);
            user.signIn(chat, username, password);
            messageLabel.setText("Login information sent to Server.");
            while (MainClient.result == null)
            {
                System.out.print("");
            }
            Object res = MainClient.result;

            if (res instanceof ClientServerStatus)
            {
                ClientServerStatus clientServerStatus = ((ClientServerStatus) res);
                switch (clientServerStatus)
                {
                    case WRONG_USERNAME_OR_PASSWORD:
                        messageLabel.setText("Wrong Username or Password.");
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Wrong Username or Password." , ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
                        alert.showAndWait();

                        if (alert.getResult() == ButtonType.YES) {
                            System.out.println("YES");
                        }
                        break;
                    case CONNECTED:
                        messageLabel.setText("User already connected");
                        break;

                }
//                usernameText.clear();
//                usernameText.setPromptText("Enter Username");
//                passwordText.clear();
//                passwordText.setPromptText("Enter Password");
            }

            else if (res instanceof MemberCard)
            {
                memberSignedIn = ((MemberCard) res);
                MainClient.permission = memberSignedIn.getPermission();
                MainClient.personalName = memberSignedIn.getPersonalName();

                switch (MainClient.permission)
                {
                    case USER:
                        // Goto Search screen
                        ((Node)actionEvent.getSource()).getScene().getWindow().hide();
                        Stage searchStage = new Stage();
                        searchStage.setTitle("Member Page");
                        searchStage.setOnCloseRequest(e -> {
                            e.consume();
                            System.out.print("");
                        });
                        Pane root = FXMLLoader.load(getClass().getResource("fxml/search.fxml"));
                        Scene searchScene = new Scene(root);
                        searchStage.setScene(searchScene);
                        searchStage.show();
                        break;

                    case MEMBER:
                        // Goto Member Menu screen
                        ((Node)actionEvent.getSource()).getScene().getWindow().hide();
                        Stage memberStage = new Stage();
                        memberStage.setTitle("Member Page");
                        memberStage.setOnCloseRequest(e -> {
                            e.consume();
                            System.out.print("");
                        });
                        Pane rootMember = FXMLLoader.load(getClass().getResource("fxml/member-screen.fxml"));
                        Scene memberScene = new Scene(rootMember);
                        memberStage.setScene(memberScene);
                        memberStage.show();
                        break;

                    case WORKER:
                        // Goto Worker Menu screen
                        Worker worker = new Worker(memberSignedIn.getMemberIDByString(), memberSignedIn.getPersonalName(),
                                memberSignedIn.getUserName(), memberSignedIn.getPassword(),
                                memberSignedIn.getPhoneNumberByString(), memberSignedIn.getEmail(), null,
                                memberSignedIn.getPermissionByString());
                        MainClient.memberSignedIn = worker;
                        workerPage(actionEvent);
                        break;

                    case CONTENT_WORKER:
                        ContentWorker contentWorker = new ContentWorker(memberSignedIn.getMemberIDByString(), memberSignedIn.getPersonalName(),
                            memberSignedIn.getUserName(), memberSignedIn.getPassword(),
                            memberSignedIn.getPhoneNumberByString(), memberSignedIn.getEmail(), null,
                            memberSignedIn.getPermissionByString());
                        MainClient.memberSignedIn = contentWorker;
                        workerPage(actionEvent);
                        break;

                    case CONTENT_MANAGER:
                        ContentManager contentManager = new ContentManager(memberSignedIn.getMemberIDByString(), memberSignedIn.getPersonalName(),
                                memberSignedIn.getUserName(), memberSignedIn.getPassword(),
                                memberSignedIn.getPhoneNumberByString(), memberSignedIn.getEmail(), null,
                                memberSignedIn.getPermissionByString());
                        MainClient.memberSignedIn = contentManager;
                        managerPage(actionEvent);
                        break;

                    case COMPANY_MANAGER:
                        CompanyManager companyManager = new CompanyManager(memberSignedIn.getMemberIDByString(), memberSignedIn.getPersonalName(),
                                memberSignedIn.getUserName(), memberSignedIn.getPassword(),
                                memberSignedIn.getPhoneNumberByString(), memberSignedIn.getEmail(), null,
                                memberSignedIn.getPermissionByString());
                        MainClient.memberSignedIn = companyManager;
                        managerPage(actionEvent);
                }
            }

            else
            {
                System.out.println("Error! Returned message from server didn't matched.");
                messageLabel.setText("Server Error!");
            }
        }
    }

    private void workerPage(ActionEvent actionEvent) throws IOException
    {
        ((Node)actionEvent.getSource()).getScene().getWindow().hide();
        Stage workerStage = new Stage();
        workerStage.setTitle("Worker Page");
        workerStage.setOnCloseRequest(e -> {
            e.consume();
            System.out.print("");
        });
        Pane rootWorker = FXMLLoader.load(getClass().getResource("fxml/worker-screen.fxml"));
        Scene workerScene = new Scene(rootWorker);
        workerStage.setScene(workerScene);
        workerStage.show();
    }

    private void managerPage(ActionEvent actionEvent) throws IOException
    {
        ((Node)actionEvent.getSource()).getScene().getWindow().hide();
        Stage managerStage = new Stage();
        managerStage.setTitle("Manager Page");
        managerStage.setOnCloseRequest(e -> {
            e.consume();
            System.out.print("");
        });
        Pane rootManager = FXMLLoader.load(getClass().getResource("fxml/manager-screen.fxml"));
        Scene managerScene = new Scene(rootManager);
        managerStage.setScene(managerScene);
        managerStage.show();
    }

    @FXML
    public void backButton(ActionEvent actionEvent) throws IOException
    {
        // TODO after all scenes are build
//        Stage mainScreenStage = new Stage();
//        mainScreenStage.setScene(MainClient.sceneStack.peek());
//        mainScreenStage.show();

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
