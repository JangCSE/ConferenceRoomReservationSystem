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
			JOptionPane.showMessageDialog(null, ("������ ��ҵǾ����ϴ�."));
			lbrm.setMyList(data.getRoomList());
		} else if (data.getFlags() == 83) {
			JOptionPane.showMessageDialog(null, (sd.format(data.getDate())));
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
		} else if (arg0.getActionCommand().equals("��������ϱ�")) {
			data = new TransmissionData();
			data.setFlags(90);
			int select = lbrv.getTable().getSelectedRow();
			try {
				data.setRoom(lbrm.getMyList().getList().get(select));// �̰ɷ� �������
				data.setDateKey(lbrm.getMyList().getTempDateKey().get(select));
				// data.setDate();//date ó��
			} catch (ArrayIndexOutOfBoundsException e) {
				JOptionPane.showMessageDialog(null, ("����� ȸ�ǽ��� �����Ͽ� �ֽʽÿ�."));
				return;
			} catch (NullPointerException e) {
				JOptionPane.showMessageDialog(null, ("����� ȸ�ǽ��� �����Ͽ� �ֽʽÿ�."));
				return;
			}
			try {
				ClientMasterController.getClient().sendToServer(data);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (arg0.getActionCommand().equals("���� ��¥ Ȯ��")) {
			data = new TransmissionData();
			data.setFlags(82);
			int select = lbrv.getTable().getSelectedRow();
			try {
				data.setKey(lbrm.getMyList().getList().get(select).getKey());
				data.setDateKey(lbrm.getMyList().getTempDateKey().get(select));
			} catch (ArrayIndexOutOfBoundsException e) {
				JOptionPane.showMessageDialog(null,
						("���೯¥�� Ȯ���� ȸ�ǽ��� ������ �ֽʽÿ�."));
				return;
			} catch (NullPointerException e) {
				JOptionPane.showMessageDialog(null, ("ȸ�ǽ��� ��ȸ�� �ֽʽÿ�."));
				return;
			}
			try {
				ClientMasterController.getClient().sendToServer(data);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (arg0.getActionCommand().equals("�α׾ƿ�")) {
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