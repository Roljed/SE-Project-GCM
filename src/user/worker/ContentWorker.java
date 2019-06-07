package user.worker;

import client.ClientConsole;
import product.content.Editor;
import product.content.Editor;
import user.UserRole;

public class ContentWorker extends Worker implements Editor
{
	
	private ContentEdit editor;
	
	public ContentWorker(String namePersonal_, String nameUser_, String password_, int phoneNumber_, String email_, ClientConsole client_){
		super(namePersonal_,nameUser_,password_,phoneNumber_,email_,client_);
		this.userRole = UserRole.Role.CONTENT_WORKER;
		editor = new ContentEdit(client);
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
}
