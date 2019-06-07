package user.member;

import client.ClientConsole;
import java.lang.String;

public class SignUpForm
{
	private String name;
	private String userName;
	private String password;
	private int phoneNumber;
	private String email;
	private ClientConsole client;
	
	public SignUpForm(String name_,String userName_,String password_,int phoneNumber_,String email_, ClientConsole client_){
		name = name_;
		userName = userName_;
		password = password_;
		phoneNumber = phoneNumber_;
		email = email_;
		client = client_;
	}
	
	public MemberCard createMemberCard()
	{
		return new MemberCard( name, userName, password, phoneNumber, email, client);
	}
}
