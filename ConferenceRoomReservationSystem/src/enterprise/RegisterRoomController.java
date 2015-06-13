package enterprise;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.IOException;

import master.ClientMasterController;
import transmission.TransmissionData;

public class RegisterRoomController implements ActionListener {

	private RegisterRoomModel rrm;
	private RegisterRoomView rrv;
	private TransmissionData data;

	public RegisterRoomController(RegisterRoomModel m, RegisterRoomView v) {
		rrm = m;
		rrv = v;
		rrv.setListener(this);
	}

	public void controlModel(TransmissionData data) {

		if (data.getFlags() == 21) {
			// there is same named room
			rrm.setMessage(data.getMessage());
		} else if (data.getFlags() == 22) {
			// successfully registered
			rrm.setMessage(data.getMessage());
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

		if (arg0.getActionCommand().equals("되돌아가기")) {
			data = new TransmissionData();
			data.setFlags(2); // 무슨 값 해야되는지 모름~
			try {
				ClientMasterController.getClient().sendToServer(data);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;
		}

		rrm.setMessage(rrv.getMsgStr());
		rrm.setName(rrv.getNameStr());
		rrm.setMaxNumber(Integer.parseInt(rrv.getMaxNumStr()));
		rrm.setCost(Integer.parseInt(rrv.getCost()));
		rrm.setCity(rrv.getCityStr());
		rrm.setDetailLocation(rrv.getDetailLoc());
		rrm.setDetail(rrv.getDetail());

		// 다른 처리 필요한가?

	}

}