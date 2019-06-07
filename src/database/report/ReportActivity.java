package database.report;

import java.text.SimpleDateFormat;

/**
*
* @version 1
* @author Avi Ayeli
*/

public class ReportActivity extends Report
{
	private int countMaps;
	private int countSinglePurchase;
	private int countSubsctiptions;
	private int countRenewSubsctiptions;
	private int countView;
	private int countDownload;
	
	public ReportActivity(String activeManager) {
		super(activeManager);
		countMaps=0;
		countSinglePurchase=0;
		countSubsctiptions=0;
		countRenewSubsctiptions=0;
		countView=0;
		countDownload=0;
	}
	
	public int getCountMaps() {
		return countMaps;
	}
	
	public int getCountSinglePurchase() {
		return countSinglePurchase;
	}
	
	public int getCountSubsctiptions() {
		return countSubsctiptions;
	}
	
	public int getCountRenewSubsctiptions() {
		return countRenewSubsctiptions;
	}
	
	public int getCountView() {
		return countView;
	}
	
	public int getCountDownload() {
		return countDownload;
	}
	
	public void AddCountMaps(int add) {
		countMaps=+add;
	}
	
	public void AddCountSinglePurchase(int add) {
		countSinglePurchase+=add;
	}
	
	public void AddCountSubsctiptions(int add) {
		countSubsctiptions+=add;
	}
	
	public void AddCountRenewSubsctiptions(int add) {
		countRenewSubsctiptions+=add;
	}
	
	public void AddCountView(int add) {
		countView+=add;
	}
	
	public void AddCountDownload(int add) {
		countDownload+=add;
	}
	
	public void PrintReport() {
		System.out.println("Activity Report:");
		System.out.println("Number of maps: "+countMaps);
		System.out.println("Number of single purchase: "+countSinglePurchase);
		System.out.println("Number of subsctiptions: "+countSubsctiptions);
		System.out.println("Number of renew subsctiptions: "+ countRenewSubsctiptions);
		System.out.println("Number of views: "+countView);
		System.out.println("Number of downloads: "+countDownload);
		System.out.println("Active Manager: "+this.getActiveManager());
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		System.out.println("Date: "+formatter.format(this.getDate()));
	}
}