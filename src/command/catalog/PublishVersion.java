package command.catalog;

import product.City;
import product.DigitalMap;
import product.Tour;
import product.content.Content;
import product.content.Site;

import java.io.Serializable;
import java.util.List;


/**
 * Content of all approved packages for company manager approval
 *
 * @version 1
 * @author Avi Ayeli
 */
public class PublishVersion extends Catalog implements Serializable
{
    private boolean versionApproved = false;

    public PublishVersion(List<Site> contents, List<DigitalMap> digitalMaps, List<Tour> tours, List<City> cities)
    {
        super(contents, digitalMaps, tours, cities);
    }

    public void approveVersion()
    {
        this.versionApproved = true;
    }
}
