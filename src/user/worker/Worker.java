package user.worker;

import user.UserRole.Role;
import user.member.MemberCard;
import client.ClientConsole;

public class Worker extends MemberCard
{
	static int NextworkerID = 1;
	
	private int workerID;
	
	public Worker(String namePersonal_,String nameUser_,String password_,int phoneNumber_,String email_,ClientConsole client_)
	{
		super(namePersonal_,nameUser_,password_,phoneNumber_,email_,client_);
		clientID = 0;
		NextClientID--;
		workerID = NextworkerID;
		NextworkerID++;
		this.userRole = Role.WORKER;
	}
}
