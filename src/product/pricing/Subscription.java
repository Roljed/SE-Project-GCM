package product.pricing;

import java.util.Calendar;
import java.util.Date;

import command.Message;

/**
*
* @version 1
* @author Avi Ayeli
*/


public class Subscription extends Acquisition{
	private Date dateOfExpiration;
	
	public Subscription(int[] purchasedCityID,int[] purchasedMapID,int purchasedCost,AcquisitionType acquisitionType) {
		super(purchasedCityID,purchasedMapID,purchasedCost,acquisitionType);
		dateOfExpiration=new Date();
		if (acquisitionType == AcquisitionType.Subscription)
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
	
	public void SendAdvanceNotice() {
		Message.advanceNotice();
	}
	
	public void SendEndSubscription() {
		Message.endSubscription();
	}
	
	public void SendNewVersion() {
		Message.newVersion();
	}
}
