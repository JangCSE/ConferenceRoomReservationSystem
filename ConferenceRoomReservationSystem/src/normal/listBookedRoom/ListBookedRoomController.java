package normal.listBookedRoom;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.swing.JOptionPane;

import master.ClientMasterController;
import transmission.TransmissionData;

public class ListBookedRoomController implements ActionListener {

	private ListBookedRoomModel lbrm;
	private ListBookedRoomView lbrv;
	private TransmissionData data;
	@SuppressWarnings("unused")
	private FileWriter fw;
	static SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");

	public ListBookedRoomController(ListBookedRoomModel lbrm,
			ListBookedRoomView lbrv) {
		this.lbrm = lbrm;
		this.lbrv = lbrv;
		lbrv.setListBookedRoomListener(this);
	}

	public void controlModel(TransmissionData data) {

		if (data.getFlags() == 81) {
			lbrm.setMyList(data.getRoomList());
		} else if (data.getFlags() == 91) {
			JOptionPane.showMessageDialog(null, ("예약이 취소되었습니다."));
			lbrm.setMyList(data.getRoomList());
		} else if (data.getFlags() == 83) {
			JOptionPane.showMessageDialog(null, (sd.format(data.getDate())));
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
		} else if (arg0.getActionCommand().equals("예약취소하기")) {
			data = new TransmissionData();
			data.setFlags(90);
			int select = lbrv.getTable().getSelectedRow();
			try {
				data.setRoom(lbrm.getMyList().getList().get(select));// 이걸로 예약취소
				data.setDateKey(lbrm.getMyList().getTempDateKey().get(select));
				// data.setDate();//date 처리
			} catch (ArrayIndexOutOfBoundsException e) {
				JOptionPane.showMessageDialog(null, ("취소할 회의실을 선택하여 주십시오."));
				return;
			} catch (NullPointerException e) {
				JOptionPane.showMessageDialog(null, ("취소할 회의실을 선택하여 주십시오."));
				return;
			}
			try {
				ClientMasterController.getClient().sendToServer(data);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (arg0.getActionCommand().equals("예약 날짜 확인")) {
			data = new TransmissionData();
			data.setFlags(82);
			int select = lbrv.getTable().getSelectedRow();
			try {
				data.setKey(lbrm.getMyList().getList().get(select).getKey());
				data.setDateKey(lbrm.getMyList().getTempDateKey().get(select));
			} catch (ArrayIndexOutOfBoundsException e) {
				JOptionPane.showMessageDialog(null,
						("예약날짜를 확인할 회의실을 선택해 주십시오."));
				return;
			} catch (NullPointerException e) {
				JOptionPane.showMessageDialog(null, ("회의실을 조회해 주십시오."));
				return;
			}
			try {
				ClientMasterController.getClient().sendToServer(data);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (arg0.getActionCommand().equals("로그아웃")) {
			data = new TransmissionData();
			data.setFlags(100);
			try {
				ClientMasterController.getClient().sendToServer(data);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}