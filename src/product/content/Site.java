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
        CINEMA,
        HISTORICAL,
        HOTEL,
        MUSEUM,
        PARK,
        PUBLIC_SITE,
        RESTURANT,
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
