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
		if (arg0.getActionCommand().equals("��ȸ�ϱ�")) {
			data = new TransmissionData();
			data.setFlags(80);
			try {
				ClientMasterController.getClient().sendToServer(data);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;
		} else if (arg0.getActionCommand().equals("��������ϱ�")) {
			data = new TransmissionData();
			data.setFlags(90);
			int select = lbrv.getTable().getSelectedRow();
			data.setRoom(lbrm.getMyList().getList().get(select));// �̰ɷ� �������
			// data.setDate();//date ó��

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