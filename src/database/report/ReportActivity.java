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
	private int countSubscriptions;
	private int countRenewSubscriptions;
	private int countView;
	private int countDownload;
	
	public ReportActivity(String activeManager) {
		super(activeManager);
		countMaps=0;
		countSinglePurchase=0;
		countSubscriptions =0;
		countRenewSubscriptions =0;
		countView=0;
		countDownload=0;
	}
	
	public int getCountMaps() {
		return countMaps;
	}
	
	public int getCountSinglePurchase() {
		return countSinglePurchase;
	}
	
	public int getCountSubscriptions() {
		return countSubscriptions;
	}
	
	public int getCountRenewSubscriptions() {
		return countRenewSubscriptions;
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
		countSubscriptions +=add;
	}
	
	public void AddCountRenewSubsctiptions(int add) {
		countRenewSubscriptions +=add;
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
		System.out.println("Number of subscriptions: "+ countSubscriptions);
		System.out.println("Number of renew subscriptions: "+ countRenewSubscriptions);
		System.out.println("Number of views: "+countView);
		System.out.println("Number of downloads: "+countDownload);
		System.out.println("Active Manager: "+this.getActiveManager());
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		System.out.println("Date: "+formatter.format(this.getDate()));
	}
}