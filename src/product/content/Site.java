package product.content;

/**
 * Implements interesting places on the DigitalMap
 *
 * @version 1
 * @author Yaad Nahshon
 */
public class Site extends Content
{
    private String name;
    private Classification type;
    private String details;
    private boolean accessibility;

    public enum Classification
    {
        CINEMA,
        HISTORICAL,
        HOTEL,
        MUSEUM,
        PARK,
        PUBLIC_SITE,
        RESTURANT,
        STORE
    }

    public Site(Location newLocationCoordinate, double newDuration, String name, Classification type, String details, boolean accessibility)
    {
        super(newLocationCoordinate, newDuration);
        this.name = name;
        this.type = type;
        this.details = details;
        this.accessibility = accessibility;
    }

    public Site(double newCoordinateX, double newCoordinateY, double newDuration, String name, Classification type, String details, boolean accessibility)
    {
        super(newCoordinateX, newCoordinateY, newDuration);
        this.name = name;
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
        return this.getLocationCoordinate();
    }

    public void setLocation(Location location)
    {
        this.setLocationCoordinate(location);
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
