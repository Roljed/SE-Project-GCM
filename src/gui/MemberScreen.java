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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

/**
 *  Member screen for all sign up users of the system
 *
 * @author Yaad Nahshon
 */
public class MemberScreen implements ChatIF, Serializable
{
    protected ChatClient chat = MainClient.getChat();

    @FXML
    protected Label welcomeMessage;

    @FXML
    public void initialize()
    {
        welcomeMessage.setText("Welcome " + MainClient.personalName);
        Date thisDate = new Date();

        Alert alert = new Alert(Alert.AlertType.INFORMATION, "testing", ButtonType.OK);
        alert.showAndWait();

    }
    
    /**
     * This method handles with pushing the "Search" button on the screen
     *
     * @param actionEvent the event of mouse clicking on the "Search" button
     */

    @FXML
    protected void searchButton(ActionEvent actionEvent) throws IOException
    {
        ((Node)actionEvent.getSource()).getScene().getWindow().hide();
        Stage searchStage = new Stage();
        searchStage.setTitle("Search");
        searchStage.setOnCloseRequest(e -> {
            e.consume();
            System.out.print("");
        });

        Pane root = FXMLLoader.load(getClass().getResource("fxml/search.fxml"));
        Scene searchScene = new Scene(root);
        searchStage.setScene(searchScene);
        searchStage.show();
    }
    
    /**
     * This method handles with pushing the "View Purchased Items" button on the screen
     *
     * @param actionEvent the event of mouse clicking on the "View Purchased Items" button
     */

    @FXML
    private void viewPurchasesButton(ActionEvent actionEvent) throws IOException
    {
        ((Node)actionEvent.getSource()).getScene().getWindow().hide();
        Stage purchaseStage = new Stage();
        purchaseStage.setTitle("Member Purchases");
        purchaseStage.setOnCloseRequest(e -> {
            e.consume();
            System.out.print("");
        });

        Pane root = FXMLLoader.load(getClass().getResource("fxml/member-purchase.fxml"));
        Scene purchaseScene = new Scene(root);
        purchaseStage.setScene(purchaseScene);
        purchaseStage.show();
    }
    
    /**
     * This method handles with pushing the "View User Info" button on the screen
     *
     * @param actionEvent the event of mouse clicking on the "View User Info" button
     */

    @FXML
    protected void userInfoButton(ActionEvent actionEvent) throws IOException
    {
        ((Node)actionEvent.getSource()).getScene().getWindow().hide();
        Stage userInfoStage = new Stage();
        userInfoStage.setTitle("Private Info ");
        userInfoStage.setOnCloseRequest(e -> {
            e.consume();
            System.out.print("");
        });

        Pane root = FXMLLoader.load(getClass().getResource("fxml/user-info.fxml"));
        Scene userInfoScene = new Scene(root);
        userInfoStage.setScene(userInfoScene);
        userInfoStage.show();
    }
    
    /**
     * This method handles with pushing the "Logout" button on the screen
     *
     * @param actionEvent the event of mouse clicking on the "Logout" button
     */

    @FXML
    protected void logoutButton(ActionEvent actionEvent) throws IOException
    {
        chat.handleMessageFromClientUI("#SignOut " + MainClient.memberSignedIn.getUserName());
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
    
    /**
     * This method handles with pushing the "Exit" button on the screen
     *
     * @param actionEvent the event of mouse clicking on the "Exit" button
     */

    @FXML
    protected void exitButton(ActionEvent actionEvent)
    {
        ((Node)actionEvent.getSource()).getScene().getWindow().hide();
        System.exit(0);
    }

    @Override
    public void display(String message) {}

}
