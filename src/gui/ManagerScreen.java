/**
 * Sample Skeleton for 'manager-screen.fxml' Controller Class
 */

package gui;

import java.io.IOException;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


/**
 *  Manager class for all manager actions
 *
 * @author Yaad Nahshon
 */
public class ManagerScreen extends WorkerScreen{

    @FXML // fx:id="welcomeMessage"
    private Label welcomeMessage; // Value injected by FXMLLoader

    @FXML
    public void approvalsButton(ActionEvent actionEvent) throws IOException
    {
        // goto Inbox screen
        ((Node)actionEvent.getSource()).getScene().getWindow().hide();
        Stage approvalStage = new Stage();
        approvalStage.setTitle("Approve");
        approvalStage.setOnCloseRequest(e -> {
            e.consume();
            System.out.print("");
        });

        Pane root = FXMLLoader.load(getClass().getResource("fxml/inbox.fxml"));
        Scene approvalScene = new Scene(root);
        approvalStage.setScene(approvalScene);
        approvalStage.show();
    }

    @FXML
    public void purchaseInfoButton(ActionEvent actionEvent) throws IOException
    {
        MainClient.memberReportActivity = true;
        ((Node)actionEvent.getSource()).getScene().getWindow().hide();
        Stage approvalStage = new Stage();
        approvalStage.setTitle("ClientsPurchases");
        approvalStage.setOnCloseRequest(e -> {
            e.consume();
            System.out.print("");
        });

        Pane root = FXMLLoader.load(getClass().getResource("fxml/member-purchase.fxml"));
        Scene approvalScene = new Scene(root);
        approvalStage.setScene(approvalScene);
        approvalStage.show();
    }

}
