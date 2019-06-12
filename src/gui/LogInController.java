package gui;

import client.ChatClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import user.User;
import user.UserStatus;

import java.io.IOException;

public class LogInController
{
    public TextField txtUsername;
    public TextField txtPassword;

    @FXML
    public void btn_Submit(ActionEvent actionEvent) throws IOException
    {
        String u = txtUsername.getText();
        String p = txtPassword.getText();
        UserStatus back = UserStatus.NOT_EXIST;

//        User user = new User(new ChatClient());
//        UserStatus back = user.signIn(txtUsername.getText(), txtPassword.getText());

        switch (back)
        {
            case NOT_EXIST:
                Alert alert = new Alert(Alert.AlertType.ERROR, "User not exist", ButtonType.OK);
                alert.showAndWait();

                break;

                // TODO Sign In error screen, must to Sing Up
            case MEMBER:
                // TODO Member screen
                break;
            case CONTENT_WORKER:
            case WORKER:
                // TODO simple screen
                break;
            case MANAGER:
            case CONTENT_MANAGER:
                // TODO manager screen
                break;
        }
    }

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
