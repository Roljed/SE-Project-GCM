package product.pricing;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import product.DigitalMap;

/**
*
* @version 1
* @author Avi Ayeli
*/

public class Acquisition {
	private Date dateOfAcquisition;
	private int[] purchasedCityID;
	private int[] purchasedMapID;
	private int purchasedCost;
	private AcquisitionType acquisitionType;
	private List<DigitalMap> digitalMap;
	
	public Acquisition(int[] purchasedCityID,int[] purchasedMapID,int purchasedCost,AcquisitionType acquisitionType) {
		this.dateOfAcquisition=new Date();
		this.purchasedCityID=purchasedCityID;
		this.purchasedMapID=purchasedMapID;
		this.purchasedCost=purchasedCost;
		this.acquisitionType=acquisitionType;
		this.digitalMap=new ArrayList<DigitalMap>();
	}
	
	public Date getDateOfAcquisition() {
		return dateOfAcquisition;
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
	
	public AcquisitionType getAcquisitionType(){
		return acquisitionType;
	}
	
	public void AddDigitalMap(DigitalMap map) {
		this.digitalMap.add(map);
	}
	
	public List<DigitalMap> DownloadAcquisition(){
		return digitalMap;
	}
}
