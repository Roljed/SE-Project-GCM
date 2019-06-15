package gui;

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
import user.member.MemberCard;

/**
 * User Info screen
 *
 * @author Avi Ayeli
 */

public class UserInfoScreen implements ChatIF, Serializable
{

    static private final int SERVER_PORT = 5555;
    private ChatClient chat = MainClient.getChat();
    private MemberCard memberCard;

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

    public UserInfoScreen(MemberCard memberCard) {
        this.memberCard=memberCard;
        Username.setText(memberCard.getNameUser());
        Password.setText(memberCard.getPassword());
        FullName.setText(memberCard.getNamePersonal());
        PhoneNumber.setText(memberCard.getPhoneNumberByString());
        Email.setText(memberCard.getEmail());
    }

    @FXML
    void btn_Update(ActionEvent event) {
        if (UpadatePassword.getText().isEmpty() && UpdateUsername.getText().isEmpty() &&
                UpdatePhoneNumber.getText().isEmpty() && UpadateFullName.getText().isEmpty() && UpdateEmail.getText().isEmpty())
            messageLabel.setText("There's nothing to update!");
        else {
            if (!UpadatePassword.getText().isEmpty())
                memberCard.setPassword(UpadatePassword.getText());
            if (!UpdateUsername.getText().isEmpty())
                memberCard.setnameUser(UpdateUsername.getText());
            if (!UpdatePhoneNumber.getText().isEmpty())
                memberCard.setPhoneNumber(UpdatePhoneNumber.getText());
            if (!UpadateFullName.getText().isEmpty())
                memberCard.setNamePersonal(UpadateFullName.getText());
            if (!UpdateEmail.getText().isEmpty())
                memberCard.setEmail(UpdateEmail.getText());
            List<MemberCard> update= new ArrayList<>();
            update.add(memberCard);
            try {
                chat.sendToServer(update);
            }
            catch(IOException ex) {}
            boolean res = (boolean) chat.receiveObjectFromServer();
            if(res)
                messageLabel.setText("Updated successfully!");
            else
                messageLabel.setText("Something went worng, please try again");
        }
    }

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
        // TODO Auto-generated method stub

    }
}