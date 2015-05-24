package login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import transmission.TransmissionData;

public class LoginController implements ActionListener {

	private LoginModel lm;
	private LoginView lv;

	public LoginController(LoginModel m, LoginView v) {
		lm = new LoginModel();
		lv = new LoginView();
		lv.setLoginListener(this);
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

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

}