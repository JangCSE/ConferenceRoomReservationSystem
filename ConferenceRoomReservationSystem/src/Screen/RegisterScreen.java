package Screen;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Client.UserClient;
import Console.ClientConsole;
import User.EnterpriseUser;
import User.EnterpriseUserList;
import User.NormalUser;

public class RegisterScreen extends JFrame implements ActionListener {

	private static RegisterScreen RS = new RegisterScreen();

	public static RegisterScreen getRegisterScreen() {
		return RS;
	}

	public RegisterScreen() {
	}

	private JPanel buttonPanel = new JPanel();
	private JButton NormalUserButton = new JButton("일반 사용자 등록");
	private JButton EnterpriseButton = new JButton("기업 사용자 등록");

	public void chooseType() {
		setTitle("회원가입 선택");

		RS.setVisible(true);
		RS.setSize(400, 200);

		buttonPanel.setLayout(new GridLayout(1, 2));
		buttonPanel.add(NormalUserButton);
		buttonPanel.add(EnterpriseButton);
		NormalUserButton.setFont(new Font("Malgun Gothic", Font.BOLD, 20));
		EnterpriseButton.setFont(new Font("Malgun Gothic", Font.BOLD, 20));

		RS.add(buttonPanel);

		EnterpriseButton.addActionListener(this);
		NormalUserButton.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String msg = e.getActionCommand();

		if (msg.equals("기업 사용자 등록")) {

			EnterpriseUser EU;

			JTextField id = new JTextField("id");
			JTextField password = new JTextField();
			JTextField name = new JTextField();
			JTextField email = new JTextField();
			JTextField contact = new JTextField();

			JLabel idl = new JLabel("아이디");
			JLabel passwordl = new JLabel("패스워드");
			JLabel namel = new JLabel("이름");
			JLabel emaill = new JLabel("이메일");
			JLabel contactl = new JLabel("연락처");

			idl.setFont(new Font("Malgun Gothic", Font.BOLD, 20));
			passwordl.setFont(new Font("Malgun Gothic", Font.BOLD, 20));
			namel.setFont(new Font("Malgun Gothic", Font.BOLD, 20));
			emaill.setFont(new Font("Malgun Gothic", Font.BOLD, 20));
			contactl.setFont(new Font("Malgun Gothic", Font.BOLD, 20));

			Object[] message = { idl, id, passwordl, password, namel, name,
					emaill, email, contactl, contact };

			JOptionPane ask = new JOptionPane(message,
					JOptionPane.QUESTION_MESSAGE, JOptionPane.OK_CANCEL_OPTION);
			ask.setFont(new Font("Malgun Gothic", Font.BOLD, 20));
			ask.createDialog(null, "기업 사용자 회원등록").setVisible(true);

			System.out.println(ask.getValue() + ">>");

			try {

				if ( (Integer)ask.getValue() == 2)
					return;

				EU = new EnterpriseUser(id.getText(), password.getText(),
						name.getText(), email.getText(), contact.getText());

				System.out.println(EU.getId() + ", " + EU.getPassword());

				int registerSuccess = register(EU);

				JLabel fail = new JLabel("아이디가 중복되었습니다.");
				JLabel success = new JLabel("환영합니다.");

				fail.setFont(new Font("Malgun Gothic", Font.BOLD, 20));
				success.setFont(new Font("Malgun Gothic", Font.BOLD, 20));

				if (registerSuccess == 1) {
					JOptionPane.showMessageDialog(null, fail, "등록 실패",
							JOptionPane.WARNING_MESSAGE);
				} else if (registerSuccess == 0) {
					JOptionPane.showMessageDialog(null, success, "등록 성공",
							JOptionPane.DEFAULT_OPTION);
				}
			} catch (Exception e3) {

			}

			dispose();
		} else if (msg.equals("일반 사용자 등록")) {

			NormalUser EU;

			JTextField id = new JTextField("id");
			JTextField password = new JTextField();
			JTextField name = new JTextField();
			JTextField email = new JTextField();
			JTextField contact = new JTextField();

			JLabel idl = new JLabel("아이디");
			JLabel passwordl = new JLabel("패스워드");
			JLabel namel = new JLabel("이름");
			JLabel emaill = new JLabel("이메일");
			JLabel contactl = new JLabel("연락처");

			idl.setFont(new Font("Malgun Gothic", Font.BOLD, 20));
			passwordl.setFont(new Font("Malgun Gothic", Font.BOLD, 20));
			namel.setFont(new Font("Malgun Gothic", Font.BOLD, 20));
			emaill.setFont(new Font("Malgun Gothic", Font.BOLD, 20));
			contactl.setFont(new Font("Malgun Gothic", Font.BOLD, 20));

			Object[] message = { idl, id, passwordl, password, namel, name,
					emaill, email, contactl, contact };

			JOptionPane ask = new JOptionPane(message,
					JOptionPane.QUESTION_MESSAGE, JOptionPane.OK_CANCEL_OPTION);
			ask.setFont(new Font("Malgun Gothic", Font.BOLD, 20));
			ask.createDialog(null, "기업 사용자 회원등록").setVisible(true);

			System.out.println(ask.getValue() + ">>");

			try {

				if ((Integer) ask.getValue() == 2)
					return;

				EU = new NormalUser(id.getText(), password.getText(),
						name.getText(), email.getText(), contact.getText());

				System.out.println(EU.getId() + ", " + EU.getPassword());

				int registerSuccess = register(EU);

				JLabel fail = new JLabel("아이디가 중복되었습니다.");
				JLabel success = new JLabel("환영합니다.");

				fail.setFont(new Font("Malgun Gothic", Font.BOLD, 20));
				success.setFont(new Font("Malgun Gothic", Font.BOLD, 20));

				if (registerSuccess == 1) {
					JOptionPane.showMessageDialog(null, fail, "등록 실패",
							JOptionPane.WARNING_MESSAGE);
				} else if (registerSuccess == 0) {
					JOptionPane.showMessageDialog(null, success, "등록 성공",
							JOptionPane.DEFAULT_OPTION);
				}
			} catch (Exception e2) {

			}

			dispose();
		}
	}

	private int register(EnterpriseUser EU) {

		UserClient UC = ClientConsole.getClient();

		UC.registerEnterpriseUser(EU);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String temp = UC.getMessageFromServer();
		System.out.println(temp);
		String message = "";

		try {
			message = temp.substring(0, 3);
		} catch (Exception e) {
		}

		if (message.equals("%%1"))
			return 1;
		else if (message.equals("%%0"))
			return 0;

		return -1;

	}

	private int register(NormalUser EU) {

		UserClient UC = ClientConsole.getClient();

		UC.registerNormalUser(EU);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String temp = UC.getMessageFromServer();
		System.out.println(temp);
		String message = "";

		try {
			message = temp.substring(0, 3);
		} catch (Exception e) {
		}

		if (message.equals("%%1"))
			return 1;
		else if (message.equals("%%0"))
			return 0;

		return -1;

	}

}