package product.pricing;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import product.DigitalMap;

/**
*
* @version 1
* @author Avi Ayeli
*/

public class Purchase implements Serializable
{
	private int purchaseID;
	private static int purchaseIDNext = 0;
	private Date dateOfPurchase;
	private int[] purchasedCityID;
	private int[] purchasedMapID;
	private int purchasedCost;
	private PurchaseType purchaseType;
	private List<DigitalMap> digitalMap;
	
	public Purchase(int[] purchasedCityID, int[] purchasedMapID, int purchasedCost, PurchaseType purchaseType)
	{
		purchaseID = purchaseIDNext++;
		this.dateOfPurchase=new Date();
		this.purchasedCityID=purchasedCityID;
		this.purchasedMapID=purchasedMapID;
		this.purchasedCost=purchasedCost;
		this.purchaseType = purchaseType;
		this.digitalMap=new ArrayList<DigitalMap>();
	}
	
	public Date getDateOfPurchase() {
		return dateOfPurchase;
	}
	
	public int[] getPurchasedCityID() {
		return purchasedCityID;
	}
	
	public int[] getPurchasedMapID() {
		return purchasedMapID;
	}
	
	public int getPurchasedCost() {
		return purchasedCost;
	}
	
	public PurchaseType getPurchaseType(){
		return purchaseType;
	}
	
	public void AddDigitalMap(DigitalMap map) {
		this.digitalMap.add(map);
	}
	
	public List<DigitalMap> DownloadAcquisition(){
		return digitalMap;
	}
}
