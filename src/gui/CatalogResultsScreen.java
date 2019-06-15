package gui;

import chat.ChatClient;
import command.catalog.Catalog;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import product.City;
import product.DigitalMap;

import java.io.IOException;
import java.util.Map;

/**
 *  Opens display of the searched items
 * @author Yaad Nahshon
 */
public class CatalogResultsScreen
{



    private static class CityToDisplay
    {
        private String name;
        private String numberMap;
        private String numberSite;
        private String numberTour;
        private String price;
        private String description;


        public CityToDisplay(String name, String numberMaps, String numberSites, String numberTour, String price, String description) {
            this.name = name;
            this.numberMap = numberMaps;
            this.numberSite = numberSites;
            this.numberTour = numberTour;
            this.price = price;
            this.description = description;
        }
    }


    @FXML
    public void initialize()
    {
        // Table
        cityNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        cityCountMapsColumn.setCellValueFactory(new PropertyValueFactory<>("numberMap"));
        cityCountSitesColumn.setCellValueFactory(new PropertyValueFactory<>("numberSite"));
        cityCountToursColumn.setCellValueFactory(new PropertyValueFactory<>("numberTour"));
        cityPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        mapDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

        tableView.setItems(getCatalog());

        // Toggle
        purchaseOption = new ToggleGroup();
        oneTimePurchaseRadioButton.setToggleGroup(purchaseOption);
        subscriptionRadioButton.setToggleGroup(purchaseOption);
    }

    private ObservableList<CityToDisplay> getCatalog()
    {
        ObservableList<CityToDisplay> cityToDisplay = FXCollections.observableArrayList();
        Catalog catalog = MainClient.catalog;
        if (catalog.getCities() == null)
        {
            System.out.println("Error! CatalogResultsScreen --> getCatalog --> catalog.getCities() is null");
            System.exit(101);
        }

        for (City city : catalog.getCities())
        {
            // City details and firs map description
            Map.Entry<Integer, DigitalMap> firstMap = city.getCityMaps().entrySet().iterator().next();
            cityToDisplay.add(new CityToDisplay(city.getCityName(), Integer.toString(city.getCityMaps().size()),
                    Integer.toString(city.getCountContent()), Integer.toString(city.getCityTours().size()), Double.toString(city.getCityPrice()), firstMap.getValue().getDigitalMapDescription()));
            // Descriptions of the the rest city maps
            for (Map.Entry<Integer, DigitalMap> cityMap : city.getCityMaps().entrySet())
            {
                cityToDisplay.add(new CityToDisplay(null,null,null, null ,null , cityMap.getValue().getDigitalMapDescription()));
            }
        }
        return cityToDisplay;
    }

    public void makePurchaseButton(ActionEvent actionEvent)
    {
        if (tableView.getSelectionModel().getSelectedItem() == null) {
            messageLabel.setText("No selection made");
            return;
        }

        if (purchaseOption.getSelectedToggle().equals(oneTimePurchaseRadioButton))
        {
            System.out.println("One Time Purchase");
        }
        else
        {
            System.out.println("Subscription");
        }
    }

    public void backButton(ActionEvent actionEvent) throws IOException
    {
        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
        Stage backScreen = new Stage();
        FXMLLoader loader = new FXMLLoader();
        Pane root;
        if (MainClient.permission == null) {
            backScreen.setTitle("GCM Main Screen");
            root = loader.load(getClass().getResource("fxml/main.fxml").openStream());
        } else {
            switch (MainClient.permission) {
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
    }


    public void logoutButton(ActionEvent actionEvent) throws IOException
    {
        chat.handleMessageFromClientUI("#SignOut " + MainClient.memberSignedIn.getUserName());
        ((Node)actionEvent.getSource()).getScene().getWindow().hide();
        Stage mainScreenStage = new Stage();
        mainScreenStage.setTitle("GCM Main Screen");
        mainScreenStage.setOnCloseRequest(e ->
        {
            e.consume();
            System.out.print("");
        });

        Pane root = FXMLLoader.load(getClass().getResource("fxml/main.fxml"));
        Scene mainScreenScene = new Scene(root);
        mainScreenStage.setScene(mainScreenScene);
        mainScreenStage.show();
    }

    private ChatClient chat = MainClient.getChat();

    @FXML
    public TableView<CityToDisplay> tableView;

    @FXML
    public TableColumn<CityToDisplay, String> cityNameColumn;

    @FXML
    public TableColumn<CityToDisplay, String> cityCountMapsColumn;

    @FXML
    public TableColumn<CityToDisplay, String> cityCountSitesColumn;

    @FXML
    public TableColumn<CityToDisplay, String> cityCountToursColumn;

    @FXML
    public TableColumn<CityToDisplay, String> cityPriceColumn;

    @FXML
    public TableColumn<CityToDisplay, String> mapDescriptionColumn;

    @FXML
    public RadioButton oneTimePurchaseRadioButton;

    @FXML
    public RadioButton subscriptionRadioButton;

    @FXML
    public Label messageLabel;

    private ToggleGroup purchaseOption;
}
