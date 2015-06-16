package normal.showRoominfo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

import normal.listBookableRoom.ListBookableRoomModel;
import normal.listBookableRoom.ListBookableRoomView;
import master.ClientMasterController;
import transmission.TransmissionData;

public class ShowRoominfoController implements ActionListener {

	private ShowRoominfoModel shm;
	private ShowRoominfoView shv;
	private ListBookableRoomView lbrv;
	private ListBookableRoomModel lbrm;
	private FileWriter fw;
	private File dir;
	private TransmissionData data;

	public ShowRoominfoController(ShowRoominfoModel sm, ShowRoominfoView sv,
			ListBookableRoomModel lbrm, ListBookableRoomView lbrv) {
		this.shm = sm;
		this.shv = sv;
		this.lbrv = lbrv;
		this.lbrm = lbrm;
		lbrv.setShowRoominfoListener(this);
	}

	public void controlModel(TransmissionData data) {
		if (data.getFlags() == 71) {
			shm.setSelectedRoom(data.getRoom());
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

		if (arg0.getActionCommand().equals("�ؽ�Ʈ���Ϸ� ����")) {
			int select = lbrv.getTable().getSelectedRow();
			
			try {
				shm.setSelectedRoom(lbrm.getMyList().getList().get(select));
			} catch (ArrayIndexOutOfBoundsException e) {
				JOptionPane.showMessageDialog(null, ("ȸ�ǽ��� �������ֽʽÿ�."));
				return;
			} catch (NullPointerException e) {
				JOptionPane.showMessageDialog(null, ("ȸ�ǽ��� �������ֽʽÿ�."));
				return;
			}
			
			fw = null;
			dir = new File("C:\\ConferenceRoomReservationSystem");
			if (!dir.exists()) {
				dir.mkdir();
			}
			try {
				fw = new FileWriter("C:\\ConferenceRoomReservationSystem\\"
						+ shm.getSelectedRoom().getName() + ".txt");
				fw.write("Name : ");
				fw.write(shm.getSelectedRoom().getName());
				fw.write("\r\n");
				fw.write("City : ");
				fw.write(shm.getSelectedRoom().getCity());
				fw.write("\r\n");
				fw.write("Detail Location : ");
				fw.write(shm.getSelectedRoom().getDetailLocation());
				fw.write("\r\n");
				fw.write("Max Number : ");
				fw.write(Integer.toString(shm.getSelectedRoom().getMaxNumber()));
				fw.write("\r\n");
				fw.write("Cost : ");
				fw.write(Integer.toString(shm.getSelectedRoom().getCost()));
				fw.write("\r\n");
				fw.write("Detail : ");
				fw.write(shm.getSelectedRoom().getDetail());
				fw.write("\r\n");
				fw.close();
			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, ("�˼����� ������ �߻��߽��ϴ�."));
			}
			JOptionPane.showMessageDialog(null, ("�ؽ�Ʈ ������ ����Ǿ����ϴ�."));
			return;
		} else if (arg0.getActionCommand().equals("������������")) {

			data = new TransmissionData();
			data.setFlags(70);

			int select = lbrv.getTable().getSelectedRow();

			try {
				data.setKey(lbrm.getMyList().getList().get(select).getKey());
			} catch (ArrayIndexOutOfBoundsException e) {
				JOptionPane.showMessageDialog(null, ("ȸ�ǽ��� �������ֽʽÿ�."));
				return;
			} catch (NullPointerException e) {
				JOptionPane.showMessageDialog(null, ("ȸ�ǽ��� �������ֽʽÿ�."));
				return;
			}

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