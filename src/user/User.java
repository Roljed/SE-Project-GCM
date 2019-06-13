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
import java.util.Scanner;

import static server.ClientServerStatus.*;
import static user.Permission.*;


public class User implements Serializable
{
	public boolean registeredUser = false;
	private Search search = null;
	protected static ChatClient m_chat;
	protected Permission permission = Permission.USER;

	protected ClientServerStatus clientServerStatus = ClientServerStatus.NOT_EXIST;
	private Scanner in = new Scanner(System.in);
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

	public void signUp()
	{
		System.out.println("Type your name:");
		String name = in.nextLine();
		System.out.println("Type your username:");
		String username = in.nextLine();
		System.out.println("Type your password:");
		String password = in.nextLine();
		System.out.println("Type your phone number:");
		int phoneNumber = Integer.parseInt(in.nextLine());
		System.out.println("Type your email:");
		String email = in.nextLine();
		SignUpForm signUpForm = new SignUpForm(name, username, password, phoneNumber, email, m_chat);
		MemberCard clientCard = signUpForm.createMemberCard();
		try {
			m_chat.sendToServer(clientCard);
		}
		catch(IOException ex) {}
		boolean res = (boolean) m_chat.receiveObjectFromServer();
		if(res){
			System.out.println("You are signed up now. Please sign in using your username and password!");
		}
		else
		{
			System.out.println("Something went wrong. Please try again.");
		}
	}

	public void viewCatalog()
	{
		String request;
		search = new Search(m_chat);
		while(true)
		{
			System.out.println("Please type which product you are searching:");
			System.out.println("Please type the name of the city or the content that you are interested in:");
			request = in.nextLine();

			Catalog resultCatalog = search.searchByCityName(request);
			if(resultCatalog == null)
			{
				resultCatalog = search.searchBySite(request);
				if(resultCatalog == null){
					System.out.println("Your request is not found. Please try again.");
					continue;
				}
				resultCatalog.viewCatalog();
			}
			break;
		}
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
			case MANAGER: return "MANAGER";
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
			case "MANAGER": return MANAGER;
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
