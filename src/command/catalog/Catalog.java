package command.catalog;

import java.io.Serializable;
import java.util.List;

import product.City;
import product.DigitalMap;
import product.Tour;
import product.content.Content;
import product.content.Site;
import user.Permission;

/**
 * Catalog of all products that can be accessed directly by all users
 *
 * @version 1
 * @author Yaad Nahshon
 */
public class Catalog implements Serializable
{
    private Permission permission = Permission.USER;

    private List<Site> contents;
    private List<DigitalMap> digitalMaps;
    private List<Tour> tours;
    private List<City> cities;

    private boolean empty = false;

    public Catalog(List<Site> contents, List<DigitalMap> digitalMaps, List<Tour> tours, List<City> cities)
    {
        if (!contents.isEmpty() || !digitalMaps.isEmpty() || !tours.isEmpty() || !cities.isEmpty())
        {
            empty = true;
        }
        this.contents = contents;
        this.digitalMaps = digitalMaps;
        this.tours = tours;
        this.cities = cities;
    }

    public List<Site> getContents() {
        return contents;
    }

    public void setContents(List<Site> contents) {
        this.contents = contents;
    }

    public List<DigitalMap> getDigitalMaps() {
        return digitalMaps;
    }

    public void setDigitalMaps(List<DigitalMap> digitalMaps) {
        this.digitalMaps = digitalMaps;
    }

    public List<Tour> getTours() {
        return tours;
    }

    public void setTours(List<Tour> tours) {
        this.tours = tours;
    }

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    public boolean isEmpty() {
        return empty;
    }

    public void setEmpty(boolean empty) {
        this.empty = empty;
    }

    public void setPermission(Permission permission)
    {
        this.permission = permission;
    }

    public void viewCatalog()
    {
        if (contents.isEmpty() == false)
        {
            System.out.println("List of Contents:");
            for (Content content : contents)
            {
                content.out(this.permission);
                System.out.println("********************");
            }
            System.out.println();
        }

        if (digitalMaps.isEmpty() == false)
        {
            System.out.println("List of Maps:");
            for (DigitalMap map : digitalMaps)
            {
                map.out(this.permission);
                System.out.println("********************");
            }
            System.out.println();
        }

        if (tours.isEmpty() == false)
        {
            System.out.println("List of Tours:");
            for (Tour tour : tours)
            {
                tour.out(this.permission);
                System.out.println("********************");
            }
            System.out.println();
        }

        if (cities.isEmpty() == false)
        {
            System.out.println("List of Cities:");
            for (City city : cities)
            {
                city.out(this.permission);
                System.out.println("********************");
            }
            System.out.println();
        }
    }
}
