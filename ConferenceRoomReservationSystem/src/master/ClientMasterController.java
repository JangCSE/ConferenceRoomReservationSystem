package master;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import normal.NormalDistributer;
import client.ChatClient;
import login.LoginController;
import login.LoginModel;
import login.LoginView;
import enterprise.EnterpriseDistributer;
import register.RegisterController;
import register.RegisterModel;
import register.RegisterView;
import transmission.TransmissionData;

@SuppressWarnings("serial")
public class ClientMasterController extends JFrame {

	private static ChatClient client;
	private JPanel cards;
	CardLayout c;

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
		rm.addObserver(rv);
		rc = new RegisterController(rm, rv);
	}

	public void initialGUI() {
		this.setTitle("회의실 예약 시스템");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(1280, 720);
		this.setVisible(true);

		cards = new JPanel(new CardLayout());
		cards.add(lv, "loginPanel");
		cards.add(rv, "registerPanel");
		cards.add(ed, "EPUser");
		cards.add(nd, "NMUser");
		c = (CardLayout) cards.getLayout();
		c.show(cards, "loginPanel");
		this.add(cards);
	}

	private EnterpriseDistributer ed = new EnterpriseDistributer();
	private NormalDistributer nd = new NormalDistributer();
	private LoginModel lm = new LoginModel();
	private LoginView lv = new LoginView();
	private RegisterModel rm = new RegisterModel();
	private RegisterView rv = new RegisterView();
	private RegisterController rc;
	private LoginController lc;

	public void perform(TransmissionData data) {

		if (data.getFlags() < 10) {
			// user register
			if (data.getFlags() == 1) {
				c.show(cards, "registerPanel");
			} else if (data.getFlags() == 3) {
				c.show(cards, "loginPanel");
			} else
				rc.controlModel(data);
		} else if (data.getFlags() < 20) {
			if (data.getFlags() == 12)
				c.show(cards, "NMUser");
			if (data.getFlags() == 13)
				c.show(cards, "EPUser");
			lc.controlModel(data);
		} else if (data.getFlags() < 30) {
			// room register
			ed.distribute(data);
		} else if (data.getFlags() < 40) {
			// registered room list
			ed.distribute(data);
		} else if (data.getFlags() < 50) {
			// delete room

		} else if (data.getFlags() < 60) {
			// book room
			nd.distribute(data);
		} else if (data.getFlags() < 70) {
			// bookable room list
			nd.distribute(data);
		} else if (data.getFlags() < 80) {
			// room info
			nd.distribute(data);
		} else if (data.getFlags() < 90) {
			// booked room list
			nd.distribute(data);
		} else if (data.getFlags() < 100) {
			// cancel booking
			nd.distribute(data);
		} else if (data.getFlags() < 110) {
			// log out
			c.show(cards, "loginPanel");
		}
	}

}