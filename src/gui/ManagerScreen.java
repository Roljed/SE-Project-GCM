package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Managers main screen
 *
 * @author Yaad Nahshon
 */
public class ManagerScreen extends WorkerScreen
{

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
//        MainClient.memberReportActivity = 1;
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
