package user.manager;

import product.pricing.MapCost;
import chat.ChatClient;
import user.Permission;

import java.io.Serializable;

public class CompanyManager extends Manager implements Serializable
{

	public CompanyManager(String namePersonal_, String nameUser_, String password_, int phoneNumber_, String email_, Permission permission_, ChatClient chat_){
		super(namePersonal_,nameUser_,password_,phoneNumber_,email_, permission_, chat_);

	}

	public void setMapPrice(MapCost mapCost,double price){
		mapCost.setPrice(price,nameUser, permission);
	}
}
