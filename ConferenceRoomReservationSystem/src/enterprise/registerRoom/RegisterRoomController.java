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
			rrm.setMessage("수용인원은 숫자로 입력해주세요.");
			return;
		}

		/*
		 * cost form validation
		 * return if error
		 */
		try {
			rrm.setCost(Integer.parseInt(rrv.getCost()));
		} catch (NumberFormatException e) {
			rrm.setMessage("대여비용은 숫자로 입력해주세요.");
			return;
		}

		rrm.setCity(rrv.getCityStr());
		rrm.setDetailLocation(rrv.getDetailLoc());
		rrm.setDetail(rrv.getDetail());

		/*
		 * validation
		 */
		if (rrm.validCheck() == 1) {
			rrm.setMessage("회의실 위치가 잘못되었습니다.");
		} else if (rrm.validCheck() == 2) {
			rrm.setMessage("수용가능 인원은 최소 두 명에서 최대 만 명까지 입력해주세요.");
		} else if (rrm.validCheck() == 3) {
			rrm.setMessage("대여비용은 최소 만 원으로 천 원 단위로 입력해주세요.");
		} else if (rrm.validCheck() == 4) {
			rrm.setMessage("회의실 이름은 한 자 이상 스무 자 이하로 입력해주세요.");
		} else if (rrm.validCheck() == 5) {
			rrm.setMessage("부대시설 정보는 최대 500자까지 입력해주세요.");
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