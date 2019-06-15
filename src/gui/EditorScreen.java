/**
 * Sample Skeleton for 'edit.fxml' Controller Class
 */

package gui;

import java.io.IOException;
import java.util.Date;

import chat.ChatClient;
import command.Search;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import product.City;
import product.DigitalMap;
import product.ProductType;
import product.Tour;
import product.content.Site;
import command.Editor;

public class EditorScreen {

    private ChatClient chat = MainClient.getChat();
    @FXML // fx:id="addMapToCity_mapID"
    private TextField addMapToCity_mapID; // Value injected by FXMLLoader

    @FXML // fx:id="instructions"
    private Label instructions; // Value injected by FXMLLoader

    @FXML // fx:id="updateContent_newIDID"
    private TextField updateContent_newIDID; // Value injected by FXMLLoader

    @FXML // fx:id="updateTourRadioBtn"
    private RadioButton updateTourRadioBtn; // Value injected by FXMLLoader

    @FXML // fx:id="updateTour_newID"
    private TextField updateTour_newID; // Value injected by FXMLLoader

    @FXML // fx:id="addContentToCity_contentID"
    private TextField addContentToCity_contentID; // Value injected by FXMLLoader

    @FXML // fx:id="addCity_cityName"
    private TextField addCity_cityName; // Value injected by FXMLLoader

    @FXML // fx:id="updateTour_oldID"
    private TextField updateTour_oldID; // Value injected by FXMLLoader

    @FXML // fx:id="updateContentRadionBtn"
    private RadioButton updateContentRadionBtn; // Value injected by FXMLLoader

    @FXML // fx:id="resultLbl"
    private Label resultLbl; // Value injected by FXMLLoader

    @FXML // fx:id="addContentToMap_contentID"
    private TextField addContentToMap_contentID; // Value injected by FXMLLoader

    @FXML // fx:id="addMapToCity_cityID"
    private TextField addMapToCity_cityID; // Value injected by FXMLLoader

    @FXML // fx:id="updateMapRadionBtn"
    private RadioButton updateMapRadionBtn; // Value injected by FXMLLoader

    @FXML // fx:id="welcome"
    private Label welcome; // Value injected by FXMLLoader

    @FXML // fx:id="group"
    private ToggleGroup group; // Value injected by FXMLLoader

    @FXML // fx:id="updateMap_oldID"
    private TextField updateMap_oldID; // Value injected by FXMLLoader

    @FXML // fx:id="submitBtn"
    private Button submitBtn; // Value injected by FXMLLoader

    @FXML // fx:id="addContentToCityRadionBtn"
    private RadioButton addContentToCityRadionBtn; // Value injected by FXMLLoader

    @FXML // fx:id="addContentToMap_mapID"
    private TextField addContentToMap_mapID; // Value injected by FXMLLoader

    @FXML // fx:id="updateContent_oldID"
    private TextField updateContent_oldID; // Value injected by FXMLLoader

    @FXML // fx:id="updateMap_newID"
    private TextField updateMap_newID; // Value injected by FXMLLoader

    @FXML // fx:id="addContentToCity_cityID"
    private TextField addContentToCity_cityID; // Value injected by FXMLLoader

    @FXML // fx:id="addContentToMapRadionBtn"
    private RadioButton addContentToMapRadionBtn; // Value injected by FXMLLoader

    @FXML // fx:id="updateCity_oldID"
    private TextField updateCity_oldID; // Value injected by FXMLLoader

    @FXML // fx:id="updateCityRadionBtn"
    private RadioButton updateCityRadionBtn; // Value injected by FXMLLoader

    @FXML // fx:id="addMapToCityRadionBtn"
    private RadioButton addMapToCityRadionBtn; // Value injected by FXMLLoader

    @FXML // fx:id="addCity_price"
    private TextField addCity_price; // Value injected by FXMLLoader

    @FXML // fx:id="addTourToCity_cityID"
    private TextField addTourToCity_cityID; // Value injected by FXMLLoader

    @FXML // fx:id="addCityRadionBtn"
    private RadioButton addCityRadionBtn; // Value injected by FXMLLoader

    @FXML // fx:id="backBtn"
    private Button backBtn; // Value injected by FXMLLoader

    @FXML // fx:id="updateCity_newID"
    private TextField updateCity_newID; // Value injected by FXMLLoader

    @FXML // fx:id="addTourToCity_tourID"
    private TextField addTourToCity_tourID; // Value injected by FXMLLoader

    @FXML // fx:id="addTourToCityRadionBtn"
    private RadioButton addTourToCityRadionBtn; // Value injected by FXMLLoader

