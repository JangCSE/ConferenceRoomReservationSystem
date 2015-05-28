package user;

import java.io.Serializable;

public class EPuser extends GenericUser implements Serializable {

	public EPuser(String id, String pw, String name, String email, String ct) {
		super(id, pw, name, email, ct);
	}

	public void setEPuser(EPuser epuser) {
		// using save logined user data in server
		setId(epuser.getId());
		setPassword(epuser.getPassword());
		setName(epuser.getName());
		setEmail(epuser.getEmail());
		setContact(epuser.getContact());
		setKey(epuser.getKey());
		setLogin(true);
	}

}