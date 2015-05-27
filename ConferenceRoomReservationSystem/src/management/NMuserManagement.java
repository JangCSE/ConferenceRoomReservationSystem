package management;

import transmission.LoginData;
import transmission.TransmissionData;
import user.NMuser;
import list.NormalUserList;

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