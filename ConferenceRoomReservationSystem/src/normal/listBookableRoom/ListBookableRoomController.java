package normal.listBookableRoom;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Date;
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
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getActionCommand().equals("��ȸ�ϱ�")) {
			data = new TransmissionData();
			data.setFlags(60);

			JTextField city = new JTextField();
			JTextField date = new JTextField();
			JTextField maxNum = new JTextField();
			JPanel panel = new JPanel(new GridLayout(0, 2));
			panel.add(new JLabel("����"));
			panel.add(city);
			panel.add(new JLabel("��¥"));
			panel.add(date);
			panel.add(new JLabel("�ִ��ο�"));
			panel.add(maxNum);

			JOptionPane.showMessageDialog(null, panel, "�����Է�",
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
							("�ִ� �ο��� ����ΰų� ���ڷ� �Է����ּ���."));
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
							("��¥ �Է� ����� YYYY-MM-DD �Դϴ�."));
					return;
				}
			}

			try {
				ClientMasterController.getClient().sendToServer(data);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (arg0.getActionCommand().equals("�����ϱ�")) {
			data = new TransmissionData();
			data.setFlags(50);
			int select = lbrv.getTable().getSelectedRow();
			lbrm.getMyList().getList().get(select);// �̰ɷ� ����

			try {
				ClientMasterController.getClient().sendToServer(data);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (arg0.getActionCommand().equals("������������")) {
			data = new TransmissionData();
			data.setFlags(70);
			int select = lbrv.getTable().getSelectedRow();
			data.setRoom(lbrm.getMyList().getList().get(select));

			try {
				ClientMasterController.getClient().sendToServer(data);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}