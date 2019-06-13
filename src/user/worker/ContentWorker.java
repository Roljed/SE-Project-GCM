package user.worker;

import chat.ChatClient;
import command.Editor;
import user.Permission;

public class ContentWorker extends Worker implements Editor
{
	public ContentWorker(String namePersonal_, String nameUser_, String password_, int phoneNumber_, String email_, ChatClient chat_){
		super(namePersonal_, nameUser_, password_, phoneNumber_, email_, chat_);
		permission = Permission.CONTENT_WORKER;
	}
}
