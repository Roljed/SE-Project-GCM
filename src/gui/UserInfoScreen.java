package gui;

import static gui.MainClient.memberSignedIn;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
import user.member.Member;

/**
 * User Info screen
 *
 * @author Avi Ayeli
 */

public class UserInfoScreen implements ChatIF, Serializable
{

    private String host = MainClient.getHost();
    private int port = MainClient.getPort();
    private ChatClient chat = MainClient.getChat();

    @FXML // fx:id="UpadatePassword"
    private TextField UpadatePassword; // Value injected by FXMLLoader

    @FXML // fx:id="UpdateUsername"
    private TextField UpdateUsername; // Value injected by FXMLLoader

    @FXML // fx:id="Email"
    private Label Email; // Value injected by FXMLLoader

    @FXML // fx:id="UpdatePhoneNumber"
    private TextField UpdatePhoneNumber; // Value injected by FXMLLoader

    @FXML // fx:id="Username"
    private Label Username; // Value injected by FXMLLoader

    @FXML // fx:id="UpadateFullName"
    private TextField UpadateFullName; // Value injected by FXMLLoader

    @FXML // fx:id="FullName"
    private Label FullName; // Value injected by FXMLLoader

    @FXML // fx:id="PhoneNumber"
    private Label PhoneNumber; // Value injected by FXMLLoader

    @FXML // fx:id="UpdateEmail"
    private TextField UpdateEmail; // Value injected by FXMLLoader

    @FXML // fx:id="messageLabel"
    private Label messageLabel; // Value injected by FXMLLoader

    @FXML // fx:id="Password"
    private Label Password; // Value injected by FXMLLoader
    
    /**
     * This method initializes the window paraneters
     *
     * @param prinaryStage the window to be opened
     */


    public void start(Stage primaryStage) throws Exception
    {
        primaryStage.setTitle("User Info");
        primaryStage.setOnCloseRequest(e -> {
            e.consume();
            System.out.print("");
        });

        FXMLLoader loader = new FXMLLoader();
        Pane root = loader.load(getClass().getResource("fxml/user-info.fxml").openStream());
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    /**
     * This method initializes the parameteres on the window, so it will be shown to the user on opening
     */
    
    @FXML
    public void initialize() throws IOException
    {
        Username.setText(memberSignedIn.getUserName());
        Password.setText(memberSignedIn.getPassword());
        FullName.setText(memberSignedIn.getPersonalName());
        PhoneNumber.setText(memberSignedIn.getPhoneNumberByString());
        Email.setText(memberSignedIn.getEmail());
    }
    
    /**
     * This method handles with pushing the "Update" button on the screen
     *
     * @param actionEvent the event of mouse clicking on the "Update" button
     */

    @FXML
    void btn_Update(ActionEvent event) throws IOException {
        if (UpadatePassword.getText().equals("") && UpdateUsername.getText().equals("") &&
                UpdatePhoneNumber.getText().equals("") && UpadateFullName.getText().equals("") && UpdateEmail.getText().equals(""))
            messageLabel.setText("There's nothing to update!");

        else {
            if (this.chat == null)
            {
                this.chat = new ChatClient(host, port, this);
            }

            if (!UpadatePassword.getText().isEmpty())
                memberSignedIn.setPassword(UpadatePassword.getText());
            if (!UpdateUsername.getText().isEmpty())
                memberSignedIn.setUserName(UpdateUsername.getText());
            if (!UpdatePhoneNumber.getText().isEmpty())
                memberSignedIn.setPhoneNumber(UpdatePhoneNumber.getText());
            if (!UpadateFullName.getText().isEmpty())
                memberSignedIn.setPersonalName(UpadateFullName.getText());
            if (!UpdateEmail.getText().isEmpty())
                memberSignedIn.setEmail(UpdateEmail.getText());
            List<Member> update= new ArrayList<>();
            update.add(memberSignedIn);
            try {
                chat.sendToServer(update);
            }
            catch(IOException ex) {}
            messageLabel.setText("Updated successfully!");

        }
    }
    
    /**
     * This method handles with pushing the "Back" button on the screen
     *
     * @param actionEvent the event of mouse clicking on the "Back" button
     */

    @FXML
    void btn_Back(ActionEvent event)  throws IOException{
        ((Node)event.getSource()).getScene().getWindow().hide();

        Stage backScreen = new Stage();
        FXMLLoader loader = new FXMLLoader();
        Pane root;
        switch(MainClient.permission) {
            case USER:
                backScreen.setTitle("Member Page");
                root = loader.load(getClass().getResource("fxml/member-screen.fxml").openStream());
                break;
            case WORKER:
            case CONTENT_WORKER:
                backScreen.setTitle("Member Page");
                root = loader.load(getClass().getResource("fxml/worker-screen.fxml").openStream());
                break;
            case COMPANY_MANAGER:
            case CONTENT_MANAGER:
                backScreen.setTitle("Manager Page");
                root = loader.load(getClass().getResource("fxml/manager-screen.fxml").openStream());
                break;
            case MEMBER:
                backScreen.setTitle("Member Page");
                root = loader.load(getClass().getResource("fxml/member-screen.fxml").openStream());
                break;
            default:
                backScreen.setTitle("GCM Main Screen");
                root = loader.load(getClass().getResource("fxml/main").openStream());
                break;
        }
        backScreen.setOnCloseRequest(e ->
        {
            e.consume();
            System.out.print("");
        });

        Scene mainScreenScene = new Scene(root);
        backScreen.setScene(mainScreenScene);
        backScreen.show();
    }


    @Override
    public void display(String message) {
    }
}
