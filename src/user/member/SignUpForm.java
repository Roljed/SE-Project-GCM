package user.member;

import chat.ChatClient;
import user.Permission;

import java.io.Serializable;
import java.lang.String;

public class SignUpForm implements Serializable
{
	private String name;
	private String userName;
	private String password;
	private int phoneNumber;
	private String email;
	private ChatClient chat;

	public SignUpForm(String name,String userName,String password,int phoneNumber,String email, ChatClient chat){
		this.name = name;
		this.userName = userName;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.chat = chat;
	}

	public MemberCard createMemberCard()
	{
		return new MemberCard(name, userName, password, phoneNumber, email, chat, Permission.MEMBER);
	}
}
