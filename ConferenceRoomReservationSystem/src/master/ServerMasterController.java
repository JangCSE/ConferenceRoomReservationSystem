package master;

import java.io.IOException;

import server.ConnectionToClient;
import transmission.TransmissionData;
import management.EPuserManagement;

public class ServerMasterController {

	private EPuserManagement EPM = new EPuserManagement();
	private TransmissionData sendingData = new TransmissionData();

	public void perform(TransmissionData data, ConnectionToClient client) {
		if (data.getFlags() < 10) {
			// user register

		} else if (data.getFlags() < 20) {
			// login

			if (data.getFlags() == 10) {
				// normal user login

			} else if (data.getFlags() == 11) {
				// enterprise user login
				if (EPM.login(data.getLoginData())) {
					sendingData.setFlags(12); // success
					sendingData.setMessage("로그인에 성공하였습니다.");
				} else {
					sendingData.setFlags(13); // fail
					sendingData.setMessage("로그인에 실패하였습니다.");
				}
			}

		} else if (data.getFlags() < 30) {
			// room register

		} else if (data.getFlags() < 40) {
			// registered room list

		} else if (data.getFlags() < 50) {
			// delete room

		} else if (data.getFlags() < 60) {
			// book room

		} else if (data.getFlags() < 70) {
			// bookable room list

		} else if (data.getFlags() < 80) {
			// room info

		} else if (data.getFlags() < 90) {
			// booked room list

		} else if (data.getFlags() < 100) {
			// cancel booking

		}
		
		try {
			client.sendToClient(sendingData);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void saveToFile() {

	}

	public void loadFromFile() {

	}

}