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
	protected String personalName;
	private String password;
	protected String userName;
	private int phoneNumber;
	private String email;
	protected SignInForm signInForm = null;
	private List<Purchase> purchaseHistory;
	private boolean connencted = false;

	public MemberCard(String namePersonal_, String nameUser_, String password_, int phoneNumber_, String email_, ChatClient client, Permission permission_){
		super(client);
		personalName = namePersonal_;
		password = password_;
		userName = nameUser_;
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
		personalName = namePersonal_;
		password = password_;
		userName = nameUser_;
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


	public void addPurchase(Purchase purchase){
		purchaseHistory.add(purchase);
	}

	public int getMemberID() {
		return memberID;
	}

	public String getPersonalName() {
		return personalName;
	}

	public String getPassword() {
		return password;
	}

	public String getUserName() {
		return userName;
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


	public void setPassword(String password)
	{
		this.password = password;
	}

	public void setUserName(String nameUser)
	{
		this.userName = nameUser;
	}

	public void setPhoneNumber(String phoneNumber)
	{
		this.phoneNumber = Integer.parseInt(phoneNumber);
	}

	public void setPersonalName(String personalName)
	{
		this.personalName = personalName;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public List<Purchase> getPurchaseHistory() {
		return purchaseHistory;
	}

	public void setPurchaseHistory(List<Purchase> purchaseHistory) {
		this.purchaseHistory = purchaseHistory;
	}

	public boolean isConnencted() {
		return connencted;
	}

	public void setConnencted(boolean connencted) {
		this.connencted = connencted;
	}

}
