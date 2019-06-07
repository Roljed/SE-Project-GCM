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
    private static int nextContentID = 0;
    private Location locationCoordinate;
    private double contentDuration;

    Content(Location newLocationCoordinate, double newDuration)
    {
        this.contendID = nextContentID++;
        this.locationCoordinate = newLocationCoordinate;
        this.contentDuration = newDuration;
    }

    Content(double newCoordinateX, double newCoordinateY, double newDuration)
    {
        this.contendID = nextContentID + 1;
        this.locationCoordinate = new Location(newCoordinateX, newCoordinateY);
        this.contentDuration = newDuration;
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
