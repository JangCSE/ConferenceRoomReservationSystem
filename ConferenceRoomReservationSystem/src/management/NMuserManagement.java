package management;

import list.NormalUserList;
import transmission.LoginData;
import user.NMuser;

public class NMuserManagement {
	
	private NormalUserList NUL = new NormalUserList();
	
	public NMuserManagement() {
		// temporary master account
		NMuser temp = new NMuser("master", "master", "master", "master", "master");
		addNMuser(temp);
	}
	
	public boolean login(LoginData loginData) {
		// true if success, false if fail
		if (NUL.findByID(loginData.getId()) != null) {
			if (NUL.findByID(loginData.getId()).getPassword().equals(loginData.getPw())) {
				return true;
			}
		}		
		return false;
	}
	
	public NMuser getNMuserByID(String id) {
		//get log-in user data
		return NUL.findByID(id);
	}
	
	public NMuser getNMuserByKey(int key) {
		//get user data
		return NUL.findByKey(key);
	}
	
	public boolean isItduplicated(LoginData loginData) {
		// true if overlap, false if non-overlap
		if(NUL.isItDuplicated(loginData.getId()))
			return true;
		else
			return false;
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
}