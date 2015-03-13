package User;

import java.io.Serializable;

public class User implements Serializable {
	
	private String id;
	private String password;
	private int key;

	public User(String id, String password) {
		this.id = id;
		this.password = password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getId() {
		return id;
	}

	public String getPassword() {
		return password;
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

}