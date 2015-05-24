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

	private JPanel loginPanel = new JPanel();
	private JTextField idField = new JTextField("아이디");
	private JPasswordField pwField = new JPasswordField("비밀번호");
	private JTextArea messageArea = new JTextArea("메시지");
	private JButton loginButton = new JButton("로그인");
	private JRadioButton eupLogin = new JRadioButton("기업 사용자로 로그인");
	private JRadioButton nmuLogin = new JRadioButton("일반 사용자로 로그인");
	private final Insets insets = new Insets(10, 10, 10, 10);

	public LoginView() {
		this.setBackground(Color.darkGray);
		loginPanel.setBackground(Color.WHITE);
		loginPanel.setLayout(new GridBagLayout());
		addComponent(loginPanel, eupLogin, 0, 0, 1, 1,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH);
		addComponent(loginPanel, nmuLogin, 2, 0, 1, 1,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH);
		addComponent(loginPanel, new JLabel("아이디 :"), 0, 1, 1, 1,
				GridBagConstraints.EAST, GridBagConstraints.VERTICAL);
		addComponent(loginPanel, idField, 1, 1, GridBagConstraints.REMAINDER, 1,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH);
		addComponent(loginPanel, new JLabel("비밀번호 :"), 0, 2, 1, 1,
				GridBagConstraints.EAST, GridBagConstraints.VERTICAL);
		addComponent(loginPanel, pwField, 1, 2, GridBagConstraints.REMAINDER, 1,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH);
		addComponent(loginPanel, new JLabel("메시지 :"), 0, 3, 1, 1,
				GridBagConstraints.EAST, GridBagConstraints.VERTICAL);
		addComponent(loginPanel, messageArea, 1, 3, GridBagConstraints.REMAINDER, 1,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH);
		addComponent(loginPanel, loginButton, 3, 4, 1, 1,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH);
		addComponent(this, loginPanel, 0, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL);
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