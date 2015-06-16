package login;

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
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/*
 * This class is about to login
 * MVC view
 */
@SuppressWarnings("serial")
public class LoginView extends JPanel implements Observer {

	private JPanel loginPanel = new JPanel();
	private JTextField idField = new JTextField("���̵�");
	private JPasswordField pwField = new JPasswordField("��й�ȣ");
	private JTextArea messageArea = new JTextArea("�޽���");
	private JButton loginButton = new JButton("�α���");
	private JButton registerButton = new JButton("ȸ������");
	private JRadioButton epuLogin = new JRadioButton("��� ����ڷ� �α���");
	private JRadioButton nmuLogin = new JRadioButton("�Ϲ� ����ڷ� �α���");
	private final Insets insets = new Insets(10, 10, 10, 10);

	public LoginView() {
		this.setBackground(Color.darkGray);
		messageArea.setEditable(false);
		loginPanel.setBackground(Color.WHITE);
		loginPanel.setLayout(new GridBagLayout());
		addComponent(loginPanel, epuLogin, 0, 0, 1, 1,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH);
		addComponent(loginPanel, nmuLogin, 2, 0, 1, 1,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH);
		addComponent(loginPanel, new JLabel("���̵� :"), 0, 1, 1, 1,
				GridBagConstraints.EAST, GridBagConstraints.VERTICAL);
		addComponent(loginPanel, idField, 1, 1, GridBagConstraints.REMAINDER,
				1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
		addComponent(loginPanel, new JLabel("��й�ȣ :"), 0, 2, 1, 1,
				GridBagConstraints.EAST, GridBagConstraints.VERTICAL);
		addComponent(loginPanel, pwField, 1, 2, GridBagConstraints.REMAINDER,
				1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
		addComponent(loginPanel, new JLabel("��� �޽��� :"), 0, 3, 1, 1,
				GridBagConstraints.EAST, GridBagConstraints.VERTICAL);
		addComponent(loginPanel, messageArea, 1, 3,
				GridBagConstraints.REMAINDER, 1, GridBagConstraints.CENTER,
				GridBagConstraints.BOTH);
		addComponent(loginPanel, loginButton, 3, 4, 1, 1,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH);
		addComponent(loginPanel, registerButton, 4, 4, 1, 1,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH);
		addComponent(this, loginPanel, 0, 0, 1, 1, GridBagConstraints.CENTER,
				GridBagConstraints.HORIZONTAL);

		makeFocusAll();
	}

	/*
	 * focusing method
	 */
	public void makeFocusAll() {
		idField.addFocusListener(new java.awt.event.FocusAdapter() {
			public void focusGained(java.awt.event.FocusEvent evt) {
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						idField.selectAll();
					}
				});
			}
		});

		pwField.addFocusListener(new java.awt.event.FocusAdapter() {
			public void focusGained(java.awt.event.FocusEvent evt) {
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						pwField.selectAll();
					}
				});
			}
		});
	}

	public void logout() {
		idField.setText("���̵�");
		pwField.setText("��й�ȣ");
	}

	public String getIdStr() {
		return idField.getText();
	}

	public String getPwStr() {
		return new String(pwField.getPassword());
	}

	public boolean isEpu() {
		return epuLogin.isSelected();
	}

	public boolean isNmu() {
		return nmuLogin.isSelected();
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		messageArea.setText(((LoginModel) arg0).getMessage());
	}

	public void setLoginListener(ActionListener listener) {
		loginButton.addActionListener(listener);
		registerButton.addActionListener(listener);
	}

	private void addComponent(JPanel container, Component component, int gridx,
			int gridy, int gridwidth, int gridheight, int anchor, int fill) {
		GridBagConstraints gbc = new GridBagConstraints(gridx, gridy,
				gridwidth, gridheight, 1.0, 1.0, anchor, fill, insets, 0, 0);
		container.add(component, gbc);
	}

}