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
			JOptionPane.showMessageDialog(null, ("ȸ�ǽ��� ���������� ����Ǿ����ϴ�."));
		else if (data.getFlags() == 52)
			JOptionPane.showMessageDialog(null, ("�ִ� 3���� ȸ�ǽ��� ������ �� �ֽ��ϴ�."));
		else if (data.getFlags() == 53)
			JOptionPane.showMessageDialog(null, ("�̹� ����� ��¥ �Դϴ�."));
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getActionCommand().equals("��ȸ�ϱ�")) {
			search();
		} else if (arg0.getActionCommand().equals("�����ϱ�")) {
			book();
		}

	}

	private void book() {
		JTextField date = new JTextField();
		JPanel panel = new JPanel(new GridLayout(0, 1));
		panel.add(new JLabel("������ ��¥"));
		panel.add(date);

		JOptionPane.showMessageDialog(null, panel, "�����Է�",
				JOptionPane.QUESTION_MESSAGE);
		
		int select = lbrv.getTable().getSelectedRow();
		
		// book this room
		try{
			data.setRoom(lbrm.getMyList().getList().get(select));
		} catch(ArrayIndexOutOfBoundsException e) {
			JOptionPane.showMessageDialog(null, ("������ ȸ�ǽ��� �����Ͽ� �ֽʽÿ�."));
			return;
		} catch(NullPointerException e) {
			JOptionPane.showMessageDialog(null, ("������ ȸ�ǽ��� �����Ͽ� �ֽʽÿ�."));
			return;
		}
		
		if(date.getText().equals("")) {
			JOptionPane.showMessageDialog(null, ("��¥�� �Է��� �ֽʽÿ�."));
			return;
		}
		
		if (stringToDate(date.getText()) == null)
			return;

		data = new TransmissionData();
		data.setFlags(50);
		data.setDate(stringToDate(date.getText()));
		data.setKey(lbrm.getMyList().getList().get(select).getKey());

		try {
			ClientMasterController.getClient().sendToServer(data);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private Date stringToDate(String strDate) {
		Date to = null;
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			to = (Date) transFormat.parse(strDate);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, ("��¥ �Է� ����� YYYY-MM-DD �Դϴ�."));
		}
		return to;
	}

	private void search() {
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
	}
}