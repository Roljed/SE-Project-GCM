package product;


/**
 *  Class for parsing to CatalogScreenView City data
 *
 * @author Yaad Nahshon
 */
public class CityToDisplay
{
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumberMap() {
        return numberMap;
    }

    public void setNumberMap(String numberMap) {
        this.numberMap = numberMap;
    }

    public String getNumberSite() {
        return numberSite;
    }

    public void setNumberSite(String numberSite) {
        this.numberSite = numberSite;
    }

    public String getNumberTour() {
        return numberTour;
    }

    public void setNumberTour(String numberTour) {
        this.numberTour = numberTour;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private String name;
    private String numberMap;
    private String numberSite;
    private String numberTour;
    private String price;
    private String description;

    public CityToDisplay(String name, String numberMaps, String numberSites, String numberTour, String price, String description) {
        this.name = name;
        this.numberMap = numberMaps;
        this.numberSite = numberSites;
        this.numberTour = numberTour;
        this.price = price;
        this.description = description;
    }
}

