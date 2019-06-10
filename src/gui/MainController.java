package gui;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController
{
    @FXML
    public void btn_Login(ActionEvent actionEvent) throws IOException
    {
        Parent loginView = FXMLLoader.load(getClass().getResource("fxml/login.fxml"));
        Scene loginScene = new Scene(loginView);

        Stage loginStage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        loginStage.setScene(loginScene);
        loginStage.show();
    }

    @FXML
    public void btn_Register(ActionEvent actionEvent) throws IOException
    {
        Parent registerView = FXMLLoader.load(getClass().getResource("fxml/register.fxml"));
        Scene registerScene = new Scene(registerView);

        Stage registerStage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        registerStage.setScene(registerScene);
        registerStage.show();
    }


    @FXML
    public void btn_SearchCatalog(ActionEvent actionEvent)
    {

    }

    @FXML
    public void btn_Exit(ActionEvent actionEvent)
    {
        Platform.exit();
    }
}
