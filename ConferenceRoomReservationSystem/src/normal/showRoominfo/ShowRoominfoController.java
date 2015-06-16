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
import server.room.Room;
import transmission.TransmissionData;

public class ShowRoominfoController implements ActionListener {

	private ShowRoominfoModel shm;
	@SuppressWarnings("unused")
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

		if (arg0.getActionCommand().equals("텍스트파일로 저장")) {
			int select = lbrv.getTable().getSelectedRow();
			Room temp;
			try {
				temp = lbrm.getMyList().getList().get(select);
			} catch (ArrayIndexOutOfBoundsException e) {
				JOptionPane.showMessageDialog(null, ("회의실을 선택해주십시오."));
				return;
			} catch (NullPointerException e) {
				JOptionPane.showMessageDialog(null, ("회의실을 선택해주십시오."));
				return;
			}

			fw = null;
			dir = new File("C:\\ConferenceRoomReservationSystem");
			if (!dir.exists()) {
				dir.mkdir();
			}
			try {
				fw = new FileWriter("C:\\ConferenceRoomReservationSystem\\"
						+ temp.getName() + ".txt");
				fw.write("Name : ");
				fw.write(temp.getName());
				fw.write("\r\n");
				fw.write("City : ");
				fw.write(temp.getCity());
				fw.write("\r\n");
				fw.write("Detail Location : ");
				fw.write(temp.getDetailLocation());
				fw.write("\r\n");
				fw.write("Max Number : ");
				fw.write(Integer.toString(temp.getMaxNumber()));
				fw.write("\r\n");
				fw.write("Cost : ");
				fw.write(Integer.toString(temp.getCost()));
				fw.write("\r\n");
				fw.write("Detail : ");
				fw.write(temp.getDetail());
				fw.write("\r\n");
				fw.close();
			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, ("알수없는 오류가 발생했습니다."));
			}
			JOptionPane.showMessageDialog(null, ("텍스트 파일이 저장되었습니다."));
			return;
		} else if (arg0.getActionCommand().equals("예약정보보기")) {
			data = new TransmissionData();
			data.setFlags(70);

			int select = lbrv.getTable().getSelectedRow();

			try {
				data.setKey(lbrm.getMyList().getList().get(select).getKey());
			} catch (ArrayIndexOutOfBoundsException e) {
				JOptionPane.showMessageDialog(null, ("회의실을 선택해주십시오."));
				return;
			} catch (NullPointerException e) {
				JOptionPane.showMessageDialog(null, ("회의실을 선택해주십시오."));
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