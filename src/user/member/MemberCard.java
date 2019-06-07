package user.client;

import client.ChatClient;
import client.ClientConsole;
import product.pricing.PurchaseHistory;

public class ClientCard extends User
{
	protected static int NextClientID = 1;
	
	public String namePersonal;
	public String password;
	public String nameUser;
	public int phoneNumber;
	public String email;
	private PurchaseHistory purchaseHistory;
	public Role role;
	private int clientID;
	
	public ClientCard(String namePersonal_, String nameUser_, String password_, int phoneNumber_, String email_, ChatClient client){
		super(chat);
		namePersonal = namePersonal_;
		password = password_;
		nameUser = nameUser_;
		phoneNumber = phoneNumber_;
		email = email_;
		purchaseHistory = new PurchaseHistory();
		role = ClientRole;
		clientID = NextClientID;
		NextClientID++;
	}

	public void buyMap(int mapID){
		boolean res = purchaseHistory.addMap(mapID);
		if(res){
			System.out.println("The purchase is completed.");
		}
		else{
			System.out.println("Something went wrong. Please try again or contact us.");
		}
	}
	
	public void buySubscription(int cityID){
		boolean res = purchaseHistory.addCity(cityID);
		if(res){
			System.out.println("The purchase is completed.");
		}
		else{
			System.out.println("Something went wrong. Please try again or contact us.");
		}
	}
}
