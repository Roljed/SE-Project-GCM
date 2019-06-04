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

    public Content(int newContentID, Location newLocationCoordinate)
    {
        this.contendID = newContentID;
        this.locationCoordinate = newLocationCoordinate;
    }

    public Content(int newContentID, double newCoordinateX, double newCoordinateY)
    {
        this.contendID = newContentID;
        this.locationCoordinate = new Location(newCoordinateX, newCoordinateY);
    }

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
