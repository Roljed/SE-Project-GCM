package user.manager;
import chat.ChatClient;
import command.Editor;
import product.pricing.Purchase;
import user.Permission;
import user.worker.Worker;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * Abstract class for al company's different kinds of managers
 *
 * @version 1
 * @author Daniel Katz
 */
abstract class Manager extends Worker implements Editor, Serializable
{

	Manager(String namePersonal_, String nameUser_, String password_, int phoneNumber_, String email_, ChatClient chat_, Permission permission_){
		super(namePersonal_,nameUser_,password_,phoneNumber_,email_,chat_);
		this.permission = permission_;
	}

	Manager(String id, String namePersonal_, String nameUser_, String password_, String phoneNumber_, String email_, ChatClient chat_, String permission_)
	{
		super(id, namePersonal_,nameUser_,password_,phoneNumber_,email_,chat_, permission_);
	}


	public List<Purchase> getCustomersReportActivity(ChatClient chat){
		String request = "REPORT";
		chat.handleMessageFromClientUI(request);
		List<?> res = (List<?>)chat.receiveObjectFromServer();
		if(res == null) {
			return null;
		}
		List<Purchase> purchases = new ArrayList<Purchase>();
		for(Object o : res) {
			if(o instanceof Purchase) {
				purchases.add((Purchase)o);
			}
		}
		return purchases;
	}
}
