package gui;

import java.io.IOException;


import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import chat.ChatClient;
import chat.common.ChatIF;
import command.Editor;
import command.Search;
import command.catalog.Catalog;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import product.City;
import product.ProductType;
import product.pricing.Purchase;
import user.Permission;
import user.manager.*;


public class MemberPurchaseScreen implements ChatIF, Serializable
{

    public class PurchaseForDisplay{
        private Date dateOfPurchase;
        private String cost;
        private String type;
        private String city;
        private String map;
        public PurchaseForDisplay(Date dateOfPurchase, String cost,String type,String city,String map)
        {
            this.dateOfPurchase=dateOfPurchase;
            this.cost=cost;
            this.type=type;
            this.city=city;
            this.map=map;
        }
    }

    private String host = MainClient.getHost();
    private int port = MainClient.getPort();
    private ChatClient chat = MainClient.getChat();

    @FXML // fx:id="PurchaseType"
    private TableColumn<PurchaseForDisplay, String> PurchaseType; // Value injected by FXMLLoader

    @FXML // fx:id="PurchaseMaps"
    private TableColumn<PurchaseForDisplay, String> PurchaseMaps; // Value injected by FXMLLoader

    @FXML // fx:id="PurchaseCities"
    private TableColumn<PurchaseForDisplay, String> PurchaseCities; // Value injected by FXMLLoader

    @FXML // fx:id="DateOfPurchase"
    private TableColumn<PurchaseForDisplay, Date> DateOfPurchase; // Value injected by FXMLLoader

    @FXML // fx:id="Cost"
    private TableColumn<PurchaseForDisplay, String> Cost; // Value injected by FXMLLoader

    @FXML // fx:id="table"
    private TableView<PurchaseForDisplay> table; // Value injected by FXMLLoader

    public void initialize() throws IOException{
        PurchaseType.setCellValueFactory(new PropertyValueFactory<PurchaseForDisplay, String>("type"));
        PurchaseMaps.setCellValueFactory(new PropertyValueFactory<PurchaseForDisplay, String>("map"));
        PurchaseCities.setCellValueFactory(new PropertyValueFactory<PurchaseForDisplay, String>("city"));
        DateOfPurchase.setCellValueFactory(new PropertyValueFactory<PurchaseForDisplay, Date>("dateOfPurchase"));
        Cost.setCellValueFactory(new PropertyValueFactory<PurchaseForDisplay, String>("cost"));
        if (this.chat == null)
        {
            this.chat = new ChatClient(host, port, this);
        }
        System.out.println("start");
        List<Purchase> purchaseHistory = null;
        if(MainClient.memberReportActivity) {
            if(MainClient.memberSignedIn instanceof CompanyManager) {
                purchaseHistory = ((CompanyManager)(MainClient.memberSignedIn)).getCustomersReportActivity(chat);
            }
            if(MainClient.memberSignedIn instanceof ContentManager) {
                purchaseHistory = ((ContentManager)(MainClient.memberSignedIn)).getCustomersReportActivity(chat);
            }
            MainClient.memberReportActivity = false;
        }
        else {
            System.out.println("else");
            MainClient.result=null;
            chat.sendToServer("report "+MainClient.memberSignedIn.getMemberIDByString());
            while (MainClient.result == null)
            {
                System.out.print("");
            }
            purchaseHistory = (List<Purchase>) MainClient.result;
        }
        if(purchaseHistory == null) {
            return;
        }
        System.out.println("1");
        ObservableList<PurchaseForDisplay> temp=FXCollections.observableArrayList();
        for (Purchase p : purchaseHistory)
        {
            int purchaseCityIDs = p.getPurchasedCityID();
            MainClient.result = null;
            //Search.searchByID(purchaseCityIDs, ProductType.CITY, Permission.COMPANY_MANAGER);
            chat.sendToServer("#Search product city " + purchaseCityIDs);
            while (MainClient.result == null)
            {
                System.out.print("");
            }
            Catalog res = (Catalog) MainClient.result;
            System.out.println(res.getCities().get(0).getCityName());
            System.out.println(p.getDateOfPurchase() + " " + p.getCostByString() + " " + p.getPurchaseTypeInString() + " " + p.getPurchasedMapNumberByString());
            String city = Objects.requireNonNull(res.getCities().get(0).getCityName());
            temp.add(new PurchaseForDisplay(p.getDateOfPurchase(),p.getCostByString(),p.getPurchaseTypeInString()
                    ,city,p.getPurchasedMapNumberByString()));
        }
        table.setItems(temp);
    }

    public void start(Stage primaryStage) throws Exception
    {
        primaryStage.setTitle("Purchase History");
        primaryStage.setOnCloseRequest(e -> {
            e.consume();
            System.out.print("");
        });

        FXMLLoader loader = new FXMLLoader();
        Pane root = loader.load(getClass().getResource("fxml/member-purchase.fxml").openStream());
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML
    void backButton(ActionEvent event)  throws IOException{
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

    @Override
    public void display(String message) {}

}
