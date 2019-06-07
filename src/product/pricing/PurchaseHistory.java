package product.pricing;

import java.util.ArrayList;
import java.util.List;

/**
*
* @version 1
* @author Avi Ayeli
*/

public class PurchaseHistory {
	private List<Acquisition> lisOfAcquisition;
		
	public PurchaseHistory() {
		lisOfAcquisition=new ArrayList<Acquisition>();
	}
	
	public List<Acquisition> getLisOfAcquisition(){
		return lisOfAcquisition;
	}
	
	public void AddAcquisition(Acquisition acquisition) {
		this.lisOfAcquisition.add(acquisition);
	}
}
