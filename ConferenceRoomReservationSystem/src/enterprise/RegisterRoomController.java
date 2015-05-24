package enterprise;

import transmission.TransmissionData;

public class RegisterRoomController {

	private RegisterRoomModel rrm = new RegisterRoomModel();
	private RegisterRoomView rrv = new RegisterRoomView();

	public RegisterRoomController() {
		rrm.addObserver(rrv);
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

}