package command;

import product.ProductType;
import product.content.Content;
import product.City;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import client.ChatClient;
import command.catalog.Catalog;
import user.Role;


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


    public Object searchByID(int objectID, ProductType productType, Role role)
    {
        String type = productTypeToString(productType);
        assert !type.equals("none");

        try {
            chat.sendToServer("#search product" + type + " " + objectID);
            return chat.recieveObjectFromServer();
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
            List<?> objectCities = (List<?>)chat.recieveObjectFromServer();
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

    public Catalog searchByContent(String contentName)
    {
        try {
            chat.sendToServer("#search content name" + contentName);
            List<?> objectContents = (List<?>)chat.recieveObjectFromServer();
            if(objectContents.isEmpty()) {
                return null;
            }
            List<Content> contents = new ArrayList<>();
            for (Object obj : objectContents) {
                contents.add((Content)obj);
            }
            catalog = new Catalog(contents,null,null,null);
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
            List<?> objectCities = (List<?>)chat.recieveObjectFromServer();
            if(objectCities.isEmpty()) {
                return null;
            }
            List<City> cities = new ArrayList<>();
            for (Object obj : objectCities) {
                cities.add((City)obj);
            }
            catalog = new Catalog(null,null,null,cities);
            return catalog;
        }
        catch(IOException ex) {
            return null;
        }
    }


    public void displayCityMaps(int cityID) {
        try {
            chat.sendToServer(cityID);
            List<?> objectCities = (List<?>)chat.recieveObjectFromServer();
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
