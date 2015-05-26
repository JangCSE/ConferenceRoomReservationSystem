package master;

import java.io.IOException;

import server.ConnectionToClient;
import transmission.TransmissionData;
import management.EPuserManagement;
import management.NMuserManagement;

public class ServerMasterController {

	private EPuserManagement EPM = new EPuserManagement();
	private NMuserManagement NMM = new NMuserManagement();
	private TransmissionData sendingData;

	public void perform(TransmissionData data, ConnectionToClient client) {
		sendingData = new TransmissionData();
		
		if (data.getFlags() < 10) {
			// user register
			if (data.getFlags() == 0) {
				// request register
				sendingData.setFlags(1);
			} else if (data.getFlags() == 2) {
				// request cancel register
				sendingData.setFlags(3);
			} else if (data.getFlags() == 4) {
				// send nmuser data
				if(EPM.isItduplicated(data.getLoginData()) || 
						NMM.isItduplicated(data.getLoginData())) {
					sendingData.setFlags(6);
					sendingData.setMessage("�̹� ���Ե� ���̵��Դϴ�.");
				} else {
					NMM.addNMuser(data.getNMuser());
					sendingData.setFlags(7);
					sendingData.setMessage("ȸ�����Կ� �����Ͽ����ϴ�.");
				}
			} else if (data.getFlags() == 5) {
				//send epuser data
				if(EPM.isItduplicated(data.getLoginData()) || 
						NMM.isItduplicated(data.getLoginData())) {
					sendingData.setFlags(6);
					sendingData.setMessage("�̹� ���Ե� ���̵��Դϴ�.");
				} else {
					EPM.addEPuser(data.getEPuser());
					sendingData.setFlags(7);
					sendingData.setMessage("ȸ�����Կ� �����Ͽ����ϴ�.");
				}
			} 

		} else if (data.getFlags() < 20) {
			// login

			if (data.getFlags() == 10) {
				// normal user login
				if (NMM.login(data.getLoginData())) {
					sendingData.setFlags(12); // success
					sendingData.setMessage("�α��ο� �����Ͽ����ϴ�.");
				} else {
					sendingData.setFlags(13); // fail
					sendingData.setMessage("�α��ο� �����Ͽ����ϴ�.");
				}
			} else if (data.getFlags() == 11) {
				// enterprise user login				
				if (EPM.login(data.getLoginData())) {
					sendingData.setFlags(12); // success
					sendingData.setMessage("�α��ο� �����Ͽ����ϴ�.");
				} else {
					sendingData.setFlags(13); // fail
					sendingData.setMessage("�α��ο� �����Ͽ����ϴ�.");
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