package User;

import java.io.Serializable;

public class LoginData implements Serializable {

	private String id;
	private String pw;

	public LoginData(String id, String pw) {
		this.setId(id);
		this.setPw(pw);
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

}