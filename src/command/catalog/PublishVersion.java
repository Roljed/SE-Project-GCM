package command.catalog;

import product.City;
import product.DigitalMap;
import product.Tour;
import product.content.Content;
import product.content.Site;

import java.util.List;

public class PublishVersion extends Catalog
{
    private boolean versionApproved = false;

    public PublishVersion(List<Site> contents, List<DigitalMap> digitalMaps, List<Tour> tours, List<City> cities)
    {
        super(contents, digitalMaps, tours, cities);
    }

    public void viewCatalog()
    {
        if (versionApproved == false)
        {
            System.out.println("This version not approved and will not be displayed");
        }
        else
        {
            super.viewCatalog();
        }
    }

    public void approveVersion()
    {
        this.versionApproved = true;
    }
}
