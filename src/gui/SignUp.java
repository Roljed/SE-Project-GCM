package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 *  TODO explain class
 * @author Yaad Nahshon
 */
public class SignUp
{
    @FXML
    public void btn_Back(ActionEvent actionEvent) throws IOException
    {
        Parent mainView = FXMLLoader.load(getClass().getResource("fxml/main.fxml"));
        Scene mainScene = new Scene(mainView);

        Stage mainStage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        mainStage.setScene(mainScene);
        mainStage.show();
    }
}
