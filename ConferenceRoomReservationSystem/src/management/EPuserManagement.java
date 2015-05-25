package management;

import transmission.LoginData;
import user.EPuser;
import list.EnterpriseUserList;

public class EPuserManagement {
	
	private EnterpriseUserList EUL = new EnterpriseUserList();
	
	public EPuserManagement() {
		EPuser temp = new EPuser("master", "master", "master", "master", "master");
		addEPuser(temp);
	}
	
	public boolean login(LoginData loginData) {
		// true if success, false if fail
		if (EUL.findByID(loginData.getId()) != null) {
			System.out.println(loginData.getId() + ", " + loginData.getPw());
			if (EUL.findByID(loginData.getId()).getPassword().equals(loginData.getPw())) {
				System.out.println("correct password");
				return true;
			}
		}		
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