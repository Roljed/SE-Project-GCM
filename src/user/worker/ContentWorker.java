package user.worker;

import client.ChatClient;
import command.Editor;
import user.Role;

public class ContentWorker extends Worker implements Editor
{
	public ContentWorker(String namePersonal_, String nameUser_, String password_, int phoneNumber_, String email_, ChatClient chat_){
		super(namePersonal_, nameUser_, password_, phoneNumber_, email_, chat_);
		role = Role.CONTENT_WORKER;
	}
}
