package user.manager;

import product.pricing.MapCost;
import chat.ChatClient;

public class CompanyManager extends Manager
{

	public CompanyManager(String namePersonal_, String nameUser_, String password_, int phoneNumber_, String email_, ChatClient chat_){
		super(namePersonal_,nameUser_,password_,phoneNumber_,email_,chat_);
	}

	public void setMapPrice(MapCost mapCost,double price){
		mapCost.setPrice(price,nameUser, permission);
	}
}