    @FXML
    void submit(ActionEvent event) throws IOException{
        //Search search = new Search(chat);
        RadioButton selected = (RadioButton) group.getSelectedToggle();
        if(selected == updateTourRadioBtn) {
            if(updateTour_oldID.getText().isEmpty() || updateTour_newID.getText().isEmpty()) {
                resultLbl.setText("Please enter the required fields");
            }
            else {
                Tour newTour = (Tour)Search.searchByID(Integer.parseInt(updateTour_newID.getText()),ProductType.TOUR,MainClient.permission);
                boolean res = ((Editor)(MainClient.memberSignedIn)).updateTour(Integer.parseInt(updateTour_oldID.getText()),newTour,chat);
                if (res) {
                    resultLbl.setText("The information was updated");
                }
                else {
                    resultLbl.setText("Something went wrong");
                }
            }
        }
        if(selected == updateContentRadionBtn) {
            if(updateContent_oldID.getText().isEmpty() || updateContent_newIDID.getText().isEmpty()) {
                resultLbl.setText("Please enter the required fields");
            }
            else {
                Site newSite = (Site)Search.searchByID(Integer.parseInt(updateContent_newIDID.getText()),ProductType.CONTENT,MainClient.permission);
                boolean res = ((Editor)(MainClient.memberSignedIn)).updateContent(Integer.parseInt(updateTour_oldID.getText()),newSite,chat);
                if (res) {
                    resultLbl.setText("The information was updated");
                }
                else {
                    resultLbl.setText("Something went wrong");
                }
            }
        }
        if(selected == updateMapRadionBtn) {
            if(updateMap_oldID.getText().isEmpty() || updateMap_newID.getText().isEmpty()) {
                resultLbl.setText("Please enter the required fields");
            }
            else {
                DigitalMap newMap = (DigitalMap)Search.searchByID(Integer.parseInt(updateMap_newID.getText()),ProductType.DIGITAL_MAP,MainClient.permission);
                boolean res = ((Editor)(MainClient.memberSignedIn)).updateDigitalMap(Integer.parseInt(updateMap_oldID.getText()),newMap,chat);
                if (res) {
                    resultLbl.setText("The information was updated");
                }
                else {
                    resultLbl.setText("Something went wrong");
                }
            }
        }
        if(selected == updateCityRadionBtn) {
            if(updateCity_oldID.getText().isEmpty() || updateCity_newID.getText().isEmpty()) {
                resultLbl.setText("Please enter the required fields");
            }
            else {
                City newCity = (City)Search.searchByID(Integer.parseInt(updateCity_newID.getText()),ProductType.CITY,MainClient.permission);
                boolean res = ((Editor)(MainClient.memberSignedIn)).updateCity(Integer.parseInt(updateCity_oldID.getText()),newCity,chat);
                if (res) {
                    resultLbl.setText("The information was updated");
                }
                else {
                    resultLbl.setText("Something went wrong");
                }
            }
        }
        if(selected == addContentToCityRadionBtn) {
            if(addContentToCity_cityID.getText().isEmpty() || addContentToCity_contentID.getText().isEmpty()) {
                resultLbl.setText("Please enter the required fields");
            }
            else {
                Site newSite = (Site)Search.searchByID(Integer.parseInt(addContentToCity_contentID.getText()),ProductType.CONTENT,MainClient.permission);
                boolean res = ((Editor)(MainClient.memberSignedIn)).addContentToCity(Integer.parseInt(addContentToCity_cityID.getText()),newSite,chat);
                if (res) {
                    resultLbl.setText("The information was updated");
                }
                else {
                    resultLbl.setText("Something went wrong");
                }
            }
        }
        if(selected == addContentToMapRadionBtn) {
            if(addContentToMap_mapID.getText().isEmpty() || addContentToMap_contentID.getText().isEmpty()) {
                resultLbl.setText("Please enter the required fields");
            }
            else {
                Site newSite = (Site)Search.searchByID(Integer.parseInt(addContentToCity_contentID.getText()),ProductType.CONTENT,MainClient.permission);
                boolean res = ((Editor)(MainClient.memberSignedIn)).addContentToMap(Integer.parseInt(addContentToMap_mapID.getText()),newSite,chat);
                if (res) {
                    resultLbl.setText("The information was updated");
                }
                else {
                    resultLbl.setText("Something went wrong");
                }
            }
        }
        if(selected == addMapToCityRadionBtn) {
            if(addMapToCity_cityID.getText().isEmpty() || addMapToCity_mapID.getText().isEmpty()) {
                resultLbl.setText("Please enter the required fields");
            }
            else {
                DigitalMap newMap = (DigitalMap)Search.searchByID(Integer.parseInt(addMapToCity_mapID.getText()),ProductType.DIGITAL_MAP,MainClient.permission);
                City res = ((Editor)(MainClient.memberSignedIn)).addDigitalMapToCity(Integer.parseInt(addMapToCity_cityID.getText()),newMap,chat);
                if (res == null) {
                    resultLbl.setText("The information was updated");
                }
                else {
                    resultLbl.setText("Something went wrong");
                }
            }
        }
        if(selected == addCityRadionBtn) {
            if(addCity_cityName.getText().isEmpty() || addCity_price.getText().isEmpty()) {
                resultLbl.setText("Please enter the required fields");
            }
            else {
                City newCity = new City(addCity_cityName.getText(),null,null,Double.parseDouble(addCity_price.getText()),1,new Date());
                boolean res = ((Editor)(MainClient.memberSignedIn)).addCity(newCity,chat);
                if (res) {
                    resultLbl.setText("The information was updated");
                }
                else {
                    resultLbl.setText("Something went wrong");
                }
            }
        }
        if(selected == addTourToCityRadionBtn) {
            if(addTourToCity_cityID.getText().isEmpty() || addTourToCity_tourID.getText().isEmpty()) {
                resultLbl.setText("Please enter the required fields");
            }
            else {
                Tour newTour = (Tour)Search.searchByID(Integer.parseInt(addTourToCity_tourID.getText()),ProductType.TOUR,MainClient.permission);
                City res = ((Editor)(MainClient.memberSignedIn)).addTour(Integer.parseInt(addTourToCity_cityID.getText()),newTour,chat);
                if (res == null) {
                    resultLbl.setText("The information was updated");
                }
                else {
                    resultLbl.setText("Something went wrong");
                }
            }
        }
    }

    @FXML
    void back(ActionEvent event) throws IOException{
        ((Node)event.getSource()).getScene().getWindow().hide();
        Stage backScreen = new Stage();
        FXMLLoader loader = new FXMLLoader();
        Pane root;
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
        backScreen.setOnCloseRequest(e ->
        {
            e.consume();
            System.out.print("");
        });

        Scene mainScreenScene = new Scene(root);
        backScreen.setScene(mainScreenScene);
        backScreen.show();
    }
}
