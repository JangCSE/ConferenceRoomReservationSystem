package normal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import transmission.TransmissionData;

public class ShowRoominfoController implements ActionListener {

	private ShowRoominfoModel shm;
	private ShowRoominfoView shv;
	
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
		
	}
}
