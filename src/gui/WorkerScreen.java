package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import user.Permission;

import java.io.IOException;

/**
 * Workers main screen
 *
 * @author Yaad Nahshon
 */
public class WorkerScreen extends MemberScreen
{

    public Button editProducts;

    @FXML
    @Override
    public void initialize()
    {
        welcomeMessage.setText("Welcome " + MainClient.personalName);

        if (MainClient.permission == Permission.WORKER)
        {
            editProducts.setDisable(true);
        }
    }

    @FXML
    protected void editButtonAction(ActionEvent actionEvent) throws IOException
    {
        // goto edit screen
        ((Node)actionEvent.getSource()).getScene().getWindow().hide();
        Stage editStage = new Stage();
        editStage.setTitle("Editor");
        editStage.setOnCloseRequest(e -> {
            e.consume();
            System.out.print("");
        });

        Pane root = FXMLLoader.load(getClass().getResource("fxml/edit.fxml"));
        Scene editScene = new Scene(root);
        editStage.setScene(editScene);
        editStage.show();
    }
}
