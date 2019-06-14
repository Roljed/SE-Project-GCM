package user;

import command.catalog.Catalog;
import server.ClientServerStatus;
import user.member.MemberCard;
import command.Search;
import chat.ChatClient;
import user.member.SignInForm;
import user.member.SignUpForm;
import java.io.IOException;
import java.io.Serializable;

import static server.ClientServerStatus.*;
import static user.Permission.*;


public class User implements Serializable
{
	public boolean registeredUser = false;
	private Search search = null;
	protected static ChatClient m_chat;
	protected Permission permission = Permission.USER;

	protected ClientServerStatus clientServerStatus = ClientServerStatus.NOT_EXIST;
	private SignUpForm signUpForm;
	private SignInForm signInForm;

	public User(ChatClient chat_)
	{
		m_chat = chat_;
	}

	public void signIn(ChatClient chat, String username, String password)
	{
		signInForm = new SignInForm(username,password);
		chat.handleMessageFromClientUI(signInForm);
	}

	public void signUp(String name, String username, String password, int phoneNumber, String email)
	{
		SignUpForm signUpForm = new SignUpForm(name, username, password, phoneNumber, email, m_chat);
		MemberCard clientCard = signUpForm.createMemberCard();
		try {
			m_chat.sendToServer(clientCard);
		}
		catch(IOException ex) {}
		boolean res = (boolean) m_chat.receiveObjectFromServer();
		if(res){
			System.out.println("You are signed up now. Please sign in using your personalName and password!");
		}
		else
		{
			System.out.println("Something went wrong. Please try again.");
		}
	}

	public Catalog viewCatalog(String request, int searchType)
	{
		search = new Search(m_chat);
		Catalog resultCatalog = null;
		switch(searchType){
			case 1: resultCatalog = search.searchByCityName(request); break;
			case 2: resultCatalog = search.searchBySite(request); break;
			case 3: resultCatalog = search.searchByDescription(request); break;
			default: break;
		}
		return resultCatalog;
	}

	public SignUpForm getSignUpForm()
	{
		return signUpForm;
	}

	public Permission getPermission()
	{
		return permission;
	}

	public String getPermissionByString()
	{
		switch(permission) {
			case MEMBER: return "MEMBER";
			case WORKER: return "WORKER";
			case CONTENT_WORKER: return "CONTENT_WORKER";
			case COMPANY_MANAGER: return "COMPANY_MANAGER";
			case CONTENT_MANAGER: return "CONTENT_MANAGER";
			default: return "USER";
		}
	}

	public  Permission getPermissionFromString(String str)
	{
		switch(str) {
			case "MEMBER": return MEMBER;
			case "WORKER": return WORKER;
			case "CONTENT_WORKER": return CONTENT_WORKER;
			case "COMPANY_MANAGER": return COMPANY_MANAGER;
			case "CONTENT_MANAGER": return CONTENT_MANAGER;
			default: return USER;
		}
	}


	public ClientServerStatus getClientServerStatus()
	{
		return clientServerStatus;
	}

	public String getClientServerStatusToString()
	{
		switch(clientServerStatus) {
			case CONNECTED: return "CONNECTED";
			case NOT_CONNECTED: return "NOT_CONNECTED";
			case WRONG_USERNAME_OR_PASSWORD: return "WRONG_USERNAME_OR_PASSWORD";
			case WRONG_ARGUMENTS: return "WRONG_ARGUMENTS";
			default: return "NOT_EXIST";
		}
	}

	public ClientServerStatus getClientServerStatusFromString(String str)
	{
		switch(str) {
			case "CONNECTED": return CONNECTED;
			case "NOT_CONNECTED": return NOT_CONNECTED;
			case "WRONG_USERNAME_OR_PASSWORD": return WRONG_USERNAME_OR_PASSWORD;
			case "WRONG_ARGUMENTS": return WRONG_ARGUMENTS;
			default: return NOT_EXIST;
		}
	}

	public void setClientServerStatus(ClientServerStatus clientServerStatus)
	{
		this.clientServerStatus = clientServerStatus;
	}


}
