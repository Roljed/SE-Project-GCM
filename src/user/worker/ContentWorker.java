package user.worker;

import chat.ChatClient;
import command.Editor;
import user.Permission;

import java.io.Serializable;


/**
 * Worker class that have additional editorial capabilities
 *
 * @version 1
 * @author Daniel Katz
 */
public class ContentWorker extends Worker implements Editor, Serializable
{
	public ContentWorker(String namePersonal_, String nameUser_, String password_, int phoneNumber_, String email_, ChatClient chat_){
		super(namePersonal_, nameUser_, password_, phoneNumber_, email_, chat_);
		permission = Permission.CONTENT_WORKER;
	}

	public ContentWorker(String id, String namePersonal_, String nameUser_, String password_, String phoneNumber_, String email_, ChatClient chat_, String permission){
		super(id, namePersonal_, nameUser_, password_, phoneNumber_, email_, chat_, permission);
	}
}
