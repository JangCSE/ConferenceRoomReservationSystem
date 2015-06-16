package login;

import java.util.Observable;

/*
 * This is class about to login
 * MVC model
 */
public class LoginModel extends Observable {

	private String id;
	private String pw;
	private String message;

	public LoginModel() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
		setChanged();
		notifyObservers();
	}

}