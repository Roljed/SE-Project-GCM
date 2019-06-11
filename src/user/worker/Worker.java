package user.worker;

import client.ChatClient;
import user.UserStatus;
import user.member.MemberCard;

public class Worker extends MemberCard
{
	private int workerID;
	static int NextworkerID = 0;

	public Worker(String namePersonal_,String nameUser_,String password_,int phoneNumber_,String email_,ChatClient chat_){
		super(namePersonal_,nameUser_,password_,phoneNumber_,email_,chat_);
		workerID = NextworkerID++;
		userStatus = UserStatus.WORKER;
	}

	public int getWorkerID() {
		return workerID;
	}
}