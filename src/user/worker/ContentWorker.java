package user.worker;

import client.ChatClient;
import client.ClientConsole;
import product.DigitalMap;
import product.content.Content;
import product.content.Editor;
import user.Role;

public class ContentWorker extends Worker implements Editor
{
	public ContentWorker(String namePersonal_, String nameUser_, String password_, int phoneNumber_, String email_, ChatClient chat_){
		super(namePersonal_, nameUser_, password_, phoneNumber_, email_, chat_);
		role = Role.CONTENT_WORKER;
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

	public void addNewElectronicMapToCity(int cityID, DigitalMap map){
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
}
