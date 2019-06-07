package product.content;

import product.City;
import product.DigitalMap;
import product.Tour;

import java.util.HashMap;

public interface Editor
{
    default DigitalMap addContent(HashMap<Integer, DigitalMap> digitalMap, Content newContent, int digitalMapID)
    {
        if (digitalMapHub == null || newContent == null)
        {
            throw new IllegalArgumentException("addContent can't except null arguments.");
        }

        DigitalMap updateMap = digitalMapHub.get(digitalMapID);
        if ( updateMap != null)
        {
            if (updateMap.addContent(newContent) == true)
            {
                digitalMapHub.replace(digitalMapID, updateMap);
                return true;
            }
        }
        return false;
    }

    default Boolean updateContent(Content newContent, DigitalMap digitalMapID)
    {

    }

    default Boolean addDigitalMap(DigitalMap digitalMap, int cityID)
    {

    }

    default Boolean updateDigitalMap(DigitalMap digitalMap, int cityID)
    {

    }

    default Boolean addTour(Tour newTour, City cityID)
    {

    }

    default Boolean updateTour(Tour newTour, City cityID)
    {

    }

    default Boolean addCity(City city)
    {

    }

    default Boolean updateCity(City city)
    {

    }

}
