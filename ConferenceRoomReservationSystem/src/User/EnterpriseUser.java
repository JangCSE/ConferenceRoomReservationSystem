package User;

import java.io.Serializable;

public class EnterpriseUser extends User implements Serializable {

	private String name;
	private String email;
	private String contact;

	public EnterpriseUser(String id, String password, String name,
			String email, String contact) {
		super(id, password);
		this.name = name;
		this.email = email;
		this.contact = contact;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getContact() {
		return contact;
	}

}