package product.content;

import user.Permission;

import java.io.Serializable;

/**
 * Abstract class for future content classes
 *
 * @version 1
 * @author Yaad Nahshon
 */
public abstract class Content implements Serializable
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

    Content(int newContentID, Location newLocationCoordinate, double newDuration)
    {
        this.contendID = newContentID;
        this.locationCoordinate = newLocationCoordinate;
        this.contentDuration = newDuration;
    }

    public int getContendID()
    {
        return contendID;
    }

    public String getContendIDToString()
    {
        return String.valueOf(contendID);
    }

    public Location getLocationCoordinate()
    {
        return locationCoordinate;
    }

    public String getLocationCoordinateToString()
    {
        Location location = getLocationCoordinate();
        return location.getCoordinateX() + "," + location.getCoordinateY();
    }


    void setLocationCoordinate(Location locationCoordinate)
    {
        this.locationCoordinate = locationCoordinate;
    }

    public double getContentDuration()
    {
        return contentDuration;
    }

    public String getContentDurationToString()
    {
        return String.valueOf(contentDuration);
    }

    public void setContentDuration(double contentDuration)
    {
        this.contentDuration = contentDuration;
    }

}
