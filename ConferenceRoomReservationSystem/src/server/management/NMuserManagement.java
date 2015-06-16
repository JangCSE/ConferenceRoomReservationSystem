package server.management;

import server.list.NormalUserList;
import server.user.NMuser;
import transmission.LoginData;

public class NMuserManagement {

	private NormalUserList NUL = new NormalUserList();

	public NMuserManagement() {
	}

	public boolean login(LoginData loginData) {
		// true if success, false if fail
		if (NUL.findByID(loginData.getId()) != null) {
			if (NUL.findByID(loginData.getId()).getPassword()
					.equals(loginData.getPw())) {
				return true;
			}
		}
		return false;
	}

	public NMuser getNMuserByID(String id) {
		// get log-in user data
		return NUL.findByID(id);
	}

	public NMuser getNMuserByKey(int key) {
		// get user data
		return NUL.findByKey(key);
	}

	public boolean isItduplicated(LoginData loginData) {
		// true if overlap, false if non-overlap
		return NUL.isItDuplicated(loginData.getId());
	}

	public void logout() {

	}

	public void addNMuser(NMuser nmuser) {
		nmuser.setLogin(false);
		nmuser.setKey(NUL.getKey());
		NUL.add(nmuser);
		NUL.increaseKey();
	}

	public void deleteNMuser() {

	}

	public void setNormalUserList(NormalUserList NUL) {
		this.NUL = NUL;
	}

	public NormalUserList getNormalUserList() {
		return this.NUL;
	}

}