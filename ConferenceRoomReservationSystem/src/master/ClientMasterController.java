package master;

import javax.swing.JFrame;

import login.LoginController;
import enterprise.EnterpriseDistributer;
import transmission.TransmissionData;

public class ClientMasterController extends JFrame {
	
	public ClientMasterController() {
		
	}
	
	private EnterpriseDistributer ed = new EnterpriseDistributer();
	private LoginController lc = new LoginController();

	public void perform(TransmissionData data) {		
		if (data.getFlags() < 10) {
			// user register
			
		} else if (data.getFlags() < 20) {
			// login
			lc.controlModel(data);
		} else if (data.getFlags() < 30) {
			// room register
			ed.distribute(data);
		} else if (data.getFlags() < 40) {
			// registered room list
			
		} else if (data.getFlags() < 50) {
			// delete room
			
		} else if (data.getFlags() < 60) {
			// book room
			
		} else if (data.getFlags() < 70) {
			// bookable room list
			
		} else if (data.getFlags() < 80) {
			// room info
			
		} else if (data.getFlags() < 90) {
			// booked room list
			
		} else if (data.getFlags() < 100) {
			// cancel booking
			
		}
	}
	
}