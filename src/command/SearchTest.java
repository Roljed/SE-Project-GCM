package command;


import junit.framework.Assert;
import junit.framework.TestCase;

import command.catalog.Catalog;
import org.junit.Test;
import product.City;
import product.DigitalMap;
import product.ProductType;
import product.Tour;
import product.content.Content;
import product.content.Location;
import product.content.Site;
import product.pricing.MapCost;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


/**
 * Test Search class methods
 *
 * @version 1
 * @author Yaad Nahshon
 */
public class SearchTest extends TestCase
{
    private Location locationCinema;
    private Location locationHotel;
    private Site cinema;
    private Site hotel;

    private DigitalMap cinemaMap;
    private DigitalMap hotelMap;
    private HashMap<Integer, DigitalMap> cityMaps;

    private Tour tour;
    private HashMap<Integer, Tour> cityTours;

    private City city;
    private List<City> cityList;

    private Catalog cityCatalog;
    private Catalog siteCatalog;


    @Override
    protected void setUp() throws Exception {
        super.setUp();

        locationCinema = new Location(40, 60);
        cinema = new Site(1, locationCinema, 20, "Hello", "Cinema", "City's cinema", false);
        HashMap<Integer, Content> digitalMapContentsCinema = new HashMap<>();
        digitalMapContentsCinema.put(cinema.getContendID(), cinema);
        MapCost mapCostCinema = new MapCost(150, 2, "10-03-1991");
        cinemaMap = new DigitalMap(2, 3, "A map of the northern area of the city of Afula", digitalMapContentsCinema, mapCostCinema);

        locationHotel = new Location(300, 555);
        hotel = new Site(4, locationHotel, 30,"world" ,"Hotel", "Where we stay", true);
        HashMap<Integer, Content> digitalMapContentsHotel = new HashMap<>();
        digitalMapContentsHotel.put(hotel.getContendID(), hotel);
        MapCost mapCostHotel = new MapCost(170, 3, "03-03-2001");
        hotelMap = new DigitalMap(3, 1, "A map of the western Galilee", digitalMapContentsHotel, mapCostHotel);

        cityMaps = new HashMap<>();
        cityMaps.put(cinemaMap.getDigitalMapID(), cinemaMap);
        cityMaps.put(hotelMap.getDigitalMapID(), hotelMap);

        List<Site> tourSequence = new ArrayList<>();
        tourSequence.add(hotel);
        siteCatalog = new Catalog(tourSequence, null, null, null);  // will be tested
        tourSequence.add(cinema);
        tour = new Tour(1, "Historical", "Historical city tour", tourSequence, 50);
        cityTours.put(tour.getTourID(), tour);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = format.parse("02-11-1554");
        city = new City(565, "CityTest", cityMaps, cityTours, 9, 4, date);
        cityList = new ArrayList<>();
        cityList.add(city);

        cityCatalog = new Catalog(null, null, null, cityList); // will be tested
    }

    @Test
    public void testSearchByID()
    {
        Catalog expected = siteCatalog;
        Object result = Search.searchByID(hotel.getContendID(), ProductType.CONTENT);
        Assert.assertEquals(expected, result);
    }

    @Test
    public void testSearchByCityName()
    {
        Catalog expected = cityCatalog;
        Catalog result = Search.searchByCityName("CityTest");
        Assert.assertEquals(expected, result);
    }

    @Test
    public void testSearchBySite()
    {
        Catalog expected = siteCatalog;
        Catalog result = Search.searchBySite("Hello");
        Assert.assertEquals(expected, result);
    }
}
