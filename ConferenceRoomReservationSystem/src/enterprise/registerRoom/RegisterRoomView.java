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
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class RegisterRoomView extends JPanel implements Observer {

	private JPanel registerRoomPanel = new JPanel();
	private JTextField nameField = new JTextField("ȸ�ǽ� �̸��� �Է����ּ���.", 50);
	private JTextField maxNumField = new JTextField("�����ο��� ���ڷ� �Է����ּ���.");
	private JTextField costField = new JTextField("�Ϸ� �Ӵ�Ḧ ���ڷ� �Է����ּ���.");
	private JTextField cityField = new JTextField("���ø� �Է����ּ���.");
	private JTextField detailLocField = new JTextField("�� �ּҸ� �Է����ּ���.");
	private JTextField detailField = new JTextField("�δ�ü��� ���� �Է����ּ���.");
	private JTextArea messageArea = new JTextArea("�޽���");
	private JButton registerButton = new JButton("ȸ�ǽ� ���");
	private final Insets insets = new Insets(10, 20, 10, 10);

	public RegisterRoomView() {
		this.setBackground(Color.darkGray);
		messageArea.setEditable(false);
		registerRoomPanel.setBackground(Color.WHITE);
		registerRoomPanel.setLayout(new GridBagLayout());

		addComponent(registerRoomPanel, new JLabel("ȸ�ǽ� �̸� :"), 0, 0, 1, 1,
				GridBagConstraints.EAST, GridBagConstraints.VERTICAL);
		addComponent(registerRoomPanel, nameField, 1, 0, 2, 1,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH);

		addComponent(registerRoomPanel, new JLabel("�����ο� :"), 0, 1, 1, 1,
				GridBagConstraints.EAST, GridBagConstraints.VERTICAL);
		addComponent(registerRoomPanel, maxNumField, 1, 1,
				GridBagConstraints.REMAINDER, 1, GridBagConstraints.CENTER,
				GridBagConstraints.BOTH);

		addComponent(registerRoomPanel, new JLabel("�Ϸ� �Ӵ�� :"), 0, 2, 1, 1,
				GridBagConstraints.EAST, GridBagConstraints.VERTICAL);
		addComponent(registerRoomPanel, costField, 1, 2,
				GridBagConstraints.REMAINDER, 1, GridBagConstraints.CENTER,
				GridBagConstraints.BOTH);

		addComponent(registerRoomPanel, new JLabel("���� :"), 0, 3, 1, 1,
				GridBagConstraints.EAST, GridBagConstraints.VERTICAL);
		addComponent(registerRoomPanel, cityField, 1, 3,
				GridBagConstraints.REMAINDER, 1, GridBagConstraints.CENTER,
				GridBagConstraints.BOTH);

		addComponent(registerRoomPanel, new JLabel("�� �ּ� :"), 0, 4, 1, 1,
				GridBagConstraints.EAST, GridBagConstraints.VERTICAL);
		addComponent(registerRoomPanel, detailLocField, 1, 4,
				GridBagConstraints.REMAINDER, 1, GridBagConstraints.CENTER,
				GridBagConstraints.BOTH);

		addComponent(registerRoomPanel, new JLabel("�δ�ü� :"), 0, 5, 1, 1,
				GridBagConstraints.EAST, GridBagConstraints.VERTICAL);
		addComponent(registerRoomPanel, detailField, 1, 5,
				GridBagConstraints.REMAINDER, 1, GridBagConstraints.CENTER,
				GridBagConstraints.BOTH);

		addComponent(registerRoomPanel, new JLabel("��� �޽��� :"), 0, 6, 1, 1,
				GridBagConstraints.EAST, GridBagConstraints.VERTICAL);
		addComponent(registerRoomPanel, messageArea, 1, 6,
				GridBagConstraints.REMAINDER, 1, GridBagConstraints.CENTER,
				GridBagConstraints.BOTH);

		addComponent(registerRoomPanel, registerButton, 2, 7, 1, 1,
				GridBagConstraints.EAST, GridBagConstraints.VERTICAL);

		addComponent(this, registerRoomPanel, 0, 0, 1, 1,
				GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL);

		makeFocusAll();
	}

	/*
	 * focus function. if user click text field, focus all automatically
	 */
	public void makeFocusAll() {
		nameField.addFocusListener(new java.awt.event.FocusAdapter() {
			public void focusGained(java.awt.event.FocusEvent evt) {
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						nameField.selectAll();
					}
				});
			}
		});

		maxNumField.addFocusListener(new java.awt.event.FocusAdapter() {
			public void focusGained(java.awt.event.FocusEvent evt) {
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						maxNumField.selectAll();
					}
				});
			}
		});

		costField.addFocusListener(new java.awt.event.FocusAdapter() {
			public void focusGained(java.awt.event.FocusEvent evt) {
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						costField.selectAll();
					}
				});
			}
		});

		cityField.addFocusListener(new java.awt.event.FocusAdapter() {
			public void focusGained(java.awt.event.FocusEvent evt) {
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						cityField.selectAll();
					}
				});
			}
		});

		detailLocField.addFocusListener(new java.awt.event.FocusAdapter() {
			public void focusGained(java.awt.event.FocusEvent evt) {
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						detailLocField.selectAll();
					}
				});
			}
		});

		detailField.addFocusListener(new java.awt.event.FocusAdapter() {
			public void focusGained(java.awt.event.FocusEvent evt) {
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						detailField.selectAll();
					}
				});
			}
		});
	}

	public String getMsgStr() {
		return messageArea.getText();
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

	/*
	 * GUI method
	 */
	private void addComponent(JPanel container, Component component, int gridx,
			int gridy, int gridwidth, int gridheight, int anchor, int fill) {
		GridBagConstraints gbc = new GridBagConstraints(gridx, gridy,
				gridwidth, gridheight, 1.0, 1.0, anchor, fill, insets, 0, 0);
		container.add(component, gbc);
	}

}