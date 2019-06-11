package user.manager;
import client.ChatClient;
import database.report.ReportActivity;
import command.Editor;
import user.UserStatus;
import user.worker.Worker;

abstract class Manager extends Worker implements Editor
{
	private ReportActivity reporter;

	public Manager(String namePersonal_, String nameUser_, String password_, int phoneNumber_, String email_, ChatClient chat_){
		super(namePersonal_,nameUser_,password_,phoneNumber_,email_,chat_);
		reporter = new ReportActivity(namePersonal_);
		userStatus = UserStatus.MANAGER;
	}

	public void viewReport(){
		reporter.PrintReport();
	}

	public ReportActivity getReportActivity()
	{
		return new ReportActivity(namePersonal);
	}

}
