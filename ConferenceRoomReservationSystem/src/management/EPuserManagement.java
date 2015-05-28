package management;

import transmission.LoginData;
import transmission.TransmissionData;
import user.EPuser;
import list.EnterpriseUserList;

public class EPuserManagement {
	
	private EnterpriseUserList EUL = new EnterpriseUserList();
	
	public EPuserManagement() {
		// temporary master account
		EPuser temp = new EPuser("master", "master", "master", "master", "master");
		addEPuser(temp);
	}
	
	public boolean login(LoginData loginData) {
		// true if success, false if fail
		if (EUL.findByID(loginData.getId()) != null) {
			if (EUL.findByID(loginData.getId()).getPassword().equals(loginData.getPw())) {
				return true;
			}
		}		
		return false;
	}
	
	public EPuser getEPuserByID(String id) {
		// get log-ined user data
		return EUL.findByID(id);
	}

	public EPuser getEPuserByKey(int key) {
		// get user data
		return EUL.findByKey(key);
	}
	
	public boolean isItduplicated(LoginData loginData) {
		// true if overlap, false if non-overlap
		if(EUL.isItDuplicated(loginData.getId()))
			return true;
		else
			return false;
	}
	
	public void logout() {
		
	}
	
	public void addEPuser(EPuser epuser) {
		epuser.setLogin(false);
		epuser.setKey(EUL.getKey());
		EUL.add(epuser);
		EUL.increaseKey();
	}
	
	public void deleteEPuser() {
		
	}
}