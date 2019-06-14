package user.member;

import product.pricing.Purchase;
import user.Permission;
import chat.ChatClient;
import user.User;

import java.io.Serializable;
import java.util.List;

public class MemberCard extends User implements Serializable
{
	private int memberID;
	protected static int NextMemberID = 0;
	protected String namePersonal;
	private String password;
	protected String nameUser;
	private int phoneNumber;
	private String email;
	protected SignInForm signInForm = null;
	private List<Purchase> purchaseHistory;
	private boolean connencted = false;

	public MemberCard(String namePersonal_, String nameUser_, String password_, int phoneNumber_, String email_, ChatClient client, Permission permission_){
		super(client);
		namePersonal = namePersonal_;
		password = password_;
		nameUser = nameUser_;
		phoneNumber = phoneNumber_;
		email = email_;
		if (permission_ == null)
		{
			permission_ = Permission.MEMBER;
		}
		else
		{
			permission = permission_;
		}
		memberID = NextMemberID++;
	}

	public MemberCard(String id_, String namePersonal_, String nameUser_, String password_, String phoneNumber_, String email_, ChatClient client, String permission_)
	{
		super(m_chat);
		memberID = Integer.parseInt(id_);
		namePersonal = namePersonal_;
		password = password_;
		nameUser = nameUser_;
		phoneNumber = Integer.parseInt(phoneNumber_);
		email = email_;
		if (permission_ == null)
		{
			permission = Permission.MEMBER;
		}
		else
		{
			permission = getPermissionFromString(permission_);
		}
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

    public String getMemberIDByString()
	{
		return Integer.toString(memberID);
    }
}
