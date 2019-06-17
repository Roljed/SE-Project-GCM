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
import product.CityToDisplay;
import product.DigitalMap;
import product.pricing.Purchase;
import product.pricing.PurchaseType;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 *  Opens display of the searched items
 * @author Yaad Nahshon
 */
public class CatalogResultsScreen
{
    private List<City> cityList;
    /**
     * This method initializes the screen, so when the screen is openned, the data is visible.
     */
    @FXML
    public void initialize()
    {
        // Table
        cityNameColumn.setCellValueFactory(new PropertyValueFactory<CityToDisplay, String>("name"));
        cityCountMapsColumn.setCellValueFactory(new PropertyValueFactory<CityToDisplay, String>("numberMap"));
        cityCountSitesColumn.setCellValueFactory(new PropertyValueFactory<CityToDisplay, String>("numberSite"));
        cityCountToursColumn.setCellValueFactory(new PropertyValueFactory<CityToDisplay, String>("numberTour"));
        cityPriceColumn.setCellValueFactory(new PropertyValueFactory<CityToDisplay, String>("price"));
        mapDescriptionColumn.setCellValueFactory(new PropertyValueFactory<CityToDisplay, String>("description"));

        tableView.setItems(getCatalog());

        // Toggle
        purchaseOption = new ToggleGroup();
        oneTimePurchaseRadioButton.setToggleGroup(purchaseOption);
        subscriptionRadioButton.setToggleGroup(purchaseOption);
    }
    
    /**
     * This method puts the objects of the catalog into the observable list, so it can be put into the table ciew.
     */

    private ObservableList<CityToDisplay> getCatalog()
    {
        ObservableList<CityToDisplay> cityToDisplay = FXCollections.observableArrayList();
        Catalog catalog = MainClient.catalog;
        if (catalog.getCities() == null)
        {
            System.out.println("Error! CatalogResultsScreen --> getCatalog --> catalog.getCities() is null");
            System.exit(101);
        }

        cityList = catalog.getCities();
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
    
    /**
     * This method handles with pushing the "Purchase" button on the screen
     *
     * @param actionEvent the event of mouse clicking on the "Purchase" button
     */

    public void makePurchaseButton(ActionEvent actionEvent)
    {
        if (tableView.getSelectionModel().getSelectedItem() == null) {
            messageLabel.setText("No selection made");
            return;
        }

        int desiredCityID = 0;
        double desiredCityPrice = 0;
        String cityName = tableView.getSelectionModel().getSelectedItem().getName();
        for (City city : cityList)
        {
            if (city.getCityName().equals(cityName))
            {
                desiredCityID = city.getCityID();
                desiredCityPrice = city.getCityPrice();
            }

        }

        PurchaseType type;
        if (purchaseOption.getSelectedToggle().equals(oneTimePurchaseRadioButton))
        {
            System.out.println("One Time Purchase");
            type = PurchaseType.ONE_TIME_PURCHASE;

        }
        else
        {
            System.out.println("Subscription");
            type = PurchaseType.ONE_TIME_PURCHASE;
        }
        Purchase purchase = new Purchase(MainClient.memberSignedIn.getMemberID() , desiredCityID, null, desiredCityPrice, type);
        MainClient.memberSignedIn.addPurchase(purchase);

        // TODO add to client and update database
        // TODO pop up message for download
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Download purchased city maps?" , ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            // TODO download
        }

    }
    
    /**
     * This method handles with pushing the "Purchase" button on the screen
     *
     * @param actionEvent the event of mouse clicking on the "Back" button
     */

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

    /**
     * This method handles with pushing the "Logout" button on the screen
     *
     * @param actionEvent the event of mouse clicking on the "Logout" button
     */

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
    private TableView<CityToDisplay> tableView;

    @FXML
    private TableColumn<CityToDisplay, String> cityNameColumn;

    @FXML
    private TableColumn<CityToDisplay, String> cityCountMapsColumn;

    @FXML
    private TableColumn<CityToDisplay, String> cityCountSitesColumn;

    @FXML
    private TableColumn<CityToDisplay, String> cityCountToursColumn;

    @FXML
    private TableColumn<CityToDisplay, String> cityPriceColumn;

    @FXML
    private TableColumn<CityToDisplay, String> mapDescriptionColumn;

    @FXML
    private RadioButton oneTimePurchaseRadioButton;

    @FXML
    private RadioButton subscriptionRadioButton;

    @FXML
    private Label messageLabel;

    @FXML
    private ToggleGroup purchaseOption;
}
