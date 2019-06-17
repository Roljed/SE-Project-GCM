package user.member;

import chat.ChatClient;
import user.Permission;

import java.io.Serializable;
import java.lang.String;


/**
 * Register user form to be sent to the database
 *
 * @version 1
 * @author Daniel Katz
 */
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

	public Member createMemberCard()
	{
		return new Member(name, userName, password, phoneNumber, email, chat, Permission.MEMBER);
	}
}
