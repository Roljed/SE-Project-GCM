package command;

import gui.MainClient;
import product.ProductType;
import product.City;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import chat.ChatClient;
import command.catalog.Catalog;


/**
 * Communicate with database to retrieve relevant requested information
 *
 * @author Daniel Katz
 * @version 1
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

    /**
     * @param objectID object to search by his id
     * @param productType for search focus
     * @return requested object that match the search argument
     */
    public static Object searchByID(int objectID, ProductType productType)
    {
        String type = productTypeToString(productType);
        assert !type.equals("none");

        try {
            chat.sendToServer("#Search product " + type + " " + objectID);
        }
        catch(IOException ex) {
            return null;
        }
        return null;
    }

    /**
     * @param cityName requested city to search
     * @return Catalog with all the cities that match the search argument
     */
    public Catalog searchByCityName(String cityName)
    {
        try {
            chat.sendToServer("#Search city name " + cityName);
            try {
                Thread.sleep(15000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Object obj = MainClient.result;
            if (obj instanceof Catalog)
            {
                return (Catalog)obj;
            }
            else {
                return null;
            }
        }
        catch(IOException ex) {
            return null;
        }
    }

    /**
     * @param siteName requested site to search
     * @return Catalog with all the sites that match the search argument
     */
    public Catalog searchBySite(String siteName)
    {
        try {
            chat.sendToServer("#Search site name " + siteName);
        }
        catch(IOException ex) {
            return null;
        }
        return null;
    }

    /**
     * @param description searches through cities and sites for description match
     * @return Catalog with all the cities and sites that match the search argument
     */
    public Catalog searchByDescription(String description) {
        try {
            chat.sendToServer("#Search object " + description);
        }
        catch(IOException ex) {
            return null;
        }
        return null;
    }

    /**
     * @param productType parsed to string
     * @return String class that matches the ProductType enum
     */
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


    /**
     * @return Catalog private argument getter
     */
    public Catalog getCatalog() {
        return catalog;
    }

}
