package user;

import command.catalog.Catalog;
import product.ProductType;
import user.member.MemberCard;
import user.member.SignInForm;
import command.Search;
import client.ChatClient;
import user.member.SignUpForm;
import java.io.IOException;
import java.util.Scanner;


public class User
{
	public boolean registeredUser = false;
	private SignUpForm signUpForm = null;
	private SignInForm signInForm = null;
	private Search search = null;
	protected static ChatClient chat;
	protected Role role = Role.USER;
	private Scanner in = new Scanner(System.in);

	public User(ChatClient chat_)
	{
		chat = chat_;
	}

	public void signIn()
	{
		if (registeredUser)
		{
			System.out.println("The user is already signed in.");
			return;
		}
		System.out.println("Type your username:");
		String username = in.nextLine();
		System.out.println("Type your password:");
		String password = in.nextLine();
		signInForm = new SignInForm(username,password);
		try {
			chat.sendToServer(signInForm);
		}
		catch(IOException ex) {}
		boolean res = (boolean)chat.recieveObjectFromServer();
		if(res){
			registeredUser = true;
			System.out.println("You are Signed in now!");
		}
		else{
			System.out.println("Check your username and password and try again!");
		}
		return;
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
		SignUpForm signUpForm = new SignUpForm(name, username,password,phoneNumber,email, chat);
		MemberCard clientCard = signUpForm.createMemberCard();
		try {
			chat.sendToServer(clientCard);
		}
		catch(IOException ex) {}
		boolean res = (boolean)chat.recieveObjectFromServer();
		if(res){
			System.out.println("You are signed up now. Please sign in using your username and password!");
		}
		else{
			System.out.println("Something went wrong. Please try again.");
		}
	}

	public void viewCatalog()
	{
		String request;
		search = new Search(chat);
		while(true)
		{
			System.out.println("Please type which product you are searching:");
			System.out.println("Please type the name of the city or the content that you are interested in:");
			request = in.nextLine();

			Catalog resultCatalog = search.searchByCityName(request);
			if(resultCatalog == null)
			{
				resultCatalog = search.searchByContent(request);
				if(resultCatalog == null){
					System.out.println("Your request is not found. Please try again.");
					continue;
				}
				resultCatalog.viewCatalog();
			}
			break;
		}
	}

	public SignUpForm getSignUpForm() {
		return signUpForm;
	}

	public Role getRole()
	{
		return role;
	}
}
