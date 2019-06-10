package product.content;

import user.Role;

/**
 * Implements interesting places on the DigitalMap
 *
 * @version 1
 * @author Yaad Nahshon
 */
public class Site extends Content
{
    private String siteName;
    private Classification siteType;
    private String siteDescription;
    private boolean siteAccessibility;

    public enum Classification
    {
        NONE,
        CINEMA,
        HISTORICAL,
        HOTEL,
        MUSEUM,
        PARK,
        PUBLIC_SITE,
        RESTAURANT,
        STORE
    }

    public Site(Location newLocationCoordinate, double newDuration, String siteName, Classification siteType, String siteDescription, boolean siteAccessibility)
    {
        super(newLocationCoordinate, newDuration);
        this.siteName = siteName;
        this.siteType = siteType;
        this.siteDescription = siteDescription;
        this.siteAccessibility = siteAccessibility;
    }

    public Site(double newCoordinateX, double newCoordinateY, double newDuration, String siteName, Classification siteType, String siteDescription, boolean siteAccessibility)
    {
        super(newCoordinateX, newCoordinateY, newDuration);
        this.siteName = siteName;
        this.siteType = siteType;
        this.siteDescription = siteDescription;
        this.siteAccessibility = siteAccessibility;
    }

    public Site (int newContentID, Location newLocationCoordinate, double newDuration)
    {
        super(newContentID, newLocationCoordinate, newDuration);
        this.siteName = "";
        this.siteType = Classification.NONE;
        this.siteDescription = "";
        this.siteAccessibility = false;
    }

    public String getSiteName()
    {
        return siteName;
    }

    public void setSiteName(String siteName)
    {
        this.siteName = siteName;
    }

    public Location getLocation()
    {
        return this.getLocationCoordinate();
    }

    public void setLocation(Location location)
    {
        this.setLocationCoordinate(location);
    }

    public Classification getSiteType()
    {
        return siteType;
    }

    public String getSityTypeToString()
    {
        switch(siteType)
        {
            case PARK:
                return "Park";
            case HOTEL:
                return "Hotel";
            case STORE:
                return "Store";
            case CINEMA:
                return "Cinema";
            case MUSEUM:
                return "Museum";
            case RESTAURANT:
                return "Restaurant";
            case HISTORICAL:
                return "Historical";
            case PUBLIC_SITE:
                return "Public Site";
            default:
                return "New Site Classification";
        }
    }


    public void setSiteType(Classification siteType)
    {
        this.siteType = siteType;
    }

    public String getSiteDescription()
    {
        return siteDescription;
    }

    public void setSiteDescription(String siteDescription)
    {
        this.siteDescription = siteDescription;
    }

    public boolean isSiteAccessibility()
    {
        return siteAccessibility;
    }

    public void setSiteAccessibility(boolean siteAccessibility)
    {
        this.siteAccessibility = siteAccessibility;
    }

    public void out()
    {

    }

    public void out(Role role)
    {
        if (role != Role.USER && role != Role.MEMBER)
        {
            System.out.println("\tID: " + getContendID());
            System.out.println("\tLocation:");
            System.out.println("\t\tCoordinate X " + this.getLocationCoordinate().getCoordinateX());
            System.out.println("\t\tCoordinate Y " + this.getLocationCoordinate().getCoordinateY());
        }

        System.out.println("\tName: " + siteName);
        System.out.println("\tType: " + siteType);
        System.out.println("\tDuration: " + this.getContentDuration());
        System.out.println("\tDescription: " + siteDescription);
        System.out.println("\tAccessibility: " + ((siteAccessibility) ? "True" : "False"));
        System.out.println("\tName: " + this.getSiteName());
    }
}
