package normal.listBookableRoom;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import master.ClientMasterController;
import server.room.Room;
import transmission.TransmissionData;

public class ListBookableRoomController implements ActionListener {

	private ListBookableRoomModel lbrm;
	private ListBookableRoomView lbrv;
	private TransmissionData data;
	static SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");

	public ListBookableRoomController(ListBookableRoomModel lbrm,
			ListBookableRoomView lbrv) {
		this.lbrm = lbrm;
		this.lbrv = lbrv;
		lbrv.setListBookalbeRoomListener(this);
	}

	public void controlModel(TransmissionData data) {

		if (data.getFlags() == 61) {
			lbrm.setMyList(data.getRoomList());
		} else if (data.getFlags() == 51)
			JOptionPane.showMessageDialog(null, ("회의실이 성공적으로 예약되었습니다."));
		else if (data.getFlags() == 51)
			JOptionPane.showMessageDialog(null, ("최대 3개의 회의실을 예약할 수 있습니다."));
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getActionCommand().equals("조회하기")) {
			search();
		} else if (arg0.getActionCommand().equals("예약하기")) {
			book();
		}

	}

	private void book() {
		JTextField date = new JTextField();
		JPanel panel = new JPanel(new GridLayout(0, 1));
		panel.add(new JLabel("예약할 날짜"));
		panel.add(date);

		JOptionPane.showMessageDialog(null, panel, "정보입력",
				JOptionPane.QUESTION_MESSAGE);

		int select = lbrv.getTable().getSelectedRow();
		// book this room
		data.setRoom(lbrm.getMyList().getList().get(select));

		// System.out.println(date.getText());

		if (stringToDate(date.getText()) == null)
			return;

		if (isRepeated(lbrm.getMyList().getList().get(select),
				stringToDate(date.getText()))) {
			return;
		} else {
			data = new TransmissionData();
			data.setFlags(50);
			data.setDate(stringToDate(date.getText()));
			data.setRoom(lbrm.getMyList().getList().get(select));

			try {
				ClientMasterController.getClient().sendToServer(data);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private Date stringToDate(String strDate) {
		Date to = null;
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			to = (Date) transFormat.parse(strDate);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, ("날짜 입력 양식은 YYYY-MM-DD 입니다."));
		}
		return to;
	}

	private boolean isRepeated(Room room, Date date) {
		int end = room.getBookingUserKeyList().size();

		for (int i = 0; i < end; i++) {
			if (room.getBookingUserKeyList().get(i).getDate().equals(date))
				return true;
		}
		return false;
	}

	private void search() {
		data = new TransmissionData();
		data.setFlags(60);

		JTextField city = new JTextField();
		JTextField date = new JTextField();
		JTextField maxNum = new JTextField();
		JPanel panel = new JPanel(new GridLayout(0, 2));
		panel.add(new JLabel("도시"));
		panel.add(city);
		panel.add(new JLabel("날짜"));
		panel.add(date);
		panel.add(new JLabel("최대인원"));
		panel.add(maxNum);

		JOptionPane.showMessageDialog(null, panel, "정보입력",
				JOptionPane.QUESTION_MESSAGE);

		int maxNumInteger;

		if (maxNum.getText().equals("")) {
			maxNumInteger = 0;
		} else {
			try {
				maxNumInteger = Integer.parseInt(maxNum.getText());
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null,
						("최대 인원은 비워두거나 숫자로 입력해주세요."));
				return;
			}
		}

		data.setRoom(new Room("", city.getText(), "", maxNumInteger, 0, ""));

		if (date.getText().equals("")) {
			data.setDate(null);
		} else {
			try {
				data.setDate(sd.parse(date.getText()));
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null,
						("날짜 입력 양식은 YYYY-MM-DD 입니다."));
				return;
			}
		}

		try {
			ClientMasterController.getClient().sendToServer(data);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}