package user.worker;

import client.ChatClient;
import user.client.ClientCard;

public class Worker extends ClientCard
{
	static int NextworkerID = 1;
	
	private int workerID;
	
	public Worker(String namePersonal_,String nameUser_,String password_,int phoneNumber_,String email_,ChatClient chat_){
		super(namePersonal_,nameUser_,password_,phoneNumber_,email_,chat_);
		clientID = 0;
		NextClientID--;
		workerID = NextworkerID;
		NextworkerID++;
		role = WorkerRole;
	}
}
