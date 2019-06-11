package command;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import client.ChatClient;
import product.City;
import product.DigitalMap;
import product.ProductType;
import product.Tour;
import product.content.Site;
import user.UserStatus;

/**
 * Interface of editor capabilities
 *
 * @version 1
 * @author Yaad Nahshon
 */
public interface Editor
{
    /**
     * @param oldContentID id of the content we want to update
     * @param newContent the content we wish to update
     * @param chat communicator with the server
     * @return true or false
     */
    default boolean updateContent(int oldContentID, Site newContent, ChatClient chat)
    {
        if (oldContentID <= 0 || newContent == null )
        {
            throw new IllegalArgumentException("Error.\nupdateContent can't except null arguments.");
        }

        Search search = new Search(chat);
        Site updateContent = (Site) search.searchByID(oldContentID, ProductType.CONTENT, UserStatus.EDITOR);
        if (updateContent == null)
        {
            return false;
        }

        updateContent = newContent;
        try
        {
            chat.sendToServer(updateContent);
        }
        catch(IOException ex)
        {
            return false;
        }

        return true;
    }

    /**
     * @param digitalMapID map id to add desired content to
     * @param newContent the content we wish to add
     * @param chat communicator with the server
     * @return true or false
     */
    default boolean addContentToMap(int digitalMapID, Site newContent, ChatClient chat)
    {
        if (digitalMapID <= 0 || newContent == null)
        {
            throw new IllegalArgumentException("Error.\naddContentToMap can't except null arguments.");
        }

        Search search = new Search(chat);
        DigitalMap updateMap  = (DigitalMap) search.searchByID(digitalMapID, ProductType.DIGITAL_MAP, UserStatus.EDITOR);
        if (updateMap == null)
        {
            return false;
        }
        try
        {
            chat.sendToServer(updateMap);
        }
        catch(IOException ex)
        {
            return false;
        }

        return true;
    }

    /**
     * @param cityID city id to add desired content to
     * @param newContent the content we wish to add
     * @param chat communicator with the server
     * @return true or false
     */
    default boolean addContentToCity(int cityID, Site newContent, ChatClient chat)
    {
        if (cityID <= 0 || newContent == null)
        {
            throw new IllegalArgumentException("Error.\naddContentToCity can't except null arguments.");
        }

        Search search = new Search(chat);
        City updateCity  = (City) search.searchByID(cityID, ProductType.CITY, UserStatus.EDITOR);
        if (updateCity == null)
        {
            return false;
        }

        try
        {
            for (Map.Entry<Integer, DigitalMap> updateMap : updateCity.getCityMaps().entrySet())
            {
                updateMap.getValue().getDigitalMapContents().put(newContent.getContendID() , newContent);
            }
            chat.sendToServer(updateCity);
        }
        catch(IOException ex)
        {
            return false;
        }

        return true;
    }

    /**
     * @param oldDigitalMapID id of the digital map we want to update
     * @param newDigitalMap the digital map we wish to update
     * @param chat communicator with the server
     * @return true or false
     */
    default boolean updateDigitalMap(int oldDigitalMapID, DigitalMap newDigitalMap, ChatClient chat)
    {
        if (oldDigitalMapID <= 0 || newDigitalMap == null)
        {
            throw new IllegalArgumentException("Error.\nupdateDigitalMap can't except null arguments.");
        }

        Search search = new Search(chat);
        DigitalMap updateDigitalMap = (DigitalMap) search.searchByID(oldDigitalMapID, ProductType.DIGITAL_MAP, UserStatus.EDITOR);
        if (updateDigitalMap == null)
        {
            return false;
        }

        updateDigitalMap = newDigitalMap;
        try
        {
            chat.sendToServer(updateDigitalMap);
        }
        catch(IOException ex)
        {
            return false;
        }
        return true;
    }

    /**
     * @param cityID city id to add desired digital map to
     * @param newDigitalMap the digital map we wish to add
     * @param chat communicator with the server
     * @return City object to check for new_city or null
     */
    default City addDigitalMapToCity(int cityID, DigitalMap newDigitalMap, ChatClient chat)
    {
        if (cityID <= 0 || newDigitalMap == null)
        {
            throw new IllegalArgumentException("Error.\naddDigitalMapToCity can't except null arguments.");
        }

        Search search = new Search(chat);
        City updatedCity = (City) search.searchByID(cityID, ProductType.CITY, UserStatus.EDITOR);
        if (updatedCity == null)
        {
            updatedCity = new City("new_city", new HashMap<>(), new HashMap<>(), 0, 0, new Date());
        }

        updatedCity.getCityMaps().put(newDigitalMap.getDigitalMapID(), newDigitalMap);
        try
        {
            chat.sendToServer(updatedCity);
        }
        catch(IOException ex)
        {
            return null;
        }

        return updatedCity;
    }

    /**
     * @param oldTour id of the tour we want to update
     * @param newTour the tour we wish to update
     * @param chat communicator with the server
     * @return true or false
     */
    default boolean updateTour(int oldTour, Tour newTour, ChatClient chat)
    {
        if (oldTour <= 0 || newTour == null)
        {
            throw new IllegalArgumentException("Error.\nupdateTour can't except null arguments.");
        }

        Search search = new Search(chat);
        Tour updatedTour = (Tour) search.searchByID(oldTour, ProductType.TOUR, UserStatus.EDITOR);
        if (updatedTour == null)
        {
            return false;
        }

        updatedTour = newTour;
        try
        {
            chat.sendToServer(updatedTour);
        }
        catch(IOException ex)
        {
            return false;
        }

        return true;
    }

    /**
     * @param cityID city id to add desired tour to
     * @param newTour the tour we wish to add
     * @param chat communicator with the server
     * @return true or false
     */
    default City addTour(int cityID, Tour newTour, ChatClient chat)
    {
        if (cityID <= 0 || newTour == null)
        {
            throw new IllegalArgumentException("Error.\naddTour can't except null arguments.");
        }

        Search search = new Search(chat);
        City updatedCity = (City) search.searchByID(cityID, ProductType.CITY, UserStatus.EDITOR);
        if (updatedCity == null)
        {
            return null;
        }

        updatedCity.getCityTours().put(newTour.getTourID(), newTour);
        try
        {
            chat.sendToServer(updatedCity);
        }
        catch(IOException ex)
        {
            return null;
        }

        return updatedCity;
    }

    /**
     * @param cityID city id we want to update
     * @param newCity the city we wish to update
     * @param chat communicator with the server
     * @return true or false
     */
    default boolean updateCity(int cityID, City newCity, ChatClient chat)
    {
        if (cityID <= 0 || newCity == null)
        {
            throw new IllegalArgumentException("Error.\nupdateCity can't except null arguments.");
        }

        Search search = new Search(chat);
        City updatedCity = (City) search.searchByID(cityID, ProductType.CITY, UserStatus.EDITOR);
        if (updatedCity == null)
        {
            return false;
        }

        updatedCity = newCity;
        try
        {
            chat.sendToServer(updatedCity);
        }
        catch(IOException ex)
        {
            return false;
        }

        return true;
    }

    /**
     * @param city the city we wish to add
     * @param chat communicator with the server
     * @return true or false
     */
    default boolean addCity(City city, ChatClient chat)
    {
        if (city == null)
        {
            throw new IllegalArgumentException("Error.\naddCity can't except null arguments.");
        }

        try
        {
            chat.sendToServer(city);
        }
        catch(IOException ex)
        {
            return false;
        }
        return true;
    }
}
