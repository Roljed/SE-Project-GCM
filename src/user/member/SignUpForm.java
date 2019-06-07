package user.client;

import client.ChatClient;
import client.ClientConsole;
import java.lang.String;

class SignUpForm
{
	private String name;
	private String userName;
	private String password;
	private int phoneNumber;
	private String email;
	private ChatClient chat;
	
	public SignUpForm(String name_,String userName_,String password_,int phoneNumber_,String email_, ChatClient chat_){
		name = name_;
		userName = userName_;
		password = password_;
		phoneNumber = phoneNumber_;
		email = email_;
		setChat(chat_);
	}
	
	public ClientCard createClientCard()
	{
		return new ClientCard( name, userName, password, phoneNumber, email, chat);
	}

	public ChatClient getChat() {
		return chat;
	}

	public void setChat(ChatClient chat) {
		this.chat = chat;
	}
}
