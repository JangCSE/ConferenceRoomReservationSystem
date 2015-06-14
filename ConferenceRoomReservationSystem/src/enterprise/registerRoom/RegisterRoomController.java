package enterprise.registerRoom;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import master.ClientMasterController;
import server.room.Room;
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

		if (arg0.getActionCommand().equals("�ǵ��ư���")) {
			data = new TransmissionData();
			data.setFlags(30); // ���� �� �ؾߵǴ��� ��~
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
		
		try {
			rrm.setMaxNumber(Integer.parseInt(rrv.getMaxNumStr()));
		} catch (NumberFormatException e) {
			rrm.setMessage("�����ο��� ���ڷ� �Է��� �ֽʽÿ�.");
			return;
		}
		
		try {
			rrm.setCost(Integer.parseInt(rrv.getCost()));
		} catch (NumberFormatException e) {
			rrm.setMessage("�뿩����� ���ڷ� �Է��� �ֽʽÿ�.");
			return;
		}
		
		rrm.setCity(rrv.getCityStr());
		rrm.setDetailLocation(rrv.getDetailLoc());
		rrm.setDetail(rrv.getDetail());

		if(rrm.validCheck() == 1) {
			rrm.setMessage("ȸ�ǽ� ��ġ�� �߸��Ǿ����ϴ�.");
		} else if(rrm.validCheck() == 2) {
			rrm.setMessage("���밡�� �ο��� �ּ� �� ���� �ִ� �� ���Դϴ�.");
		} else if(rrm.validCheck() == 3) {
			rrm.setMessage("�뿩����� �ּ� �� ������ õ �� ������ ������ų �� �ֽ��ϴ�.");
		} else if(rrm.validCheck() == 4) {
			rrm.setMessage("ȸ�ǽ� �̸��� �� �� �̻� ���� �� ���Ϸ� �Է��ϼž� �մϴ�.");
		} else if(rrm.validCheck() == 5) {
			rrm.setMessage("�ΰ������� �ִ� 500�ڱ��� �Դϴ�.");
		} else {
			data = new TransmissionData();
			data.setFlags(20);
			data.setRoom(new Room(rrm.getName(), rrm.getCity(), rrm.getDetailLocation(), rrm.getMaxNumber(), rrm.getCost(), rrm.getDetail()));
			try {
				ClientMasterController.getClient().sendToServer(data);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;
			
		}

	}

}