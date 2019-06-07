package product;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Second product - City
 *
 * @version 1
 * @author Yaad Nahshon
 */
public class City
{
    private int cityID;
    private static int cityIDNext = 0;
    private String cityName;
    private HashMap<Integer, DigitalMap> cityMaps;
    private HashMap<Integer, Tour> cityTours;
    private double cityPrice;
    private int cityVersion;
    private Date updateVersionDate;

    public City(String cityName, HashMap<Integer, DigitalMap> cityMaps, HashMap<Integer, Tour> cityTours, double cityPrice, int cityVersion, Date updateVersionDate)
    {
        this.cityID = cityIDNext++;
        this.cityName = cityName;
        this.cityMaps = cityMaps;
        this.cityTours = cityTours;
        this.cityVersion = cityVersion;
        if (cityPrice <= 0 && cityMaps.isEmpty() == false)
        {
            this.cityPrice = calculateCityPrice();
        }
        else
        {
            this.cityPrice = cityPrice;
        }
        this.updateVersionDate = updateVersionDate; //TODO make function to find last update
    }

    private double calculateCityPrice()
    {
        double price = 0;
        for (Map.Entry<Integer, DigitalMap> digitalMap : this.cityMaps.entrySet())
        {
            price += digitalMap.getValue().getDigitalMapCost().getPrice();
        }
        return price;
    }

    public int countCityContent()
    {
        int count = 0;
        for (Map.Entry<Integer, DigitalMap> digitalMap : this.cityMaps.entrySet())
        {
            count += digitalMap.getValue().getDigitalMapContents().size();
        }
        return count;
    }

    public int getCityID()
    {
        return cityID;
    }

    public String getCityName()
    {
        return cityName;
    }

    public void setCityName(String cityName)
    {
        this.cityName = cityName;
    }

    public HashMap<Integer, DigitalMap> getCityMaps()
    {
        return cityMaps;
    }

    public void setCityMaps(HashMap<Integer, DigitalMap> cityMaps)
    {
        this.cityMaps = cityMaps;
    }

    public HashMap<Integer, Tour> getCityTours()
    {
        return cityTours;
    }

    public void setCityTours(HashMap<Integer, Tour> cityTours)
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

    public Date getUpdateVersionDate()
    {
        return updateVersionDate;
    }

    public void setUpdateVersionDate(Date updateVersionDate)
    {
        this.updateVersionDate = updateVersionDate;
    }

    public int getVersion()
    {
        return cityVersion;
    }

    public void setCityVersionVersion(int cityVersion)
    {
        this.cityVersion = cityVersion;
        setUpdateVersionDate(new Date());
    }

    public void updateVersion()
    {
        this.cityVersion += 1;
    }
}
