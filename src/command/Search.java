package command;

import src.product.City;
import src.product.content.ContentInterestingPlaces;

public class Search {
    public City searchByCity(String request)
    {
        City city = new City();
        return city;
    }

    public ContentInterestingPlaces searchByContent(String request)
    {
        return new ContentInterestingPlaces();
    }
}
