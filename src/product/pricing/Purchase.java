package product.pricing;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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

	private int userID;
	private int purchaseID;
	private static int purchaseIDNext = 0;
	private Date dateOfPurchase;
	private int purchasedCityID;
	private int[] purchasedMapID;
	private double purchasedCost;
	private PurchaseType purchaseType;
	private List<DigitalMap> digitalMap;

	public Purchase(int userID, int purchasedCityID, int[] purchasedMapID, double purchasedCost, PurchaseType purchaseType)
	{
		this.userID = userID;
		this.purchasedCityID = purchasedCityID;
		purchaseID = purchaseIDNext++;
		this.dateOfPurchase = new Date();
		this.purchasedMapID=purchasedMapID;
		this.purchasedCost=purchasedCost;
		this.purchaseType = purchaseType;
		this.digitalMap=new ArrayList<DigitalMap>();
	}

	public Purchase(int userID, int purchaseID, Date dateOfPurchase, int purchasedCityID, int[] purchasedMapID, double purchasedCost, String purchaseType)
	{
		this.userID = userID;
		this.purchaseID = purchaseID;
		this.dateOfPurchase = dateOfPurchase;
		this.purchasedCityID=purchasedCityID;
		this.purchasedMapID=purchasedMapID;
		this.purchasedCost=purchasedCost;
		if (purchaseType.equals("One time purchase"))
			this.purchaseType=PurchaseType.ONE_TIME_PURCHASE;
		else
			this.purchaseType=PurchaseType.SUBSCRIPTION;
		this.digitalMap=new ArrayList<DigitalMap>();
	}

	public int getUserID() {
		return userID;
	}

	public String getUserIDByString()
	{
		return Integer.toString(this.userID);
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public void setUserIDFromString(String userID)
	{
		this.userID = Integer.parseInt(userID);
	}


	public int getPurchaseID() {
		return purchaseID;
	}

	public String getPurchaseIDByString() {
		return String.valueOf(purchaseID);
	}

	public String getCostByString() {
		return String.valueOf(purchasedCost);
	}

	public Date getDateOfPurchase() {
		return dateOfPurchase;
	}

	public String getPurchasedCityIDByString() {
		return String.valueOf(purchasedCityID);
	}

	public String getPurchasedMapIDByString() {
		String tmp="";
		for(int i=0; i<purchasedMapID.length; i++)
			tmp=tmp+","+String.valueOf(purchasedMapID[i]);
		return tmp;
	}
	public String getDateOfPurchaseByString() {
		String pattern = "dd/MM/yyyy";
		DateFormat df = new SimpleDateFormat(pattern);
		String date = df.format(dateOfPurchase);
		return date;
	}

	public int getPurchasedCityID() {
		return purchasedCityID;
	}

	public int[] getPurchasedMapID() {
		return purchasedMapID;
	}

	public String getPurchasedMapNumberByString() {
		return String.valueOf(purchasedMapID.length);
	}

	public double getPurchasedCost() {
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
