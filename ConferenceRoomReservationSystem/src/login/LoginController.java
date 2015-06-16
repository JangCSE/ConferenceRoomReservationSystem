package login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import master.ClientMasterController;
import transmission.LoginData;
import transmission.TransmissionData;

public class LoginController implements ActionListener {

	private LoginModel lm;
	private LoginView lv;
	private TransmissionData data;

	public LoginController(LoginModel m, LoginView v) {
		lm = m;
		lv = v;
		lv.setLoginListener(this);
	}

	public boolean controlModel(TransmissionData data) {

		if (data.getFlags() == 13) {
			// enterprise user login success
			lm.setMessage(data.getMessage());
			return true;
		} else if (data.getFlags() == 14) {
			// login fail
			lm.setMessage(data.getMessage());
		}

		return false;
	}

	public void logout() {
		lm.setId("���̵�");
		lm.setPw("��й�ȣ");
		lv.logout();
		lm.setMessage("���������� �α׾ƿ��Ǿ����ϴ�.");
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getActionCommand().equals("ȸ������")) {
			data = new TransmissionData();
			data.setFlags(0);
			try {
				ClientMasterController.getClient().sendToServer(data);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;
		}

		lm.setId(lv.getIdStr());
		lm.setPw(lv.getPwStr());

		if (lv.isEpu() && lv.isNmu()) {
			lm.setMessage("����� �ϳ����� �����ϼ���.");
		} else if (lv.isEpu() == false && lv.isNmu() == false) {
			lm.setMessage("����ڸ� �����ϼ���.");
		} else if (lv.isEpu()) {
			data = new TransmissionData();
			data.setFlags(11);
			data.setLoginData(new LoginData(lm.getId(), lm.getPw()));
			try {
				ClientMasterController.getClient().sendToServer(data);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (lv.isNmu()) {
			data = new TransmissionData();
			data.setFlags(10);
			data.setLoginData(new LoginData(lm.getId(), lm.getPw()));
			try {
				ClientMasterController.getClient().sendToServer(data);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}