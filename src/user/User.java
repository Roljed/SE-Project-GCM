package user;

import product.content.Content;
import user.member.Member;
import user.member.SignInForm;
import command.Search;
import product.City;
import product.DigitalMap;
import client.ChatClient;
import user.member.SignUpForm;
import user.Role;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class User
{
	public boolean registeredUser;
	private SignUpForm signUpForm = null;
	private SignInForm signInForm = null;
	private Search search = null;
	protected static ChatClient chat;
	protected Role role = Role.USER;
	
	public User(ChatClient chat_)
	{
		registeredUser = false;
		chat = chat_;
	}
	
	public void signIn()
	{
		if (registeredUser)
		{
			System.out.println("The user is already signed in.");
			return;
		}
		Scanner in = new Scanner(System.in);
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
		Scanner in = new Scanner(System.in);
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
		Member clientCard = signUpForm.createMemberCard();
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
	
	public void viewCatalog(){
		Scanner in = new Scanner(System.in);
		String request;
		search = new Search(chat);
		City resCity;
		Content content = null;
		while(true){
			System.out.println("Please type the name of the city or the content that you are intrested in:");
			request = in.nextLine();
			resCity = search.searchByCity(request);
			if(resCity == null)
			{
				content = search.searchByContent(request);
				if(content == null){
					System.out.println("Your request is not found. Please try again!");
					continue;
				}
			}
			break;
		}

		if(resCity != null)
		{
			System.out.println("The number of maps in the city is: " + resCity.getCityMaps().size() + ".\n");
			System.out.println("Here is the description of every map:");
			List<DigitalMap> cityMaps = resCity.getCityMaps();
			for (DigitalMap map : cityMaps)
			{
				System.out.println("\tMap ID: " + map.getDigitalMapID() + "\n\tMap Description: \n\t\t" + map.getDigitalMapDescription() + ".\n");
			}
			System.out.println("The numbers of places of interest in the city is: " + resCity.countCityContent() + ".\n");
			System.out.println("The numbers of routes in the city is: " + resCity.getCityTours().size() + ".");
		}
		else
		{
			System.out.println("The name of " + content.getName() + "'s city is: " + content.getCityName() + ".\n");	// TODO: content resides inside a map, not city.
			System.out.println(content.getName() + " exists in " + content.getMapsNum() + " maps.\n");
			System.out.println("Here is the description of every map:");
			content.printMapDetails();
		}
	}
}
