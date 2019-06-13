package command;

import product.ProductType;
import product.City;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import chat.ChatClient;
import command.catalog.Catalog;
import product.content.Site;
import user.Permission;


/**
 *
 */
public class Search
{
    private ChatClient chat;
    private Catalog catalog;

    public Search(ChatClient chat_)
    {
        chat = chat_;
        catalog = null;
    }


    public Object searchByID(int objectID, ProductType productType, Permission permission)
    {
        String type = productTypeToString(productType);
        assert !type.equals("none");

        try {
            chat.sendToServer("#search product" + type + " " + objectID);
            return chat.receiveObjectFromServer();
        }
        catch(IOException ex) {
            return null;
        }
    }

    public Catalog searchByCityName(String cityName)
    {

        //TODO: duplicated code. Lines 25-41 && 66-82
        try {
            chat.sendToServer("#search city name" + cityName);
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
            chat.sendToServer("#search site name" + siteName);
            List<?> objectSites = (List<?>)chat.receiveObjectFromServer();
            if(objectSites.isEmpty()) {
                return null;
            }
            List<Site> sites = new ArrayList<>();
            for (Object obj : objectSites) {
                sites.add((Site)obj);
            }
            catalog = new Catalog(sites,null,null,null);
            catalog.viewCatalog();
            return catalog;
        }
        catch(IOException ex) {
            return null;
        }
    }

    public Catalog searchByDescription(String description) {
        try {
            chat.sendToServer("#search object description" + description);
            List<?> objectCities = (List<?>)chat.receiveObjectFromServer();
            if(objectCities.isEmpty()) {
                return null;
            }
            List<City> cities = new ArrayList<>();
            List<Site> sites = new ArrayList<>();
            for (Object obj : objectCities) {
                if(obj instanceof City)
                {
                    cities.add((City)obj);
                }
                else {
                    sites.add((Site)obj);
                }
            }
            catalog = new Catalog(sites,null,null,cities);
            return catalog;
        }
        catch(IOException ex) {
            return null;
        }
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

    private String productTypeToString(ProductType productType)
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
