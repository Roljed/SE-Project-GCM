package user;

import command.catalog.Catalog;
import user.member.Member;
import command.Search;
import chat.ChatClient;
import user.member.SignInForm;
import user.member.SignUpForm;
import java.io.Serializable;

import static user.Permission.*;


/**
 * The basic class for all app user.
 *
 * @version 1
 * @author Daniel Katz
 *
 */
public class User implements Serializable
{
	protected static ChatClient m_chat;
	protected Permission permission = Permission.USER;

	public User(ChatClient chat_)
	{
		m_chat = chat_;
	}


	/**
	 * @param chat client connector with the company's server
	 * @param username user's private and uniq username
	 * @param password user's private password
	 */
	public void signIn(ChatClient chat, String username, String password)
	{
		SignInForm signInForm = new SignInForm(username, password);
		chat.handleMessageFromClientUI(signInForm);
	}


	/**
	 * @param chat client connector with the company's server
	 * @param name personal user's full name
	 * @param username user's uniq username for future access to his card
	 * @param password user's private password for future access to his card
	 * @param phoneNumber user's phone number
	 * @param email user's email
	 */
	public void signUp(ChatClient chat, String name, String username, String password, int phoneNumber, String email)
	{
		SignUpForm signUpForm = new SignUpForm(name, username, password, phoneNumber, email, chat);
		Member clientCard = signUpForm.createMemberCard();
		chat.handleMessageFromClientUI(clientCard);
	}


	/**
	 * @param request requested string to search
	 * @param searchType for 3 search configurations {1: City name, 2: Site name, 3: City or Site description}
	 * @return Catalog class with search results
	 */
	public Catalog viewCatalog(String request, int searchType)
	{
		Search search = new Search(m_chat);
		Catalog resultCatalog = null;
		switch(searchType){
			case 1: resultCatalog = search.searchByCityName(request); break;
			case 2: resultCatalog = search.searchBySite(request); break;
			case 3: resultCatalog = search.searchByDescription(request); break;
			default: break;
		}
		return resultCatalog;
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

	protected Permission getPermissionFromString(String str)
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
}
