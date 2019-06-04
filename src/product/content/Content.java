package product.content;

/**
 * Abstract class for future proof content classes
 *
 * @version 1
 * @author Yaad Nahshon
 */
public abstract class Content
{

    private int contendID;
    private Location locationCoordinate;

    public void setContentID(int newID)
    {
        this.contendID = newID;
    }

    public int getContendID()
    {
        return contendID;
    }

    public Location getLocationCoordinate()
    {
        return locationCoordinate;
    }

    public void setLocationCoordinate(Location locationCoordinate)
    {
        this.locationCoordinate = locationCoordinate;
    }
}
