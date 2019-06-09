package database.report;

import java.util.Date;

/**
*
* @version 1
* @author Avi Ayeli
*/

public abstract class Report
{
	private String activeManager;
	private Date date;
	
	Report(String activeManager){
		this.activeManager=activeManager;
		date=new Date();
	}
	
	public String getActiveManager() {
		return activeManager;
	}
	
	public Date getDate() {
		return date;
	}
	
	public abstract void PrintReport();

}