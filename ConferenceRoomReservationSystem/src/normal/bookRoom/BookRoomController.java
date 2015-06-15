package normal.bookRoom;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import master.ClientMasterController;
import server.room.Room;
import server.room.reservedDate;
import transmission.TransmissionData;

public class BookRoomController implements ActionListener {

	private BookRoomModel rrm = new BookRoomModel();
	private BookRoomView rrv = new BookRoomView();
	private TransmissionData data;
	private Room room;
	private ArrayList<reservedDate> rd;
	private reservedDate red;

	public BookRoomController(BookRoomModel rrm, BookRoomView rrv) {
		this.rrm = rrm;
		this.rrv = rrv;
		rrv.setListener(this);
	}

	public void controlModel(TransmissionData data) {

		if (data.getFlags() == 53) {
			rrm.setSelectedRoom(data.getRoom());
		} else if (data.getFlags() == 51) {
			rrm.setMessage(data.getMessage());
		} else if (data.getFlags() == 54) {
			rrm.setMessage(data.getMessage());
		}

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

		if (arg0.getActionCommand().equals("되돌아가기")) {
			data = new TransmissionData();
			data.setFlags(60);

			try {
				ClientMasterController.getClient().sendToServer(data);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;
		}
		
		if(stringTodate(rrv.getDateStr()) ==  null)
			return;
		
		rrm.setDate(stringTodate(rrv.getDateStr()));
		
		if(rrm.isRepeated()) {
			rrm.setMessage("이미  예약된 날짜 입니다.");
			return;
		} else {
			data = new TransmissionData();
			red = new reservedDate();
			data.setFlags(50);
			data.setDate(rrm.getDate());
			data.setRoom(rrm.getSelectedRoom());

			try {
				ClientMasterController.getClient().sendToServer(data);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			red.setDate(rrm.getDate());
			rrm.getSelectedRoom().getBookingUserKeyList().add(red);
		}

	}

	public Date stringTodate(String strDate) {
		Date to = null;
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			to = (Date) transFormat.parse(strDate);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					("날짜 입력 양식은 YYYY-MM-DD 입니다."));
		}
		return to;
	}
}