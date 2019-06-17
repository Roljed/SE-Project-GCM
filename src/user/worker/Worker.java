package user.worker;

import chat.ChatClient;
import user.Permission;
import user.member.Member;

import java.io.Serializable;


/**
 * Basic company's worker class for all around worker
 *
 * @version 1
 * @author Daniel Katz
 */
public class Worker extends Member implements Serializable
{
	private int workerID;
	static int NextworkerID = 0;

	public Worker(String namePersonal_,String nameUser_,String password_,int phoneNumber_,String email_,ChatClient chat_){
		super(namePersonal_,nameUser_,password_,phoneNumber_,email_,chat_ , Permission.WORKER);
		workerID = NextworkerID++;
	}

	public Worker(String id, String namePersonal_, String nameUser_, String password_, String phoneNumber_, String email_, ChatClient chat_, String permission){
		super(id, namePersonal_,nameUser_,password_,phoneNumber_,email_,chat_ , permission);
	}

	public int getWorkerID() {
		return workerID;
	}
}