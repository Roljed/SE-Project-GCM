package product;

import java.util.Date;
import java.util.List;

/**
 * Second product - City
 *
 * @version 1
 * @author Yaad Nahshon
 */
public class City
{
    private int cityID;
    private String cityName;
    private List<DigitalMap> cityMaps;
    private List<Tour> cityTours;
    private double cityPrice;
    private Date lastCityMapsVersionUpdate;

    public City(int cityID, String cityName, List<DigitalMap> cityMaps, List<Tour> cityTours, double cityPrice, Date lastCityMapsVersionUpdate)
    {
        this.cityID = cityID;
        this.cityName = cityName;
        this.cityMaps = cityMaps;
        this.cityTours = cityTours;
        if (cityPrice <= 0 && cityMaps.isEmpty() == false)
        {
            this.cityPrice = calculateCityPrice(cityMaps);
        }
        else
        {
            this.cityPrice = cityPrice;
        }
        this.lastCityMapsVersionUpdate = lastCityMapsVersionUpdate;
    }

    private double calculateCityPrice(List<DigitalMap> cityMaps)
    {
        double price = 0;
        for (DigitalMap digitalMap : cityMaps)
        {
            price += digitalMap.getDigitalMapCost().getPrice();
        }
        return price;
    }

    public int getCityID()
    {
        return cityID;
    }

    public void setCityID(int cityID)
    {
        this.cityID = cityID;
    }

    public String getCityName()
    {
        return cityName;
    }

    public void setCityName(String cityName)
    {
        this.cityName = cityName;
    }

    public List<DigitalMap> getCityMaps()
    {
        return cityMaps;
    }

    public void setCityMaps(List<DigitalMap> cityMaps)
    {
        this.cityMaps = cityMaps;
    }

    public List<Tour> getCityTours()
    {
        return cityTours;
    }

    public void setCityTours(List<Tour> cityTours)
    {
        this.cityTours = cityTours;
    }

    public double getCityPrice()
    {
        return cityPrice;
    }

    public void setCityPrice(double cityPrice)
    {
        this.cityPrice = cityPrice;
    }

    public Date getLastCityMapsVersionUpdate()
    {
        return lastCityMapsVersionUpdate;
    }

    public void setLastCityMapsVersionUpdate(Date lastCityMapsVersionUpdate)
    {
        this.lastCityMapsVersionUpdate = lastCityMapsVersionUpdate;
    }
}
