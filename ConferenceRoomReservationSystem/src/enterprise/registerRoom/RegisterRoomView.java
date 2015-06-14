package enterprise.registerRoom;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class RegisterRoomView extends JPanel implements Observer {
	// 메세지 중복되는거 있음 ????
	private JPanel registerRoomPanel = new JPanel();
	private JTextField msgField = new JTextField("메세지");
	private JTextField nameField = new JTextField("회의실 이름");
	private JTextField maxNumField = new JTextField("최대 수용 가능 인원");
	private JTextField costField = new JTextField("가격");
	private JTextField cityField = new JTextField("도시");
	private JTextField detailLocField = new JTextField("상세 주소");
	private JTextField detailField = new JTextField("디테일");
	private JTextArea messageArea = new JTextArea("메시지");
	private JButton backButton = new JButton("되돌아가기");
	private final Insets insets = new Insets(10, 10, 10, 10);

	public RegisterRoomView() {
		this.setBackground(Color.orange);
		messageArea.setEditable(false);
		registerRoomPanel.setBackground(Color.WHITE);
		registerRoomPanel.setLayout(new GridBagLayout());

		addComponent(registerRoomPanel, new JLabel("메세지 : "), 0, 1, 1, 1,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH);
		addComponent(registerRoomPanel, new JLabel("회의실 이름 : "), 0, 2, 1, 1,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH);
		addComponent(registerRoomPanel, new JLabel("최대 수용 인원 : "), 0, 3, 1, 1,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH);
		addComponent(registerRoomPanel, new JLabel("도시 : "), 0, 4, 1, 1,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH);
		addComponent(registerRoomPanel, new JLabel("상세 주소 : "), 0, 5, 1, 1,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH);
		addComponent(registerRoomPanel, new JLabel("Detail : "), 0, 6, 1, 1,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH);
		addComponent(registerRoomPanel, messageArea, 1, 7,
				GridBagConstraints.REMAINDER, 1, GridBagConstraints.CENTER,
				GridBagConstraints.BOTH);
		addComponent(this, registerRoomPanel, 0, 0, 1, 1,
				GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL);
	}

	public String getMsgStr() {
		return msgField.getText();
	}

	public String getNameStr() {
		return nameField.getText();
	}

	public String getMaxNumStr() {
		return maxNumField.getText();
	}

	public String getCost() {
		return costField.getText();
	}

	public String getCityStr() {
		return cityField.getText();
	}

	public String getDetailLoc() {
		return detailLocField.getText();
	}

	public String getDetail() {
		return detailField.getText();
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		messageArea.setText(((RegisterRoomModel) arg0).getMessage());
	}

	public void setListener(ActionListener listener) {
		backButton.addActionListener(listener);
	}

	private void addComponent(JPanel container, Component component, int gridx,
			int gridy, int gridwidth, int gridheight, int anchor, int fill) {
		GridBagConstraints gbc = new GridBagConstraints(gridx, gridy,
				gridwidth, gridheight, 1.0, 1.0, anchor, fill, insets, 0, 0);
		container.add(component, gbc);
	}

}