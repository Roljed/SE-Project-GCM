package user.member;

import user.Role;
import client.ChatClient;
import product.pricing.PurchaseHistory;
import user.User;

public class MemberCard extends User
{
	protected static int NextMemberID = 1;
	
	private int memberID;
	private String namePersonal;
	private String password;
	protected String nameUser;
	private int phoneNumber;
	private String email;
	private PurchaseHistory purchaseHistory;

	public MemberCard(String namePersonal_, String nameUser_, String password_, int phoneNumber_, String email_, ChatClient client){
		super(chat);
		namePersonal = namePersonal_;
		password = password_;
		nameUser = nameUser_;
		phoneNumber = phoneNumber_;
		email = email_;
		purchaseHistory = new PurchaseHistory();
		role = Role.MEMBER;
		memberID = NextMemberID++;
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

	public int getMemberID() {
		return memberID;
	}

	public String getNamePersonal() {
		return namePersonal;
	}

	public String getPassword() {
		return password;
	}

	public String getNameUser() {
		return nameUser;
	}

	public int getPhoneNumber() {
		return phoneNumber;
	}

	public String getEmail() {
		return email;
	}
}
