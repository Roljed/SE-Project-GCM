package product.pricing;

import java.util.Date;

import user.UserRole.Role;



/**
*
* @version 1
* @author Avi Ayeli
*/

public class MapCost
{
    private double price;
    private int mapID;
    private String lastApproval;
    private Date lastModifiedDate;
    
    public MapCost(double price,int mapID,String lastApproval)
    {
    	this.price=price;
    	this.mapID=mapID;
    	this.lastApproval=lastApproval;
    	this.lastModifiedDate=new Date();
    }

    public double getPrice() {
        return price;
    }
    
    public int getMapID() {
        return mapID;
    }
    
    public String getLastApproval() {
    	return lastApproval;
    }
    
    public Date getLastModifiedDate() {
    	return lastModifiedDate;
    }


    public boolean setPrice(double price,String approvalName,Role role) {
        if (role == Role.MANAGER)
        {
        	this.price=price;
        	this.lastApproval=approvalName;
        	this.lastModifiedDate=new Date();
        	return true;
        }
        return false;
    }

}
