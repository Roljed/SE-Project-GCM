package user.member;

import product.pricing.Purchase;
import user.Permission;
import chat.ChatClient;
import user.User;

import java.io.Serializable;
import java.util.List;


/**
 * Register user class.
 * Can make purchases and hold a client card in company's database.
 */
public class Member extends User implements Serializable
{
	private int memberID;
	private static int NextMemberID = 0;
	protected String personalName;
	private String password;
	protected String userName;
	private int phoneNumber;
	private String email;
	private List<Purchase> purchaseHistory;
	private boolean connencted = false;

	public Member(String namePersonal_, String nameUser_, String password_, int phoneNumber_, String email_, ChatClient client, Permission permission_){
		super(client);
		personalName = namePersonal_;
		password = password_;
		userName = nameUser_;
		phoneNumber = phoneNumber_;
		email = email_;
		permission = permission_;
		memberID = NextMemberID++;
	}

	public Member(String id_, String namePersonal_, String nameUser_, String password_, String phoneNumber_, String email_, ChatClient client, String permission_)
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


	/**
	 * @return list of purchases that have been made by this member
	 */
	public List<Purchase> getPurchaseReport()
	{
		String request = "report " + Integer.toString(memberID);
		m_chat.handleMessageFromClientUI(request);
		Object res = m_chat.receiveObjectFromServer();
		if(res == null) {
			return null;
		}
		if(res instanceof Purchase) {
			return (List<Purchase>) res;
		}
		else
			return null;
	}

}
