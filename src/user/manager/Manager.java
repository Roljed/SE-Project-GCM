package user.manager;
import chat.ChatClient;
import command.ReportActivity;
import command.Editor;
import user.Permission;
import user.worker.Worker;

import java.io.Serializable;

abstract class Manager extends Worker implements Editor, Serializable
{
	private ReportActivity reporter;

	public Manager(String namePersonal_, String nameUser_, String password_, int phoneNumber_, String email_, ChatClient chat_, Permission permission_){
		super(namePersonal_,nameUser_,password_,phoneNumber_,email_,chat_);
		reporter = new ReportActivity(namePersonal_);
		this.permission = permission_;
	}

	public Manager(String id ,String namePersonal_, String nameUser_, String password_, String phoneNumber_, String email_, ChatClient chat_, String permission_)
	{
		super(id, namePersonal_,nameUser_,password_,phoneNumber_,email_,chat_, permission_);
		reporter = new ReportActivity(namePersonal_);
	}

	public void viewReport(){
		reporter.PrintReport();
	}

	public ReportActivity getReportActivity()
	{
		return new ReportActivity(personalName);
	}

}
