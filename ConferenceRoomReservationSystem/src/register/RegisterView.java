package register;

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

import login.LoginModel;

public class RegisterView extends JPanel implements Observer {

	private JPanel registerPanel = new JPanel();
	private JTextField idField = new JTextField("아이디");
	private JPasswordField pwField = new JPasswordField("비밀번호");
	private JPasswordField pwConfirmField = new JPasswordField("비밀번호");
	private JTextField nameField = new JTextField("이름");
	private JTextField emailField = new JTextField("이메일");
	private JTextField contactField = new JTextField("연락처");
	private JTextArea messageArea = new JTextArea("메시지");
	private JButton confirmButton = new JButton("확인");
	private JButton cancelButton = new JButton("취소");
	private JRadioButton epuRegister = new JRadioButton("기업 사용자로 회원가입");
	private JRadioButton nmuRegister = new JRadioButton("일반 사용자로 회원가입");
	private final Insets insets = new Insets(10, 10, 10, 10);
	
	public RegisterView() {
		this.setBackground(Color.darkGray);
		messageArea.setEditable(false);
		registerPanel.setBackground(Color.WHITE);
		registerPanel.setLayout(new GridBagLayout());
		addComponent(registerPanel, epuRegister, 0, 0, 1, 1,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH);
		addComponent(registerPanel, nmuRegister, 2, 0, 1, 1,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH);
		
		addComponent(registerPanel, new JLabel("아이디 :"), 0, 1, 1, 1,
				GridBagConstraints.EAST, GridBagConstraints.VERTICAL);
		addComponent(registerPanel, idField, 1, 1, GridBagConstraints.REMAINDER,
				1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
		
		addComponent(registerPanel, new JLabel("비밀번호 :"), 0, 2, 1, 1,
				GridBagConstraints.EAST, GridBagConstraints.VERTICAL);
		addComponent(registerPanel, pwField, 1, 2, GridBagConstraints.REMAINDER,
				1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
		
		addComponent(registerPanel, new JLabel("비밀번호 확인 :"), 0, 3, 1, 1,
				GridBagConstraints.EAST, GridBagConstraints.VERTICAL);
		addComponent(registerPanel, pwConfirmField, 1, 3, GridBagConstraints.REMAINDER,
				1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
		
		addComponent(registerPanel, new JLabel("이름 :"), 0, 4, 1, 1,
				GridBagConstraints.EAST, GridBagConstraints.VERTICAL);
		addComponent(registerPanel, nameField, 1, 4, GridBagConstraints.REMAINDER,
				1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
		
		addComponent(registerPanel, new JLabel("이메일 :"), 0, 5, 1, 1,
				GridBagConstraints.EAST, GridBagConstraints.VERTICAL);
		addComponent(registerPanel, emailField, 1, 5, GridBagConstraints.REMAINDER,
				1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
		
		addComponent(registerPanel, new JLabel("연락처 :"), 0, 6, 1, 1,
				GridBagConstraints.EAST, GridBagConstraints.VERTICAL);
		addComponent(registerPanel, contactField, 1, 6, GridBagConstraints.REMAINDER,
				1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
		
		addComponent(registerPanel, new JLabel("결과 메시지 :"), 0, 7, 1, 1,
				GridBagConstraints.EAST, GridBagConstraints.VERTICAL);
		addComponent(registerPanel, messageArea, 1, 7,
				GridBagConstraints.REMAINDER, 1, GridBagConstraints.CENTER,
				GridBagConstraints.BOTH);
		addComponent(registerPanel, cancelButton, 3, 8, 1, 1,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH);
		addComponent(registerPanel, confirmButton, 4, 8, 1, 1,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH);
		addComponent(this, registerPanel, 0, 0, 1, 1, GridBagConstraints.CENTER,
				GridBagConstraints.HORIZONTAL);
	}

	public String getIdStr() {
		return idField.getText();
	}
	
	public String getPwStr() {
		return new String(pwField.getPassword());
	}
	
	public String getPwConfimStr() {
		return new String(pwConfirmField.getPassword());
	}
	
	public String getNameStr() {
		return nameField.getText();
	}
	
	public String getEmailStr() {
		return emailField.getText();
	}
	
	public String getContactStr() {
		return contactField.getText();
	}
	
	public boolean isEpu() {
		return epuRegister.isSelected();
	}

	public boolean isNmu() {
		return nmuRegister.isSelected();
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		messageArea.setText(((RegisterModel) arg0).getMessage());
	}
	
	public void setLoginListener(ActionListener listener) {
		confirmButton.addActionListener(listener);
		cancelButton.addActionListener(listener);
	}
	
	private void addComponent(JPanel container, Component component, int gridx,
			int gridy, int gridwidth, int gridheight, int anchor, int fill) {
		GridBagConstraints gbc = new GridBagConstraints(gridx, gridy,
				gridwidth, gridheight, 1.0, 1.0, anchor, fill, insets, 0, 0);
		container.add(component, gbc);
	}

}