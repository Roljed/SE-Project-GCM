package user.client;

public class SignInForm {
	private String userName;
	private String password;
	
	public SignInForm(String userName_, String password_){
		setUserName(userName_);
		setPassword(password_);
	}

	public String getPassword() {
		return password;
	}

	private void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
