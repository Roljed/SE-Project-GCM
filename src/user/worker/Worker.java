package user.worker;

public class Worker extends ClientCard
{
	static int NextworkerID = 1;
	
	private int workerID;

	
	public Worker(String namePersonal_,String nameUser_,String password_,int phoneNumber_,String email_){
		super(namePersonal_,nameUser_,password_,phoneNumber_,email_);
		clientID = 0;
		ClientCard.NextClientID--;
		workerID = NextworkerID;
		NextworkerID++;
		role = WorkerRole;
	}
}