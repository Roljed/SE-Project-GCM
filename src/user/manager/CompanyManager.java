package user.manager;

import product.pricing.MapCost;
import chat.ChatClient;
import user.Permission;

import java.io.Serializable;

public class CompanyManager extends Manager implements Serializable
{

	public CompanyManager(String namePersonal_, String nameUser_, String password_, int phoneNumber_, String email_, Permission permission_, ChatClient chat_){
		super(namePersonal_,nameUser_,password_,phoneNumber_,email_, chat_, permission_);

	}

	public CompanyManager(String id, String namePersonal_, String nameUser_, String password_, String phoneNumber_, String email_,ChatClient chat_, String permission_){
		super(id, namePersonal_,nameUser_,password_,phoneNumber_,email_, chat_, permission_);

	}

	public void setMapPrice(MapCost mapCost,double price){
		mapCost.setPrice(price, userName, permission);
	}
}
