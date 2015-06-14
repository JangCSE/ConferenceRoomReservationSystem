package normal.listBookedRoom;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;

import master.ClientMasterController;
import transmission.TransmissionData;

public class ListBookedRoomController implements ActionListener {

	private ListBookedRoomModel lbrm;
	private ListBookedRoomView lbrv;
	private TransmissionData data;
	@SuppressWarnings("unused")
	private FileWriter fw;

	public ListBookedRoomController(ListBookedRoomModel lbrm,
			ListBookedRoomView lbrv) {
		this.lbrm = lbrm;
		this.lbrv = lbrv;
		lbrv.setListBookedRoomListener(this);
	}

	public void controlModel(TransmissionData data) {

		if (data.getFlags() == 81) {
			lbrm.setMyList(data.getRoomList());
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getActionCommand().equals("조회하기")) {
			data = new TransmissionData();
			data.setFlags(80);
			try {
				ClientMasterController.getClient().sendToServer(data);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;
		} else if (arg0.getActionCommand().equals("예약취소하기")) {
			data = new TransmissionData();
			data.setFlags(90);
			int select = lbrv.getTable().getSelectedRow();
			data.setRoom(lbrm.getMyList().getList().get(select));// 이걸로 예약취소
			// data.setDate();//date 처리

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