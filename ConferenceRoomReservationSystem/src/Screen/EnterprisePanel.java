package Screen;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class EnterprisePanel extends JPanel implements ActionListener {

	public static EnterprisePanel EPP = new EnterprisePanel();

	public static EnterprisePanel getEnterprisePanel() {
		return EPP;
	}

	private JPanel buttonPanel = new JPanel();
	private JPanel viewPanel = new JPanel();
	private JButton registerRoom = new JButton("ȸ�ǽ� ���");
	// private JButton editRoom = new JButton("ȸ�ǽ� ����");
	private JButton deleteRoom = new JButton("ȸ�ǽ� ����");
	// private JButton checkRoomBooker = new JButton("������ ��ȸ");
	private JButton checkRegisteredRoom = new JButton("ȸ�ǽ� ��ȸ");
	private RegisteredRoomListTablePanel r;

	public EnterprisePanel() {
	}

	public void make() {
		EPP.setBackground(Color.WHITE);
		EPP.setLayout(new BorderLayout());

		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.PAGE_AXIS));
		buttonPanel.add(registerRoom);
		registerRoom.setFont(new Font("Malgun Gothic", Font.TRUETYPE_FONT, 30));
		registerRoom.addActionListener(this);
		// buttonPanel.add(editRoom);
		// editRoom.setFont(new Font("Gulim", Font.PLAIN, 30));
		// editRoom.addActionListener(this);
		// buttonPanel.add(deleteRoom);
		// deleteRoom.setFont(new Font("Malgun Gothic", Font.TRUETYPE_FONT,
		// 30));
		// deleteRoom.addActionListener(this);
		// buttonPanel.add(checkRoomBooker);
		// checkRoomBooker.setFont(new Font("Malgun Gothic", Font.TRUETYPE_FONT,
		// 30));
		// checkRoomBooker.addActionListener(this);
		buttonPanel.add(checkRegisteredRoom);
		checkRegisteredRoom.setFont(new Font("Malgun Gothic",
				Font.TRUETYPE_FONT, 30));
		checkRegisteredRoom.addActionListener(this);

		add(buttonPanel, BorderLayout.WEST);

		viewPanel.setLayout(new CardLayout());
		viewPanel.setBackground(Color.WHITE);
		RegisterRoomPanel.getRegisterRoomPanel().makeRegisterRoomPanel();

		JLabel hint = new JLabel(" >> ���ϴ� �۾��� �������ּ���.");
		hint.setFont(new Font("Malgun Gothic", Font.TRUETYPE_FONT, 30));
		viewPanel.add(hint, "Default");

		r = RegisteredRoomListTablePanel.getRegisteredRoomListTablePanel();
		viewPanel.add(RegisterRoomPanel.getRegisterRoomPanel(), "registerRoom");
		viewPanel.add(r, "registeredRoomList");

		add(viewPanel, BorderLayout.CENTER);

	}

	@Override
	public void actionPerformed(ActionEvent e) { // ��ư��ɱ����ʿ�

		JButton b = (JButton) e.getSource();
		CardLayout cl = (CardLayout) viewPanel.getLayout();

		if (b.getText().equals("ȸ�ǽ� ���")) {
			cl.show(viewPanel, "registerRoom");
			// } else if (b.getText().equals("ȸ�ǽ� ����")) {

		} else if (b.getText().equals("ȸ�ǽ� ��ȸ")) {

			viewPanel.remove(r);

			r = RegisteredRoomListTablePanel.getRegisteredRoomListTablePanel();
			viewPanel.add(r, "registeredRoomList");
			cl = (CardLayout) viewPanel.getLayout();
			cl.show(viewPanel, "registeredRoomList");
		}

		cl.invalidateLayout(viewPanel);

	}

}