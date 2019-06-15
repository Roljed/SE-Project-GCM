package command;

import java.io.Serializable;
import java.time.LocalDate;

/**
*
* @version 1
* @author Avi Ayeli
*/

public abstract class Report implements Serializable
{
	private String activeManager;
	private LocalDate date;
	
	Report(String activeManager){
		this.activeManager=activeManager;
		date = LocalDate.now();
	}
	
	public String getActiveManager() {
		return activeManager;
	}
	
	public LocalDate getDate() {
		return date;
	}
	
	public abstract void PrintReport();

}