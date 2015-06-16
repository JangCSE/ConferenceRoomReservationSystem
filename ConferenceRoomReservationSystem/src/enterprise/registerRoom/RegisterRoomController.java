package enterprise.registerRoom;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import master.ClientMasterController;
import server.room.Room;
import transmission.TransmissionData;

/*
 * This class is about to register room
 * MVC controller
 */
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

		rrm.setMessage(rrv.getMsgStr());
		rrm.setName(rrv.getNameStr());

		/*
		 * max number form validation
		 * return if error
		 */
		try {
			rrm.setMaxNumber(Integer.parseInt(rrv.getMaxNumStr()));
		} catch (NumberFormatException e) {
			rrm.setMessage("�����ο��� ���ڷ� �Է����ּ���.");
			return;
		}

		/*
		 * cost form validation
		 * return if error
		 */
		try {
			rrm.setCost(Integer.parseInt(rrv.getCost()));
		} catch (NumberFormatException e) {
			rrm.setMessage("�뿩����� ���ڷ� �Է����ּ���.");
			return;
		}

		rrm.setCity(rrv.getCityStr());
		rrm.setDetailLocation(rrv.getDetailLoc());
		rrm.setDetail(rrv.getDetail());

		/*
		 * validation
		 */
		if (rrm.validCheck() == 1) {
			rrm.setMessage("ȸ�ǽ� ��ġ�� �߸��Ǿ����ϴ�.");
		} else if (rrm.validCheck() == 2) {
			rrm.setMessage("���밡�� �ο��� �ּ� �� ���� �ִ� �� ����� �Է����ּ���.");
		} else if (rrm.validCheck() == 3) {
			rrm.setMessage("�뿩����� �ּ� �� ������ õ �� ������ �Է����ּ���.");
		} else if (rrm.validCheck() == 4) {
			rrm.setMessage("ȸ�ǽ� �̸��� �� �� �̻� ���� �� ���Ϸ� �Է����ּ���.");
		} else if (rrm.validCheck() == 5) {
			rrm.setMessage("�δ�ü� ������ �ִ� 500�ڱ��� �Է����ּ���.");
		} else {
			data = new TransmissionData();
			data.setFlags(20);
			data.setRoom(new Room(rrm.getName(), rrm.getCity(), rrm
					.getDetailLocation(), rrm.getMaxNumber(), rrm.getCost(),
					rrm.getDetail()));
			try {
				ClientMasterController.getClient().sendToServer(data);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}