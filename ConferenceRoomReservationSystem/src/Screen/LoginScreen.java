package Screen;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import Client.UserClient;
import Console.ClientConsole;

public class LoginScreen extends JPanel implements ActionListener {

	// Constructor
	public LoginScreen() {
	}

	private JPanel LS1 = new JPanel();
	private static LoginScreen LS = new LoginScreen();

	public static LoginScreen getLoginScreen() {
		return LS;
	}

	// GUI
	private JPanel buttonPanel = new JPanel();
	private JPanel titlePanel = new JPanel();
	private JButton registerButton = new JButton("회원등록");
	private JLabel jl = new JLabel("지금 등록하시면 100만원 상당의 치킨이 공짜!!");
	private JLabel title = new JLabel("회의실 예약 시스템 [30일 체험판]");
	private JPanel loginPanel = new JPanel();
	private JPanel loginPanel2 = new JPanel();
	private JTextField jtp1 = new JTextField("아이디");
	private JPasswordField jtp2 = new JPasswordField("password");
	private JButton loginButton = new JButton("로그인");
	private JTextArea ServerMessage = new JTextArea(
			">> 아이디와 패스워드를 입력하세염 <<\n\n");
	private JPanel textPanel = new JPanel();
	private CardLayout layout = new CardLayout();

	private EnterprisePanel EP;
	private NormalPanel NP;

	public void makeTitleScreen() {

		LS.setLayout(layout);
		LS.add(LS1);

		LS1.setBackground(Color.WHITE);
		LS1.setLayout(new BorderLayout());
		textPanel.add(jl);
		jl.setFont(new Font("Malgun Gothic", Font.TRUETYPE_FONT, 40));
		title.setFont(new Font("Malgun Gothic", Font.TRUETYPE_FONT, 50));

		loginPanel.setBackground(Color.WHITE);
		loginPanel.add(loginPanel2);

		loginPanel2.setLayout(new BoxLayout(loginPanel2, BoxLayout.PAGE_AXIS));
		// loginPanel2.setLayout(new FlowLayout());
		jtp1.addFocusListener(new java.awt.event.FocusAdapter() {
			public void focusGained(java.awt.event.FocusEvent evt) {
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						jtp1.selectAll();
					}
				});
			}
		});

		jtp2.addFocusListener(new java.awt.event.FocusAdapter() {
			public void focusGained(java.awt.event.FocusEvent evt) {
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						jtp2.selectAll();
					}
				});
			}
		});

		loginPanel2.add(jtp1);
		loginPanel2.add(jtp2);
		buttonPanel.add(loginButton);
		loginPanel2.add(buttonPanel);
		loginPanel2.add(ServerMessage);
		ServerMessage.setEditable(false);
		ServerMessage.setSize(getSize());

		ServerMessage.setFont(new Font("Malgun Gothic", Font.TRUETYPE_FONT, 30));

		titlePanel.add(title);
		LS1.add(BorderLayout.NORTH, titlePanel);
		LS1.add(BorderLayout.SOUTH, textPanel);
		LS1.add(BorderLayout.CENTER, loginPanel);

		buttonPanel.add(registerButton);

		loginButton.addActionListener(this);
		registerButton.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String msg = e.getActionCommand();
		String id;
		String passwd;
		int loginSuccess = 0;

		if (msg.equals("로그인")) {
			ServerMessage.setText(">> 아이디와 패스워드를 입력하세염 <<\n>> 로그인중입니다.\n");

			id = jtp1.getText();
			passwd = jtp2.getText();

			// ServerMessage.append(id + ", " + passwd + "\n");

			loginSuccess = login(id, passwd);

			System.out.println(loginSuccess);

			if (loginSuccess == 0) {
				// 기업사용자 로그인 성공
				ServerMessage.append(">> 기업 로그인 성공, 1초뒤 접속합니다.");

				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				EP = EP.getEnterprisePanel();
				EP.make();
				LS.add(EP);

				layout.next(LS);

			} else if (loginSuccess == 1) {
				// 사용자 회원정보 없음
				ServerMessage.append(">> 로그인 실패, 회원정보 없음!!!");
			} else if (loginSuccess == 2) {
				// 기업사용자 비밀번호 틀림
				ServerMessage.append(">> 기업 로그인 실패, 패스워드 틀림!!!");
			} else if (loginSuccess == 3) {
				// 일반사용자 로그인 성공
				ServerMessage.append(">> 일반 로그인 성공, 1초뒤 접속합니다.");

				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				NP = NP.getNormalPanel();
				NP.make();
				LS.add(NP);

				layout.next(LS);
			} else if (loginSuccess == 4) {
				// 일반사용자 비밀번호 틀림
				ServerMessage.append(">> 일반 로그인 실패, 패스워드 틀림!!!");
			}
		} else if (msg.equals("회원등록")) {
			
			RegisterScreen RS = RegisterScreen.getRegisterScreen();
			RS.chooseType();

		}
	}

	private int login(String id, String passwd) {

		UserClient UC = ClientConsole.getClient();
		UC.login(id, passwd);

		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String temp = UC.getMessageFromServer();
		String message = temp.substring(0, 3);

		try {

			ClientConsole.setKey(Integer.parseInt(temp.substring(3)));
			System.out.println(ClientConsole.getKey());

		} catch (Exception e4) {

			System.err.println(e4.getMessage());

		}

		if (message.equals("$$1"))
			return 1;
		else if (message.equals("$$2"))
			return 2;
		else if (message.equals("$$0")) {

			return 0;
		} else if (message.equals("$$3"))
			return 3;
		else if (message.equals("$$4"))
			return 4;

		return -1;
	}

}