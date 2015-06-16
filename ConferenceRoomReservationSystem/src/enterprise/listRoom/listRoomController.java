package enterprise.listRoom;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

import master.ClientMasterController;
import transmission.TransmissionData;

public class listRoomController implements ActionListener {

	private listRoomModel lrm;
	private listRoomView lrv;
	private TransmissionData data;
	private FileWriter fw;
	private File dir;
	private int select = 0;

	public listRoomController(listRoomModel lm, listRoomView lv) {
		lrm = lm;
		lrv = lv;
		lrv.setListRoomListener(this);
	}

	public void controlModel(TransmissionData data) {

		if (data.getFlags() == 31)
			lrm.setRL(data.getRoomList());
		else if (data.getFlags() == 41)
			JOptionPane.showMessageDialog(null, ("회의실이 삭제되었습니다."));
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
		} else if (arg0.getActionCommand().equals("텍스트 파일로 저장")) {
			fw = null;
			select = lrv.getTable().getSelectedRow();

			try {
				data.setRoom(lrm.getRL().getList().get(select));
			} catch (ArrayIndexOutOfBoundsException e) {
				JOptionPane.showMessageDialog(null, ("회의실을 선택해주십시오."));
				return;
			} catch (NullPointerException e) {
				JOptionPane.showMessageDialog(null, ("회의실을 선택해주십시오."));
				return;
			}

			dir = new File("C:\\ConferenceRoomReservationSystem");
			if (!dir.exists()) {
				dir.mkdir();
			}
			try {
				fw = new FileWriter("C:\\ConferenceRoomReservationSystem\\"
						+ String.valueOf(lrv.getTable().getValueAt(select, 0))
						+ ".txt");
				fw.write("Name : ");
				fw.write(String.valueOf(lrv.getTable().getValueAt(select, 0)));
				fw.write("\r\n");
				fw.write("City : ");
				fw.write(String.valueOf(lrv.getTable().getValueAt(select, 1)));
				fw.write("\r\n");
				fw.write("Detail Location : ");
				fw.write(String.valueOf(lrv.getTable().getValueAt(select, 2)));
				fw.write("\r\n");
				fw.write("Max Number : ");
				fw.write(String.valueOf(lrv.getTable().getValueAt(select, 3)));
				fw.write("\r\n");
				fw.write("Cost : ");
				fw.write(String.valueOf(lrv.getTable().getValueAt(select, 4)));
				fw.write("\r\n");
				fw.write("Detail : ");
				fw.write(String.valueOf(lrv.getTable().getValueAt(select, 5)));
				fw.write("\r\n");
				fw.close();
			} catch (Exception e) {
				System.out.println("error : " + e);
			}
			JOptionPane.showMessageDialog(null, ("텍스트 파일이 저장되었습니다."));
		} else if (arg0.getActionCommand().equals("로그아웃")) {
			data = new TransmissionData();
			data.setFlags(100);
			try {
				ClientMasterController.getClient().sendToServer(data);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (arg0.getActionCommand().equals("삭제하기")) {
			data = new TransmissionData();
			data.setFlags(40);

			int row = lrv.getTable().getSelectedRow();

			data.setKey(lrm.getRL().getList().get(row).getKey());

			try {
				ClientMasterController.getClient().sendToServer(data);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}