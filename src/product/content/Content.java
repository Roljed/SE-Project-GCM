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
    private double contentDuration;

    Content(int newContentID, Location newLocationCoordinate, double newDuration)
    {
        this.contendID = newContentID;
        this.locationCoordinate = newLocationCoordinate;
        this.contentDuration = newDuration;
    }

    Content(int newContentID, double newCoordinateX, double newCoordinateY, double newDuration)
    {
        this.contendID = newContentID;
        this.locationCoordinate = new Location(newCoordinateX, newCoordinateY);
        this.contentDuration = newDuration;
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

    public double getContentDuration()
    {
        return contentDuration;
    }

    public void setContentDuration(double contentDuration)
    {
        this.contentDuration = contentDuration;
    }

}
