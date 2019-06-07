package database.report;

/**
*
* @version 1
* @author Avi Ayeli
*/

public class ReportActivity implements Report
{
	private int countMaps;
	private int countSinglePurchase;
	private int countSubsctiptions;
	private int countRenewSubsctiptions;
	private int countView;
	private int countDownload;
	
	public ReportActivity() {
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
}
