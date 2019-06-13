package user.worker;

import chat.ChatClient;
import user.Permission;
import user.member.MemberCard;

public class Worker extends MemberCard
{
	private int workerID;
	static int NextworkerID = 0;

	public Worker(String namePersonal_,String nameUser_,String password_,int phoneNumber_,String email_,ChatClient chat_){
		super(namePersonal_,nameUser_,password_,phoneNumber_,email_,chat_ , Permission.WORKER);
		workerID = NextworkerID++;
	}

	public int getWorkerID() {
		return workerID;
	}
}