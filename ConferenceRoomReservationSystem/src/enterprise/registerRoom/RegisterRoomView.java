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

import register.RegisterModel;

public class RegisterRoomView extends JPanel implements Observer {
	// �޼��� �ߺ��Ǵ°� ���� ????
	private JPanel registerRoomPanel = new JPanel();
	private JTextField msgField = new JTextField("�޼���");
	private JTextField nameField = new JTextField("ȸ�ǽ� �̸�");
	private JTextField maxNumField = new JTextField("�ִ� ���� ���� �ο�");
	private JTextField costField = new JTextField("����");
	private JTextField cityField = new JTextField("����");
	private JTextField detailLocField = new JTextField("�� �ּ�");
	private JTextField detailField = new JTextField("������");
	private JTextArea messageArea = new JTextArea("�޽���");
	private JButton registerButton = new JButton("ȸ�ǽǵ��");
	private final Insets insets = new Insets(10, 10, 10, 10);

	public RegisterRoomView() {
		this.setBackground(Color.orange);
		messageArea.setEditable(false);
		registerRoomPanel.setBackground(Color.WHITE);
		registerRoomPanel.setLayout(new GridBagLayout());

		addComponent(registerRoomPanel, new JLabel("ȸ�ǽ� �̸� :"), 0, 0, 1, 1,
				GridBagConstraints.EAST, GridBagConstraints.VERTICAL);
		addComponent(registerRoomPanel, nameField, 1, 0, GridBagConstraints.REMAINDER,
				1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
		addComponent(registerRoomPanel, new JLabel("�ִ� ���� ���� �ο� :"), 0, 1, 1, 1,
				GridBagConstraints.EAST, GridBagConstraints.VERTICAL);
		addComponent(registerRoomPanel, maxNumField, 1, 1, GridBagConstraints.REMAINDER,
				1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
		addComponent(registerRoomPanel, new JLabel("���� :"), 0, 2, 1, 1,
				GridBagConstraints.EAST, GridBagConstraints.VERTICAL);
		addComponent(registerRoomPanel, costField, 1, 2, GridBagConstraints.REMAINDER,
				1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
		addComponent(registerRoomPanel, new JLabel("���� :"), 0, 3, 1, 1,
				GridBagConstraints.EAST, GridBagConstraints.VERTICAL);
		addComponent(registerRoomPanel, cityField, 1, 3, GridBagConstraints.REMAINDER,
				1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
		addComponent(registerRoomPanel, new JLabel("�� �ּ� :"), 0, 4, 1, 1,
				GridBagConstraints.EAST, GridBagConstraints.VERTICAL);
		addComponent(registerRoomPanel, detailLocField, 1, 4, GridBagConstraints.REMAINDER,
				1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
		addComponent(registerRoomPanel, new JLabel("�δ�ü� :"), 0, 5, 1, 1,
				GridBagConstraints.EAST, GridBagConstraints.VERTICAL);
		addComponent(registerRoomPanel, detailField, 1, 5, GridBagConstraints.REMAINDER,
				1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
		addComponent(registerRoomPanel, new JLabel("��� �޽���:"), 0, 6, 1, 1,
				GridBagConstraints.EAST, GridBagConstraints.VERTICAL);
		addComponent(registerRoomPanel, messageArea, 1, 6, GridBagConstraints.REMAINDER,
				1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
		addComponent(registerRoomPanel, registerButton, 1, 7, 1, 1,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH);
		
		
		addComponent(this, registerRoomPanel, 0, 0, 1, 1, GridBagConstraints.CENTER,
				GridBagConstraints.HORIZONTAL);
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
		registerButton.addActionListener(listener);
	}

	private void addComponent(JPanel container, Component component, int gridx,
			int gridy, int gridwidth, int gridheight, int anchor, int fill) {
		GridBagConstraints gbc = new GridBagConstraints(gridx, gridy,
				gridwidth, gridheight, 1.0, 1.0, anchor, fill, insets, 0, 0);
		container.add(component, gbc);
	}

}