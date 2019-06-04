package product;
import product.content.Content;
import product.pricing.MapCost;

/**
 * Main product - Digital Map
 *
 * @version 1
 * @author Yaad Nahshon
 */
public class DigitalMap
{
    private int digitalMapID;
    private double digitalMapVersion;
    private String digitalMapDescription;
    private Content[] digitalMapContents;
    private MapCost digitalMapCost;


    public DigitalMap(int digitalMapID, double digitalMapVersion, String digitalMapDescription, Content[] digitalMapContents, MapCost digitalMapCost)
    {
        this.digitalMapID = digitalMapID;
        this.digitalMapVersion = digitalMapVersion;
        this.digitalMapDescription = digitalMapDescription;
        this.digitalMapContents = digitalMapContents;
        this.digitalMapCost = digitalMapCost;
    }

    public int getDigitalMapID()
    {
        return digitalMapID;
    }

    public void setDigitalMapID(int digitalMapID)
    {
        this.digitalMapID = digitalMapID;
    }

    public double getDigitalMapVersion()
    {
        return digitalMapVersion;
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

    public Content[] getDigitalMapContents()
    {
        return digitalMapContents;
    }

    public void setDigitalMapContents(Content[] digitalMapContents)
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
}