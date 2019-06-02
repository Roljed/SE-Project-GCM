package user.client;
import src.client.ChatClient;
import src.client.ClientConsole;
import src.command.Search;
import src.product.City;
import src.product.content.ContentInterestingPlaces;

import java.util.Scanner;

public class User extends ClientConsole
{
	public boolean registeredUser;
	private SignUpForm signUpForm;
	private SignInForm signInForm;
	private Search search;

	public User(String loginID, String host, int port){
		super(loginID, host, port);
		registeredUser = false;
	}
	
	public void signIn(){
		if(registeredUser){
			System.out.println("The user is already signed in.");
			return;
		}
		Scanner in = new Scanner(System.in);
		System.out.println("Type your username:");
		String userName = in.nextLine();
		System.out.println("Type your password:");
		String passWord = in.nextLine();
		signInForm = new SignInForm(username,password);
		client.ChatClient.handleMessageFromClientUI(signInForm);
		boolean res = (boolean)input.readObject();
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
		String userName = in.nextLine();
		System.out.println("Type your password:");
		String password = in.nextLine();
		System.out.println("Type your phone number:");
		int phoneNumber = Integer.parseInt(in.nextLine());
		System.out.println("Type your email:");
		String email = in.nextLine();
		signUpForm = new SignUpForm(name,userName,password,phoneNumber,email);
		ClientCard clientCard = signUpForm.createClientCard();
		ChatClient.handleMessageFromClientUI(clientCard);
		boolean res = (boolean)input.readObject();
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
		City resCity;
		ContentInterestingPlaces resPlaceOfInterest;
		while(true)
		{
			System.out.println("Please type the name of the city or the src.product.content that you are intrested in:");
			request = in.nextLine();
			search = new Search();
			resCity = search.searchByCity(request);
			if(resCity == null)
			{
				resPlaceOfInterest = search.searchByContent(request);
			}
			if(resPlaceOfInterest == null){
				System.out.println("Your request is n0t found. Please try again!");
				continue;
			}
			break;
		}
		if(resCity != null){
			System.out.println("The no. of maps in the city is: " + resCity.getMapsNum() + ".");
			System.out.println("");
			System.out.println("Here is the description of every map:");
			resCity.printMapDetails();
			System.out.println("");
			System.out.println("The no. of places of interest in the city is: " + resCity.getPointsOfInterestNum() + ".");
			System.out.println("");
			System.out.println("The no. of routes in the city is: " + resCity.getRoutesNum() + ".");
		}
		else{
			System.out.println("The name of " + resPlaceOfInterest.getName() + "'s city is: " + resPlaceOfInterest.getCityName() + ".");
			System.out.println("");
			System.out.println(resPlaceOfInterest.getName() + " exists in " + resPlaceOfInterest.getMapsNum() + " maps.");
			System.out.println("");
			System.out.println("Here is the description of every map:");
			resPlaceOfInterest.printMapDetails();
		}
	}
}