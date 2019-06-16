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
            while (MainClient.result == null)
            {

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

    public Catalog searchByCityName(String cityName)
    {
        try {
            chat.sendToServer("#Search city name " + cityName);
            while (MainClient.result == null)
            {

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

    public Catalog searchBySite(String siteName)
    {
        try {
            chat.sendToServer("#Search site name " + siteName);
            while (MainClient.result == null)
            {

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

    public Catalog searchByDescription(String description) {
        try {
            chat.sendToServer("#Search object " + description);
            while (MainClient.result == null)
            {

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
