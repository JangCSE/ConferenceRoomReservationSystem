package register;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import transmission.TransmissionData;

public class RegisterController implements ActionListener {

	private RegisterModel rm;
	private RegisterView rv;
	private TransmissionData data;

	public RegisterController(RegisterModel m, RegisterView v) {
		rm = m;
		rv = v;
		rv.setLoginListener(this);
	}

	public boolean controlModel(TransmissionData data) {
		if (data.getFlags() == 7) {
			// there is same named room
			rm.setMessage(data.getMessage());
			return true;
		} else if (data.getFlags() == 6) {
			// successfully registered
			rm.setMessage(data.getMessage());
		}

		return false;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

}