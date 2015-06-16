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
	private JTextField nameField = new JTextField("회의실 이름을 입력해주세요.", 50);
	private JTextField maxNumField = new JTextField("수용인원을 숫자로 입력해주세요.");
	private JTextField costField = new JTextField("하루 임대료를 숫자로 입력해주세요.");
	private JTextField cityField = new JTextField("도시를 입력해주세요.");
	private JTextField detailLocField = new JTextField("상세 주소를 입력해주세요.");
	private JTextField detailField = new JTextField("부대시설에 관해 입력해주세요.");
	private JTextArea messageArea = new JTextArea("메시지");
	private JButton registerButton = new JButton("회의실 등록");
	private final Insets insets = new Insets(10, 20, 10, 10);

	public RegisterRoomView() {
		this.setBackground(Color.darkGray);
		messageArea.setEditable(false);
		registerRoomPanel.setBackground(Color.WHITE);
		registerRoomPanel.setLayout(new GridBagLayout());

		addComponent(registerRoomPanel, new JLabel("회의실 이름 :"), 0, 0, 1, 1,
				GridBagConstraints.EAST, GridBagConstraints.VERTICAL);
		addComponent(registerRoomPanel, nameField, 1, 0, 2, 1,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH);

		addComponent(registerRoomPanel, new JLabel("수용인원 :"), 0, 1, 1, 1,
				GridBagConstraints.EAST, GridBagConstraints.VERTICAL);
		addComponent(registerRoomPanel, maxNumField, 1, 1,
				GridBagConstraints.REMAINDER, 1, GridBagConstraints.CENTER,
				GridBagConstraints.BOTH);

		addComponent(registerRoomPanel, new JLabel("하루 임대료 :"), 0, 2, 1, 1,
				GridBagConstraints.EAST, GridBagConstraints.VERTICAL);
		addComponent(registerRoomPanel, costField, 1, 2,
				GridBagConstraints.REMAINDER, 1, GridBagConstraints.CENTER,
				GridBagConstraints.BOTH);

		addComponent(registerRoomPanel, new JLabel("도시 :"), 0, 3, 1, 1,
				GridBagConstraints.EAST, GridBagConstraints.VERTICAL);
		addComponent(registerRoomPanel, cityField, 1, 3,
				GridBagConstraints.REMAINDER, 1, GridBagConstraints.CENTER,
				GridBagConstraints.BOTH);

		addComponent(registerRoomPanel, new JLabel("상세 주소 :"), 0, 4, 1, 1,
				GridBagConstraints.EAST, GridBagConstraints.VERTICAL);
		addComponent(registerRoomPanel, detailLocField, 1, 4,
				GridBagConstraints.REMAINDER, 1, GridBagConstraints.CENTER,
				GridBagConstraints.BOTH);

		addComponent(registerRoomPanel, new JLabel("부대시설 :"), 0, 5, 1, 1,
				GridBagConstraints.EAST, GridBagConstraints.VERTICAL);
		addComponent(registerRoomPanel, detailField, 1, 5,
				GridBagConstraints.REMAINDER, 1, GridBagConstraints.CENTER,
				GridBagConstraints.BOTH);

		addComponent(registerRoomPanel, new JLabel("결과 메시지 :"), 0, 6, 1, 1,
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