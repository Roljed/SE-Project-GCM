package product.content;

import user.Permission;

import java.io.Serializable;

/**
 * Implements interesting places on the DigitalMap
 *
 * @version 1
 * @author Yaad Nahshon
 */
public class Site extends Content implements Serializable
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

    public Site(int newContentID, Location newLocationCoordinate, double newDuration, String siteName, String siteType, String siteDescription, boolean siteAccessibility)
    {
        super(newContentID,newLocationCoordinate, newDuration);
        this.siteName = siteName;
        this.siteDescription = siteDescription;
        this.siteAccessibility = siteAccessibility;
        this.siteType = getSiteTypeStringToClassification(siteType);
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

    public String getSiteTypeToString()
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

    private Classification getSiteTypeStringToClassification(String type)
    {
        type = type.toLowerCase();
        String cap = type.substring(0, 1).toUpperCase() + type.substring(1);
        switch (cap)
        {
            case "Park":
                return Classification.PARK;
            case "Hotel":
                return Classification.HOTEL;
            case "Store":
                return Classification.STORE;
            case "Cinema":
                return Classification.CINEMA;
            case "Museum":
                return Classification.MUSEUM;
            case "Restaurant":
                return Classification.RESTAURANT;
            case "Historical":
                return Classification.HISTORICAL;
            case "Public Site":
                return Classification.PUBLIC_SITE;
            default:
                return Classification.NONE;
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

}
