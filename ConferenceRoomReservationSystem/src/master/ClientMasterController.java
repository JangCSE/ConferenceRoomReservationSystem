package master;

import javax.swing.JFrame;
import client.ChatClient;
import login.LoginController;
import login.LoginModel;
import login.LoginView;
import enterprise.EnterpriseDistributer;
import transmission.TransmissionData;

@SuppressWarnings("serial")
public class ClientMasterController extends JFrame {
	
	private static ChatClient client;
	
	public ClientMasterController() {
		initialSetting();
	}
	
	public ClientMasterController(ChatClient cc) {
		client = cc;
		initialSetting();
	}
	
	public static ChatClient getClient() {
		return client;
	}
	
	public void initialSetting() {
		lm.addObserver(lv);
		lc = new LoginController(lm, lv);
	}
	
	public void initialGUI() {
		this.setTitle("회의실 예약 시스템");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(1280, 720);
		this.setVisible(true);
		
		this.add(lv);
	}
	
	private EnterpriseDistributer ed = new EnterpriseDistributer();
	private LoginModel lm = new LoginModel();
	private LoginView lv = new LoginView();;
	private LoginController lc;

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