/*
  Sample Skeleton for 'search.fxml' Controller Class
 */

package gui;

import javafx.event.ActionEvent;
import java.io.IOException;
import java.io.Serializable;


import chat.ChatClient;
import chat.common.ChatIF;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import user.User;

public class SearchScreen implements ChatIF, Serializable{

    static private final int SERVER_PORT = 5555;
    private ChatClient chat = MainClient.getChat();

    @FXML // fx:id="searchByDescription"
    private Button searchByDescription; // Value injected by FXMLLoader

    @FXML // fx:id="searchBySiteName"
    private Button searchBySiteName; // Value injected by FXMLLoader

    @FXML // fx:id="requestField"
    private TextField requestField; // Value injected by FXMLLoader

    @FXML // fx:id="back"
    private Button back; // Value injected by FXMLLoader

    @FXML // fx:id="searchByCityName"
    private Button searchByCityName; // Value injected by FXMLLoader

    private Label messageLabel;
  
  /**
     * This method initializes the parameters of the window on openning
     */

    @FXML
    public void initialize() {}
  
  /**
     * This method handles with pushing the "Search By City" button on the screen
     *
     * @param actionEvent the event of mouse clicking on the "Search By City" button
     */

    @FXML
    void SearchByCity(ActionEvent event) throws IOException{
        if (requestField.getText().isEmpty()) {
            messageLabel.setText("Please enter your search request");
        }
        String request = requestField.getText();
        MainClient.result = null;

        if (this.chat == null)
        {
            this.chat = new ChatClient(MainClient.getHost(),SERVER_PORT, this);
        }
        User user = new User(chat);
        MainClient.catalog = user.viewCatalog(request, 1);
        if(MainClient.catalog == null) {
            messageLabel.setText("Your request is now found");
            return;
        }
        gotoCatalog(event);
    }
  
  /**
     * This method handles with pushing the "Search By Site" button on the screen
     *
     * @param actionEvent the event of mouse clicking on the "Search By Site" button
     */

    @FXML
    void SearchBySite(ActionEvent event) throws IOException {
        if (requestField.getText().isEmpty()) {
            messageLabel.setText("Please enter your search request");
        }
        String request = requestField.getText();
        MainClient.result = null;

        if (this.chat == null)
        {
            this.chat = new ChatClient(MainClient.getHost(),SERVER_PORT, this);
        }
        User user = new User(chat);
        MainClient.catalog = user.viewCatalog(request, 2);
        if(MainClient.catalog == null) {
            messageLabel.setText("Your request is now found");
            return;
        }
        gotoCatalog(event);
    }
  
  /**
     * This method handles with pushing the "Search By Description" button on the screen
     *
     * @param actionEvent the event of mouse clicking on the "Search By Description" button
     */

    @FXML
    void SearchByDescription(ActionEvent event) throws IOException {
        if (requestField.getText().isEmpty()) {
            messageLabel.setText("Please enter your search request");
        }
        String request = requestField.getText();
        MainClient.result = null;

        if (this.chat == null)
        {
            this.chat = new ChatClient(MainClient.getHost(),SERVER_PORT, this);
        }
        User user = new User(chat);
        MainClient.catalog = user.viewCatalog(request, 3);
        if(MainClient.catalog == null) {
            messageLabel.setText("Your request is now found");
            return;
        }
        gotoCatalog(event);

    }
  
  /**
     * This method opens the catalog screen, following the user's choice of searching
     *
     * @param actionEvent the event of mouse clicking on any of the search button
     */

    private void gotoCatalog(ActionEvent event) throws IOException
    {
        ((Node)event.getSource()).getScene().getWindow().hide();
        Stage catalogStage = new Stage();
        catalogStage.setTitle("Catalog Results");
        catalogStage.setOnCloseRequest(e -> {
            e.consume();
            System.out.print("");
        });
        Pane root = FXMLLoader.load(getClass().getResource("fxml/catalog-results.fxml"));
        Scene catalogScene = new Scene(root);
        catalogStage.setScene(catalogScene);
        catalogStage.show();
    }
  
  /**
     * This method handles with pushing the "Back" button on the screen
     *
     * @param actionEvent the event of mouse clicking on the "Back" button
     */

    @FXML
    void goBack(ActionEvent event) throws IOException{
        ((Node)event.getSource()).getScene().getWindow().hide();
        Stage backScreen = new Stage();
        FXMLLoader loader = new FXMLLoader();
        Pane root;
        if(MainClient.permission == null) {
            backScreen.setTitle("GCM Main Screen");
            root = loader.load(getClass().getResource("fxml/main.fxml").openStream());
        }
        else {
            switch(MainClient.permission) {
                case USER:
                    backScreen.setTitle("Member Page");
                    root = loader.load(getClass().getResource("fxml/member-screen.fxml").openStream());
                    break;
                case WORKER:
                case CONTENT_WORKER:
                    backScreen.setTitle("Member Page");
                    root = loader.load(getClass().getResource("fxml/worker-screen.fxml").openStream());
                    break;
                case COMPANY_MANAGER:
                case CONTENT_MANAGER:
                    backScreen.setTitle("Manager Page");
                    root = loader.load(getClass().getResource("fxml/manager-screen.fxml").openStream());
                    break;
                case MEMBER:
                    backScreen.setTitle("Member Page");
                    root = loader.load(getClass().getResource("fxml/member-screen.fxml").openStream());
                    break;
                default:
                    backScreen.setTitle("GCM Main Screen");
                    root = loader.load(getClass().getResource("fxml/main").openStream());
                    break;
            }
        }
        backScreen.setOnCloseRequest(e ->
        {
            e.consume();
            System.out.print("");
        });

        Scene mainScreenScene = new Scene(root);
        backScreen.setScene(mainScreenScene);
        backScreen.show();
    }

    public void display(String message) {}
}
