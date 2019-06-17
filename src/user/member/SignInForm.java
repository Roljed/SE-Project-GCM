package user.member;

import java.io.Serializable;


/**
 * Sing in form to be send to database for requesting signing in
 *
 * @version 1
 * @author Daniel Katz
 */
public class SignInForm implements Serializable
{
	private String userName;
	private String password;

	public SignInForm(String userName_, String password_)
	{
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
