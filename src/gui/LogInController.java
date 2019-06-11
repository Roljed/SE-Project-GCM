package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import user.UserStatus;
import user.User;

import java.io.IOException;

public class LogInController
{
    public TextField txtUsername;
    public TextField txtPassword;
    private User user;

    @FXML
    public void btn_Submit(ActionEvent actionEvent)
    {
        UserStatus back = user.signIn(txtUsername.getText(), txtPassword.getText());

        switch (back)
        {
            case USER:
                System.out.println("User Submit");
                // TODO Sign In error screen, must to Sing Up
            case MEMBER:
            case WORKER:
                // TODO simple screen
            case CONTENT_WORKER:
                // TODO edit screen
            case MANAGER:
            case CONTENT_MANAGER:
                // TODO manager screen
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
