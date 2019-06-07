package user.manager;
import client.ClientConsole;
import product.content.Editor;
import user.UserRole.Role;
import user.worker.Worker;

abstract class Manager extends Worker implements Editor
{
	private ContentEdit editor;
	private ActivityReport reporter;
	private DataArchive archiver;
	
	public Manager(String namePersonal_, String nameUser_, String password_, int phoneNumber_, String email_, ClientConsole client_){
		super(namePersonal_,nameUser_,password_,phoneNumber_,email_,client_);
		editor = new ContentEdit(client);
		reporter = new ActivityReport(client);
		archiver = new DataArchive();
		this.userRole = Role.MANAGER;
	}
	
	public void viewReport(){
		reporter.showReport();
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
	
	public void addNewContent(int cityID,PlaceOfInterest content){
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
	
	public void createDailyReport(){
		archiver.createDailyReport();
	}
	
	public void addDailyReport(){
		archiver.addDailyReport();
	}
}
