package product.pricing;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import user.Permission;

/**
 * Container for map prices
 *
 * @version 1
 * @author Avi Ayeli
*/

public class MapCost implements Serializable
{
    private double price;
    private int mapID;
    private String lastApproval;
    private LocalDate lastModifiedDate;
    
    public MapCost(double price,int mapID,String lastApproval)
    {
    	this.price=price;
    	this.mapID=mapID;
    	this.lastApproval=lastApproval;
    	this.lastModifiedDate = LocalDate.now();
    }

    public double getPrice()
    {
        return price;
    }

    public String getPriceToString()
    {
        return String.valueOf(price);
    }
    
    public int getMapID()
    {
        return mapID;
    }
    
    public String getLastApproval()
    {
    	return lastApproval;
    }
    
    public LocalDate getLastModifiedDate()
    {
    	return lastModifiedDate;
    }

    public String getLastModifiedDateToString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return formatter.format(lastModifiedDate);
    }

    public void setPrice(double price, String approvalName, Permission permission)
    {
        if (permission == Permission.CONTENT_MANAGER)
        {
        	this.price=price;
        	this.lastApproval=approvalName;
        	this.lastModifiedDate = LocalDate.now();
        }
    }
}
