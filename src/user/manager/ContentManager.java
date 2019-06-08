package user.manager;

import client.ChatClient;
import user.Role;
import product.pricing.MapCost;

public class ContentManager extends Manager
{
	
	public ContentManager(String namePersonal_, String nameUser_, String password_, int phoneNumber_, String email_, ChatClient chat_)
	{
		super(namePersonal_,nameUser_,password_,phoneNumber_,email_,chat_);
		role = Role.CONTENT_MANAGER;
	}

	// TODO just use the method inside MapCost
	public MapCost setMapCost(double price,int mapID){
		return new MapCost(price,mapID,nameUser);
	}
}
