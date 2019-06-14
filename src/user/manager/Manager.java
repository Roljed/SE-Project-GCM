package user.manager;
import chat.ChatClient;
import database.report.ReportActivity;
import command.Editor;
import user.Permission;
import user.worker.Worker;

import java.io.Serializable;

abstract class Manager extends Worker implements Editor, Serializable
{
	private ReportActivity reporter;

	public Manager(String namePersonal_, String nameUser_, String password_, int phoneNumber_, String email_, Permission permission_, ChatClient chat_){
		super(namePersonal_,nameUser_,password_,phoneNumber_,email_,chat_);
		reporter = new ReportActivity(namePersonal_);
		this.permission = permission_;
	}

	public void viewReport(){
		reporter.PrintReport();
	}

	public ReportActivity getReportActivity()
	{
		return new ReportActivity(namePersonal);
	}

}
