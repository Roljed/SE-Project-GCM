package command;

import product.ProductType;
import product.City;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import chat.ChatClient;
import command.catalog.Catalog;
import product.content.Site;
import user.Permission;


/**
 *
 */
public class Search implements Serializable
{
    private static ChatClient chat;
    private Catalog catalog;

    public Search(ChatClient chat_)
    {
        chat = chat_;
        catalog = null;
    }

    public static Object searchByID(int objectID, ProductType productType, Permission permission)
    {
        String type = productTypeToString(productType);
        assert !type.equals("none");

        try {
            chat.sendToServer("#Search product " + type + " " + objectID);
//            return chat.receiveObjectFromServer();
        }
        catch(IOException ex) {
            return null;
        }
        return null;
    }

    public Catalog searchByCityName(String cityName)
    {

        //TODO: duplicated code. Lines 25-41 && 66-82
        try {
            chat.sendToServer("#Search city name " + cityName);
            List<?> objectCities = (List<?>)chat.receiveObjectFromServer();
            if(objectCities.isEmpty()) {
                return null;
            }
            List<City> cities = new ArrayList<>();
            for (Object obj : objectCities) {
                cities.add((City)obj);
            }
            catalog = new Catalog(null,null,null,cities);
            catalog.viewCatalog();
            return catalog;
        }
        catch(IOException ex) {
            return null;
        }
    }

    public Catalog searchBySite(String siteName)
    {
        try {
            chat.sendToServer("#Search site name " + siteName);
//            return chat.receiveObjectFromServer();
        }
        catch(IOException ex) {
            return null;
        }
        return null;
    }

    public Catalog searchByDescription(String description) {
        try {
            chat.sendToServer("#Search object " + description);
//            return chat.receiveObjectFromServer();
        }
        catch(IOException ex) {
            return null;
        }
        return null;
    }


    public void displayCityMaps(int cityID) {
        try {
            chat.sendToServer(cityID);
            List<?> objectCities = (List<?>)chat.receiveObjectFromServer();
            if(objectCities.isEmpty()) {
                return;
            }
            List<City> cities = new ArrayList<>();
            for (Object obj : objectCities) {
                cities.add((City)obj);
            }
            catalog = new Catalog(null,null,null,cities);
            catalog.viewCatalog();
        }
        catch(IOException ex) {
            return;
        }
    }

    private static String productTypeToString(ProductType productType)
    {
        switch (productType)
        {
            case CONTENT:
                return "content";
            case DIGITAL_MAP:
                return "digital_map";
            case CITY:
                return "city";
            case TOUR:
                return "tour";
            default:
                return "none";
        }
    }

    public Catalog getCatalog() {
        return catalog;
    }

}
