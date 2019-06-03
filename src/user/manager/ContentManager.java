package user.manager;

public class ContentManager extends Manager{
	
	public ContentManager(String namePersonal_,String nameUser_,String password_,int phoneNumber_,String email_,ClientConsole client_){
		super(namePersonal_,nameUser_,password_,phoneNumber_,email_,client_);
	}
	
	public MapsCost setMapCost(int mapID,int mapPrice){
		return new MapsCost(mapID,mapPrice);
	}
}
