package product.content;

/**
 * Implements interesting places on the DigitalMap
 *
 * @version 1
 * @author Yaad Nahshon
 */
public class ContentInterestingPlaces extends Content
{
    private String name;
    private Location location;
    private Classification type;
    private String details;
    private boolean accessibility;

    public enum Classification {
        CINEMA,
        HISTORICAL,
        HOTEL,
        MUSEUM,
        PARK,
        PUBLIC_SITE,
        RESTURANT,
        STORE
    }

    public ContentInterestingPlaces(int newContentID, Location newLocationCoordinate, double newDuration, String name, Location location, Classification type, String details, boolean accessibility)
    {
        super(newContentID, newLocationCoordinate, newDuration);
        this.name = name;
        this.location = location;
        this.type = type;
        this.details = details;
        this.accessibility = accessibility;
    }

    public ContentInterestingPlaces(int newContentID, double newCoordinateX, double newCoordinateY, double newDuration, String name, Location location, Classification type, String details, boolean accessibility)
    {
        super(newContentID, newCoordinateX, newCoordinateY, newDuration);
        this.name = name;
        this.location = location;
        this.type = type;
        this.details = details;
        this.accessibility = accessibility;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Location getLocation()
    {
        return location;
    }

    public void setLocation(Location location)
    {
        this.location = location;
    }

    public Classification getType()
    {
        return type;
    }

    public void setType(Classification type)
    {
        this.type = type;
    }

    public String getDetails()
    {
        return details;
    }

    public void setDetails(String details)
    {
        this.details = details;
    }

    public boolean isAccessibility()
    {
        return accessibility;
    }

    public void setAccessibility(boolean accessibility)
    {
        this.accessibility = accessibility;
    }
}
