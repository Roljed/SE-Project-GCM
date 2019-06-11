package product;

import user.Role;

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

    public City(int cityID, String cityName, HashMap<Integer, DigitalMap> cityMaps, HashMap<Integer, Tour> cityTours, double cityPrice, int cityVersion, Date updateVersionDate)
    {
        this.cityID = cityID;
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

    public String getCityIDToString()
    {
        return String.valueOf(cityID);
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

    public String getCityPriceToString()
    {
        return String.valueOf(cityPrice);
    }

    public void setCityPrice(double cityPrice)
    {
        this.cityPrice = cityPrice;
    }

    public Date getUpdateVersionDate()
    {
        return updateVersionDate;
    }

    public String getUpdateVersionDateToString()
    {
        return String.valueOf(updateVersionDate);
    }


    public void setUpdateVersionDate(Date updateVersionDate)
    {
        this.updateVersionDate = updateVersionDate;
    }

    public int getVersion()
    {
        return cityVersion;
    }

    public String getVersionToString()
    {
        return String.valueOf(cityVersion);
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

    public void out(Role role)
    {
        if (role != null && (role != Role.USER && role != Role.MEMBER))
        {
            System.out.println("\tID: " + cityID);
        }
        System.out.println("\tCity Name: " + cityName);
        System.out.println("\tMap Count:" + cityMaps.size());

        int countContent = 0;
        for (Map.Entry<Integer, DigitalMap> map : cityMaps.entrySet())
        {
            System.out.println("\t\tMap Description" + map.getValue().getDigitalMapDescription());
            countContent += map.getValue().getDigitalMapContents().size();
        }

        System.out.println("\tNumber Of Contents: " + countContent);
        System.out.println("\tNumber Of Tours: " + cityTours.size());
    }
}
