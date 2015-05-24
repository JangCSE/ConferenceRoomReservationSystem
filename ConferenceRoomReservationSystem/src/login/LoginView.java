package login;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
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

public class LoginView extends JPanel implements Observer {

	private JPanel idPanel = new JPanel();
	private JPanel pwPanel = new JPanel();
	private JPanel messagePanel = new JPanel();
	private JPanel loginPanel = new JPanel();
	private JPanel selectPanel = new JPanel();
	private JTextField idField = new JTextField("아이디");
	private JLabel idLabel = new JLabel("아이디");
	private JPasswordField pwField = new JPasswordField("비밀번호");
	private JLabel pwLabel = new JLabel("비밀번호");
	private JTextArea messageArea = new JTextArea("메시지");
	private JLabel messageLabel = new JLabel("메시지");
	private JButton loginButton = new JButton("로그인");
	private JRadioButton eupLogin = new JRadioButton("기업 사용자로 로그인");
	private JRadioButton nmuLogin = new JRadioButton("일반 사용자로 로그인");
	private final Insets insets = new Insets(0, 0, 0, 0);

	public LoginView() {
		this.setBackground(Color.WHITE);

		// id, pw, message panel
		loginPanel.setLayout(new GridBagLayout());
		addComponent(loginPanel, idPanel, 0, 0, 1, 1,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH);
		addComponent(loginPanel, pwPanel, 1, 0, 1, 1,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH);
		addComponent(loginPanel, messagePanel, 0, 1,
				GridBagConstraints.REMAINDER, 1, GridBagConstraints.CENTER,
				GridBagConstraints.BOTH);

		// select panel
		selectPanel.setLayout(new GridBagLayout());
		addComponent(selectPanel, eupLogin, 0, 0, 1, 1,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH);
		addComponent(selectPanel, nmuLogin, 0, 1, 1, 1,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH);

		this.add(selectPanel);
		this.add(loginPanel);

		idPanel.add(idLabel);
		idPanel.add(idField);

		pwPanel.add(pwLabel);
		pwPanel.add(pwField);
		messagePanel.add(messageLabel);
		messagePanel.add(messageArea);
		this.add(loginButton);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		System.out.println(((LoginModel) arg0).getMessage());
	}

	public void setLoginListener(ActionListener listener) {
	}

	private void addComponent(JPanel container, Component component, int gridx,
			int gridy, int gridwidth, int gridheight, int anchor, int fill) {
		GridBagConstraints gbc = new GridBagConstraints(gridx, gridy,
				gridwidth, gridheight, 1.0, 1.0, anchor, fill, insets, 0, 0);
		container.add(component, gbc);
	}

}