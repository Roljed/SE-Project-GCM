package user.manager;

import client.ClientConsole;
import user.Role;
import product.pricing.MapCost;

public class ContentManager extends Manager
{
	
	public ContentManager(String namePersonal_, String nameUser_, String password_, int phoneNumber_, String email_, ClientConsole client_)
	{
		super(namePersonal_,nameUser_,password_,phoneNumber_,email_,client_);
		role = Role.CONTENT_MANAGER;
	}

	// TODO just use the method inside MapCost
	public MapCost setMapCost(int mapID,int mapPrice){
		return new MapCost(mapID,mapPrice);
	}
}
