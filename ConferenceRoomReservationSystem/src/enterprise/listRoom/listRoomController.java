package enterprise.listRoom;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import master.ClientMasterController;
import transmission.TransmissionData;

public class listRoomController implements ActionListener {

	private listRoomModel lrm;
	private listRoomView lrv;
	private TransmissionData data;
	private FileWriter fw;
	private File dir;

	public listRoomController(listRoomModel lm, listRoomView lv) {
		lrm = lm;
		lrv = lv;
		lrv.setListRoomListener(this);
	}

	public void controlModel(TransmissionData data) {

		if (data.getFlags() == 31) {
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
		} else if (arg0.getActionCommand().equals("파일에 저장")) {
			fw = null;
			dir = new File("C:\\ConferenceRoomReservationSystem");
			if (!dir.exists()) {
				dir.mkdir();
			}
			try {
				fw = new FileWriter("C:\\ConferenceRoomReservationSystem\\"
						+ shm.getSelectedRoom().getName() + ".txt");
				fw.write(shm.getSelectedRoom().getName());
				fw.write(shm.getSelectedRoom().getCity());
				fw.write(shm.getSelectedRoom().getDetailLocation());
				fw.write(Integer.toString(shm.getSelectedRoom().getMaxNumber()));
				fw.write(Integer.toString(shm.getSelectedRoom().getCost()));
				fw.write(shm.getSelectedRoom().getDetail());
				fw.close();
			} catch (Exception e) {
				System.out.println("error : " + e);
			}		
			return;
		}

	}

}