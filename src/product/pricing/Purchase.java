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

	public Purchase(int purchaseID, Date dateOfPurchase, int[] purchasedCityID, int[] purchasedMapID, int purchasedCost, String purchaseType)
	{
		this.purchaseID = purchaseID;
		this.dateOfPurchase=dateOfPurchase;
		this.purchasedCityID=purchasedCityID;
		this.purchasedMapID=purchasedMapID;
		this.purchasedCost=purchasedCost;
		if (purchaseType.equals("One time purchase"))
			this.purchaseType=PurchaseType.ONE_TIME_PURCHASE;
		else
			this.purchaseType=PurchaseType.SUBSCRIPTION;
		this.digitalMap=new ArrayList<DigitalMap>();
	}

	public int getPurchaseID() {
		return purchaseID;
	}

	public String getCostByString() {
		return String.valueOf(purchasedCost);
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

	public String getPurchasedMapNumberByString() {
		return String.valueOf(purchasedMapID.length);
	}

	public int getPurchasedCost() {
		return purchasedCost;
	}

	public PurchaseType getPurchaseType(){
		return purchaseType;
	}

	public String getPurchaseTypeInString(){
		switch(purchaseType)
		{
			case ONE_TIME_PURCHASE: return "One time purchase";
			default: return "Subscription";
		}
	}


	public void AddDigitalMap(DigitalMap map) {
		this.digitalMap.add(map);
	}

	public List<DigitalMap> DownloadAcquisition(){
		return digitalMap;
	}
}
