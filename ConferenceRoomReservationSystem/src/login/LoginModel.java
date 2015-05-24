package login;

import java.util.Observable;

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
		setChanged();
		notifyObservers();
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
		setChanged();
		notifyObservers();
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