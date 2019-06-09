package user.manager;
import client.ChatClient;
import database.ModelDataArchive;
import database.report.Report;
import database.report.ReportActivity;
import product.content.Content;
import command.Editor;
import user.Role;
import user.worker.Worker;

abstract class Manager extends Worker implements Editor
{
	private ReportActivity reporter;
	private ModelDataArchive archiver;

	public Manager(String namePersonal_, String nameUser_, String password_, int phoneNumber_, String email_, ChatClient chat_){
		super(namePersonal_,nameUser_,password_,phoneNumber_,email_,chat_);
		reporter = new ReportActivity(namePersonal_);
		archiver = new ModelDataArchive();
		role = Role.MANAGER;
	}

	public void viewReport(){
		reporter.PrintReport();
	}

	public void createCity(String cityName){
		editor.createCity(cityName);
	}

	public void editElectronicMap(int mapID){
		editor.editElectronicMap(mapID);
	}

	public void editCity(int cityID){
		editor.editCity(cityID);
	}

	public void addNewElectronicMapToCity(int cityID,ElectronicMap map){
		editor.addNewElectronicMapToCity(cityID,map);
	}

	public void addNewContent(int cityID, Content content){
		editor.addNewContent(cityID,content);
	}

	public void editContent(int contentID){
		editor.editContent(contentID);
	}

	public void addNewRoute(){
		editor.addNewRoute();
	}

	public void editRoute(int routeID){
		editor.editRoute(routeID);
	}

	public void sendPublishVersion(){
		editor.sendPublishVersion();
	}

	public void newDateInterrupt(){
		archiver.newDateInterrupt();
	}

	public ReportActivity createDailyReport(){
		return archiver.createDailyReport();
	}

	public void addDailyReport(Report report){
		archiver.addDailyReport(report);
	}
}
