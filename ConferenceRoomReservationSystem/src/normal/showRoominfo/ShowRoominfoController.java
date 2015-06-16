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
			System.out.println(data.getRoom().getBookingUserKeyList().size() + data.getKey());
			shm.setSelectedRoom(data.getRoom());
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

		if (arg0.getActionCommand().equals("파일에 저장")) {
			fw = null;
			dir = new File("C:\\ConferenceRoomReservationSystem");
			if (!dir.exists()) {
				dir.mkdir();
			}
			try {
				fw = new FileWriter("C:\\ConferenceRoomReservationSystem\\"
						+ shm.getSelectedRoom().getName() + ".txt");
				fw.write(shm.getSelectedRoom().getName());
				fw.write(shm.getSelectedRoom().getCity());
				fw.write(shm.getSelectedRoom().getDetailLocation());
				fw.write(Integer.toString(shm.getSelectedRoom().getMaxNumber()));
				fw.write(Integer.toString(shm.getSelectedRoom().getCost()));
				fw.write(shm.getSelectedRoom().getDetail());
				fw.close();
			} catch (Exception e) {
				System.out.println("error : " + e);
			}

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