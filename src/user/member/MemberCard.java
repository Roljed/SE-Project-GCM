package user.member;

import product.pricing.Purchase;
import user.UserStatus;
import client.ChatClient;
import user.User;

import java.util.List;

public class MemberCard extends User
{
	private int memberID;
	protected static int NextMemberID = 0;
	protected String namePersonal;
	private String password;
	protected String nameUser;
	private int phoneNumber;
	private String email;
	private List<Purchase> purchaseHistory;
	private boolean connencted = false;

	public MemberCard(String namePersonal_, String nameUser_, String password_, int phoneNumber_, String email_, ChatClient client){
		super(chat);
		namePersonal = namePersonal_;
		password = password_;
		nameUser = nameUser_;
		phoneNumber = phoneNumber_;
		email = email_;
		userStatus = UserStatus.MEMBER;
		memberID = NextMemberID++;
	}

	public void buyMap(Purchase purchase){
		purchaseHistory.add(purchase);
	}

	public void buySubscription(Purchase purchase){
		purchaseHistory.add(purchase);
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

	public String getPhoneNumberByString()
	{
		return Integer.toString(phoneNumber);
	}

	public String getEmail() {
		return email;
	}
}
