package server.user;

import java.io.Serializable;

public abstract class GenericUser implements Serializable {

	private int key;
	private String id;
	private String password;
	private String name;
	private String email;
	private String contact;
	private boolean login;

	public GenericUser(String id, String pw, String name, String email,
			String ct) {
		setId(id);
		setPassword(pw);
		setName(name);
		setEmail(email);
		setContact(ct);
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public boolean isLogin() {
		return login;
	}

	public void setLogin(boolean login) {
		this.login = login;
	}

}