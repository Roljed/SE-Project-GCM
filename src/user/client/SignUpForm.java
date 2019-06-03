package user.client;

import java.lang.String;

class SignUpForm{
	private String name;
	private String userName;
	private String password;
	private int phoneNumber;
	private String email;
	private ClientComsole client;
	
	public signUpForm(String name_,String userName_,String password_,int phoneNumber_,String email_,ClientComsole client_){
		name = name_;
		userName = userName_;
		password = password_;
		phoneNumber = phoneNumber_;
		email = email_;
		client = client_;
	}
	
	public ClientCard createClientCard(){
		return new ClientCard(name,userName,password,phoneNumber,email,client);
	}
}
