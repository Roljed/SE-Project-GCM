package product;

import java.util.HashMap;
import product.content.Content;

/**
 * Catalog of all products that can be accessed directly by all users
 *
 * @version 1
 * @author Yaad Nahshon
 */
public class Catalog
{

    private HashMap<City, String> cityCollection;
    private HashMap<Content, String> contentCollection;
    private HashMap<DigitalMap, Integer> digitalMapCollection;


    public Catalog(HashMap<City, String> cityCollection, HashMap<Content, String> contentCollection, HashMap<DigitalMap, Integer> digitalMapCollection) {
        this.cityCollection = cityCollection;
        this.contentCollection = contentCollection;
        this.digitalMapCollection = digitalMapCollection;
    }

    public HashMap<City, String> getCityCollection()
    {
        return cityCollection;
    }

    public void setCityCollection(HashMap<City, String> cityCollection)
    {
        this.cityCollection = cityCollection;
    }

    public HashMap<Content, String> getContentCollection()
    {
        return contentCollection;
    }

    public void setContentCollection(HashMap<Content, String> contentCollection)
    {
        this.contentCollection = contentCollection;
    }

    public HashMap<DigitalMap, Integer> getDigitalMapCollection()
    {
        return digitalMapCollection;
    }

    public void setDigitalMapCollection(HashMap<DigitalMap, Integer> digitalMapCollection)
    {
        this.digitalMapCollection = digitalMapCollection;
    }
}
