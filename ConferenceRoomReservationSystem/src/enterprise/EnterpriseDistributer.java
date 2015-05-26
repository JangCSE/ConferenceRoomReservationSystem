package enterprise;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import transmission.TransmissionData;

public class EnterpriseDistributer extends JPanel{

	private RegisterRoomController rrc = new RegisterRoomController();
	private JTabbedPane EPUserTab = new JTabbedPane();
	private JPanel test = new JPanel();
	private JPanel test2 = new JPanel();
	
	public EnterpriseDistributer() {
		this.setLayout(new BorderLayout());
		EPUserTab.add("회의실 등록", test);
		EPUserTab.add("등록한 회의실 조회", test2);
		this.add(EPUserTab, BorderLayout.CENTER);
	}
	

	public void distribute(TransmissionData data) {
		if (data.getFlags() < 30) {
			// room register
			rrc.controlModel(data);
		} else if (data.getFlags() < 40) {
			// registered room list
			
		} else if (data.getFlags() < 50) {
			// delete room

		}
	}

}