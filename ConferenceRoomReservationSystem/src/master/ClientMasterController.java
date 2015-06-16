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

/*
 * This class is client master controller.
 * If server send transmission data, this controls methods by data's flag
 */
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

		int flag = data.getFlags();

		if (flag < 10) {
			// user register
			if (flag == 1) {
				// request register
				c.show(cards, "registerPanel");
			} else if (flag == 3) {
				// cancel register
				c.show(cards, "loginPanel");
			} else
				// user register
				rc.controlModel(data);
		} else if (flag < 20) {
			/*
			 * flag 12 is show nm user flag 13 is show ep user
			 */
			if (flag == 12)
				c.show(cards, "NMUser");
			if (flag == 13)
				c.show(cards, "EPUser");
			lc.controlModel(data);
		} else if (flag < 50) {
			/*
			 * if flag is 20~29, register room. else if flag is 30~39, show
			 * registered room list. else if flag is 40~49, delete room.
			 */
			// delete room
			ed.distribute(data);
		} else if (flag < 100) {
			/*
			 * if flag is 50~59, book room. else if flag is 60~69 show bookable
			 * room list. else if flag is 70~79, show room info. else if flag is
			 * 80~89, show booked room list. else if flag is 90~99, cancel
			 * booking
			 */
			nd.distribute(data);
		} else if (flag < 110) {
			// logout
			c.show(cards, "loginPanel");
			lc.logout();
		}
	}

}