package gui;

import chat.common.ChatIF;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.Serializable;

/**
 *  Member screen for all sign up users of the system
 *
 * @author Yaad Nahshon
 */
public class MemberScreen implements ChatIF, Serializable
{
    @FXML
    protected Label welcomeMessage;

    @FXML
    public void initialize()
    {
        welcomeMessage.setText("Welcome " + MainClient.personalName);
    }

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

    @FXML
    protected void logoutButton(ActionEvent actionEvent) throws IOException
    {
        ((Node)actionEvent.getSource()).getScene().getWindow().hide();
        Stage signInStage = new Stage();
        FXMLLoader loader = new FXMLLoader();

        signInStage.setTitle("Sign In");
        signInStage.setOnCloseRequest(e -> {
            e.consume();
            System.out.print("Main screen closed. Moving to Sign In screen.");
        });

        Pane root = FXMLLoader.load(getClass().getResource("fxml/sign-in.fxml"));
        Scene signInScene = new Scene(root);
        signInStage.setScene(signInScene);
        signInStage.show();
    }

    @FXML
    protected void exitButton(ActionEvent actionEvent)
    {
        ((Node)actionEvent.getSource()).getScene().getWindow().hide();
        System.exit(0);
    }

    @Override
    public void display(String message) {}

}
