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

		if (arg0.getActionCommand().equals("되돌아가기")) {
			data = new TransmissionData();
			data.setFlags(30); // 무슨 값 해야되는지 모름~
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
			rrm.setMessage("수용인원은 숫자로 입력해 주십시오.");
			return;
		}
		
		try {
			rrm.setCost(Integer.parseInt(rrv.getCost()));
		} catch (NumberFormatException e) {
			rrm.setMessage("대여비용은 숫자로 입력해 주십시오.");
			return;
		}
		
		rrm.setCity(rrv.getCityStr());
		rrm.setDetailLocation(rrv.getDetailLoc());
		rrm.setDetail(rrv.getDetail());

		if(rrm.validCheck() == 1) {
			rrm.setMessage("회의실 위치가 잘못되었습니다.");
		} else if(rrm.validCheck() == 2) {
			rrm.setMessage("수용가능 인원은 최소 두 명에서 최대 만 명입니다.");
		} else if(rrm.validCheck() == 3) {
			rrm.setMessage("대여비용은 최소 만 원으로 천 원 단위로 증가시킬 수 있습니다.");
		} else if(rrm.validCheck() == 4) {
			rrm.setMessage("회의실 이름은 한 자 이상 스무 자 이하로 입력하셔야 합니다.");
		} else if(rrm.validCheck() == 5) {
			rrm.setMessage("부가정보는 최대 500자까지 입니다.");
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