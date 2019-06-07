package product.catalog;

import java.util.List;

import product.City;
import product.DigitalMap;
import product.Tour;
import product.content.Content;
import user.Role;

/**
 * Catalog of all products that can be accessed directly by all users
 *
 * @version 1
 * @author Yaad Nahshon
 */
public class Catalog
{
    private Role role = Role.USER;

    private List<Content> contents;
    private List<DigitalMap> digitalMaps;
    private List<Tour> tours;
    private List<City> cities;

    public Catalog(List<Content> contents, List<DigitalMap> digitalMaps, List<Tour> tours, List<City> cities)
    {
        this.contents = contents;
        this.digitalMaps = digitalMaps;
        this.tours = tours;
        this.cities = cities;
    }

    public void setRole(Role role)
    {
        this.role = role;
    }

    public void viewCatalog()
    {
        if (contents.isEmpty() == false)
        {
            System.out.println("List of Contents:");
            for (Content content : contents)
            {
                content.out(this.role);
                System.out.println("********************");
            }
            System.out.println();
        }

        if (digitalMaps.isEmpty() == false)
        {
            System.out.println("List of Maps:");
            for (DigitalMap map : digitalMaps)
            {
                map.out(this.role);
                System.out.println("********************");
            }
            System.out.println();
        }

        if (tours.isEmpty() == false)
        {
            System.out.println("List of Tours:");
            for (Tour tour : tours)
            {
                tour.out(this.role);
                System.out.println("********************");
            }
            System.out.println();
        }

        if (cities.isEmpty() == false)
        {
            System.out.println("List of Cities:");
            for (City city : cities)
            {
                city.out(this.role);
                System.out.println("********************");
            }
            System.out.println();
        }
    }
}
