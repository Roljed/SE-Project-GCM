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
 *  Main screen view of client side connection
 *  Can do Sign In, Sign Up, and View Catalog - which move the client to Search screen
 * @author Yaad Nahshon
 */
public class MainScreen
{
    @FXML
    public void signInButton(ActionEvent actionEvent) throws IOException
    {
        ((Node)actionEvent.getSource()).getScene().getWindow().hide();
        Stage signInStage = new Stage();
        FXMLLoader loader = new FXMLLoader();

        signInStage.setTitle("Sign In");
        signInStage.setOnCloseRequest(e -> {
            e.consume();
            System.out.print("Main screen closed. Moving to Sign In screen.");
        });


        Pane root = loader.load(getClass().getResource("fxml/sign-in.fxml").openStream());
        Scene signInScene = new Scene(root);
        signInStage.setScene(signInScene);
        signInStage.show();
    }

    @FXML
    public void signUpButton(ActionEvent actionEvent) throws IOException
    {

        ((Node)actionEvent.getSource()).getScene().getWindow().hide();
        Stage signUpStage = new Stage();
        FXMLLoader loader = new FXMLLoader();

        signUpStage.setTitle("Sign Up");
        signUpStage.setOnCloseRequest(e -> {
            e.consume();
            System.out.print("Main screen closed. Moving to Sign Up screen.");
        });


        Pane root = loader.load(getClass().getResource("fxml/sign-up.fxml").openStream());
        Scene SignUpScene = new Scene(root);
        signUpStage.setScene(SignUpScene);
        signUpStage.show();
    }


    @FXML
    public void searchButton(ActionEvent actionEvent)
    {
        ((Node)actionEvent.getSource()).getScene().getWindow().hide();
        Stage searchStage = new Stage();
        FXMLLoader loader = new FXMLLoader();

        searchStage.setTitle("Search");
        searchStage.setOnCloseRequest(e -> {
            e.consume();
            System.out.print("Main screen closed. Moving to Search screen.");
        });


//        Pane root = loader.load(getClass().getResource("fxml/search.fxml").openStream()); // TODO Daniel's Search implementation
//        Scene SearchScene = new Scene(root);
//        searchStage.setScene(SearchScene);
//        searchStage.show();
    }

    @FXML
    public void exitButton(ActionEvent actionEvent)
    {
        ((Node)actionEvent.getSource()).getScene().getWindow().hide();
        System.exit(0);
    }
}
