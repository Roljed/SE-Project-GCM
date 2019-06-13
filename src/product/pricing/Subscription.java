package product.pricing;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
*
* @version 1
* @author Avi Ayeli
*/


public class Subscription extends Purchase implements Serializable
{
	private Date dateOfExpiration;
	
	public Subscription(int[] purchasedCityID, int[] purchasedMapID, int purchasedCost, PurchaseType purchaseType) {
		super(purchasedCityID,purchasedMapID,purchasedCost, purchaseType);
		dateOfExpiration=new Date();
		if (purchaseType == PurchaseType.SUBSCRIPTION)
		{
			Calendar cal = Calendar.getInstance();
			cal.setTime(dateOfExpiration);
			cal.set(Calendar.MONTH, (cal.get(Calendar.MONTH)+6));
			dateOfExpiration = cal.getTime();
		}
	}
	
	public Date getDateOfExpiration(){
		return dateOfExpiration;
	}
	
	public void RenewSubscription(Date dateOfExpiration) {
		this.dateOfExpiration=dateOfExpiration;
	}

}
