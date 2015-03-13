package Screen;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;

import Client.UserClient;
import Console.ClientConsole;

public class NormalPanel extends JPanel implements ActionListener {

	public static NormalPanel NMP = new NormalPanel();

	public static NormalPanel getNormalPanel() {
		return NMP;
	}

	private JPanel buttonPanel = new JPanel();
	private JPanel viewPanel = new JPanel();
	private JButton searchRoom = new JButton("ȸ�ǽ� �˻�");
	private JButton checkBookedRoom = new JButton("����� ��ȸ");
	
	private SearchRoomListTable search;
	private BookedRoomListTable book;

	public NormalPanel() {
	}

	public void make() {
		NMP.setBackground(Color.WHITE);
		NMP.setLayout(new BorderLayout());

		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.PAGE_AXIS));
		
		buttonPanel.add(searchRoom);
		searchRoom.setFont(new Font("Malgun Gothic", Font.PLAIN, 30));
		searchRoom.addActionListener(this);
		buttonPanel.add(checkBookedRoom);
		checkBookedRoom.setFont(new Font("Malgun Gothic", Font.PLAIN, 30));
		checkBookedRoom.addActionListener(this);

		add(buttonPanel, BorderLayout.WEST);

		viewPanel.setLayout(new CardLayout());
		viewPanel.setBackground(Color.WHITE);
		
		JLabel hint = new JLabel(" >> ���ϴ� �۾��� �������ּ���.");
		hint.setFont(new Font("Malgun Gothic", Font.PLAIN, 30));
		viewPanel.add(hint, "Default");
		
		search = SearchRoomListTable.getSearchRoomListTablePanel();
		
		viewPanel.add(search, "searchRoomList");
		
		
		book = BookedRoomListTable.getBookedRoomListTablePanel();
		viewPanel.add(book, "bookRoomList");

		add(viewPanel, BorderLayout.CENTER);

	}

	@Override
	public void actionPerformed(ActionEvent e) { // ��ư��ɱ����ʿ�

		JButton b = (JButton) e.getSource();
		CardLayout cl = (CardLayout) viewPanel.getLayout();

		if (b.getText().equals("ȸ�ǽ� �˻�")) {
			
			
			
			viewPanel.remove(search);
			search.makeSearchPanel();
			
			String str = "@@search";
			try {
				ClientConsole.getClient().sendToServer(str);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			
			search = SearchRoomListTable.getSearchRoomListTablePanel();			
			viewPanel.add(search,"searchRoomList2");
			cl = (CardLayout) viewPanel.getLayout();
			cl.show(viewPanel, "searchRoomList2");
			
		} else if(b.getText().equals("����� ��ȸ")){
			viewPanel.remove(b);
			book = BookedRoomListTable.getBookedRoomListTablePanel();
			viewPanel.add(book,"bookRoomList");
			cl = (CardLayout) viewPanel.getLayout();
			cl.show(viewPanel, "bookRoomList");
			
		}

		cl.invalidateLayout(viewPanel);

	}

}