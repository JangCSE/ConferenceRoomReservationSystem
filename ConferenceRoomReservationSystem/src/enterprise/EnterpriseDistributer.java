package enterprise;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import enterprise.listRoom.listRoomController;
import enterprise.listRoom.listRoomModel;
import enterprise.listRoom.listRoomView;
import enterprise.registerRoom.RegisterRoomController;
import enterprise.registerRoom.RegisterRoomModel;
import enterprise.registerRoom.RegisterRoomView;
import transmission.TransmissionData;

@SuppressWarnings("serial")
public class EnterpriseDistributer extends JPanel {

	private JTabbedPane EPUserTab = new JTabbedPane();
	private RegisterRoomModel rrm = new RegisterRoomModel();
	private RegisterRoomView rrv = new RegisterRoomView();
	private RegisterRoomController rrc;
	private listRoomModel lrm = new listRoomModel();
	private listRoomView lrv = new listRoomView();
	private listRoomController lrc;

	public EnterpriseDistributer() {
		rrm.addObserver(rrv);
		rrc = new RegisterRoomController(rrm, rrv);

		lrm.addObserver(lrv);
		lrc = new listRoomController(lrm, lrv);

		this.setLayout(new BorderLayout());
		EPUserTab.add("회의실 등록", rrv);
		EPUserTab.add("등록한 회의실 조회", lrv);
		this.add(EPUserTab, BorderLayout.CENTER);
	}

	/*
	 * 서버로부터 받은 데이터를 각 기능에 적절하게 할당하는 부분
	 */
	public void distribute(TransmissionData data) {

		if (data.getFlags() < 30) {
			// room register
			rrc.controlModel(data);
		} else if (data.getFlags() < 40) {
			lrc.controlModel(data);
		} else if (data.getFlags() < 50) {
			// delete room
			lrc.controlModel(data);
		}
	}

}