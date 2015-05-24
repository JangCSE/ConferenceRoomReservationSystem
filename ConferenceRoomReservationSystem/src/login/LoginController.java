package login;

import transmission.TransmissionData;

public class LoginController {

	private LoginModel lm = new LoginModel();
	private LoginView lv = new LoginView();
	
	public LoginController() {
		lm.addObserver(lv);
	}
	
	public void controlModel(TransmissionData data) {

		if (data.getFlags() == 12) {
			// there is same named room
			lm.setMessage(data.getMessage());
		} else if (data.getFlags() == 13) {
			// successfully registered
			lm.setMessage(data.getMessage());
		}
	}
	
}