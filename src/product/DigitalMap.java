package product;
import product.content.Content;
import product.pricing.MapCost;
import user.Permission;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Main product - Digital Map
 * Regular maps come from different source
 *
 * @version 1
 * @author Yaad Nahshon
 */
public class DigitalMap implements Serializable
{
    private int digitalMapID;
    private static int digitalMapIDNext = 0;
    private double digitalMapVersion;
    private String digitalMapDescription;
    private HashMap<Integer, Content> digitalMapContents;
    private MapCost digitalMapCost;


    public DigitalMap(double digitalMapVersion, String digitalMapDescription, HashMap<Integer, Content> digitalMapContents, MapCost digitalMapCost)
    {
        this.digitalMapID = digitalMapIDNext++;
        this.digitalMapVersion = digitalMapVersion;
        this.digitalMapDescription = digitalMapDescription;
        this.digitalMapContents = digitalMapContents;
        this.digitalMapCost = digitalMapCost;
    }

    public DigitalMap(int newDigitalMapID, double digitalMapVersion, String digitalMapDescription, HashMap<Integer, Content> digitalMapContents, MapCost digitalMapCost)
    {
        this.digitalMapID = newDigitalMapID;
        this.digitalMapVersion = digitalMapVersion;
        this.digitalMapDescription = digitalMapDescription;
        this.digitalMapContents = digitalMapContents;
        this.digitalMapCost = digitalMapCost;
    }

    public int getDigitalMapID()
    {
        return digitalMapID;
    }

    public String getDigitalMapIDToString()
    {
        return String.valueOf(digitalMapID);
    }

    public double getDigitalMapVersion()
    {
        return digitalMapVersion;
    }

    public String getDigitalMapVersionToString()
    {
        return String.valueOf(digitalMapVersion);
    }

    public void setDigitalMapVersion(double digitalMapVersion)
    {
        this.digitalMapVersion = digitalMapVersion;
    }

    public String getDigitalMapDescription()
    {
        return digitalMapDescription;
    }

    public void setDigitalMapDescription(String digitalMapDescription)
    {
        this.digitalMapDescription = digitalMapDescription;
    }

    public HashMap<Integer, Content> getDigitalMapContents()
    {
        return digitalMapContents;
    }

    public void setDigitalMapContents(HashMap<Integer, Content> digitalMapContents)
    {
        this.digitalMapContents = digitalMapContents;
    }

    public MapCost getDigitalMapCost()
    {
        return digitalMapCost;
    }

    public void setDigitalMapCost(MapCost digitalMapCost)
    {
        this.digitalMapCost = digitalMapCost;
    }

    public Content addContent(Content newContent)
    {
       return this.digitalMapContents.put(newContent.getContendID(), newContent);
    }

}