package user.manager;

import command.catalog.PublishVersion;
import client.ChatClient;
import user.UserStatus;
import product.pricing.MapCost;

public class ContentManager extends Manager
{

	public ContentManager(String namePersonal_, String nameUser_, String password_, int phoneNumber_, String email_, ChatClient chat_)
	{
		super(namePersonal_,nameUser_,password_,phoneNumber_,email_,chat_);
		userStatus = UserStatus.CONTENT_MANAGER;
	}

	public MapCost setMapCost(int mapID, double price)
	{
		return new MapCost(price,mapID,nameUser);
	}

	public boolean approveVersion(PublishVersion publishVersion)
	{
		publishVersion.approveVersion();
		return true;
	}
}
