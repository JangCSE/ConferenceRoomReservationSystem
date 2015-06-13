package normal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import master.ClientMasterController;
import transmission.TransmissionData;

public class ShowRoominfoController implements ActionListener {

	private ShowRoominfoModel shm;
	private ShowRoominfoView shv;
	private FileWriter fw;
	private File dir;
	
	public ShowRoominfoController(ShowRoominfoModel sm, ShowRoominfoView sv) {
		shm = sm;
		shv = sv;
		shm.addObserver(shv);
	}
	
	public void controlModel(TransmissionData data) {

		if(data.getFlags() == 71) {
			shm.setSelectedRoom(data.getRoom());
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
		if (arg0.getActionCommand().equals("파일에 저장")) {
			fw = null;
			dir = new File("C:\\ConferenceRoomReservationSystem");
			if(!dir.exists()) {
				dir.mkdir();
			}
			try {
				fw = new FileWriter("C:\\ConferenceRoomReservationSystem\\" + shm.getSelectedRoom().getName() + ".txt");
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
		}
		
	}
}
