package listRoom;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import master.ClientMasterController;
import transmission.LoginData;
import transmission.TransmissionData;

public class listRoomController implements ActionListener {

	private listRoomModel lrm;
	private listRoomView lrv;
	private TransmissionData data;
	
	public listRoomController(listRoomModel lm, listRoomView lv) {
		lrm = lm;
		lrv = lv;
		lrv.setListRoomListener(this);
	}

	public void controlModel(TransmissionData data) {

		if(data.getFlags() == 31) {
			lrm.setRL(data.getRoomList());
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getActionCommand().equals("조회하기")) {
			data = new TransmissionData();
			data.setFlags(30);
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