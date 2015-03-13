package Screen;

import java.awt.event.MouseEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import java.util.ArrayList;

import javax.swing.table.*;

import Room.*;
import Client.UserClient;
import Console.*;

public class RegisteredRoomListTablePanel extends JPanel implements
		ActionListener {

	public static RegisteredRoomListTablePanel RRLTP;

	public static RegisteredRoomListTablePanel getRegisteredRoomListTablePanel() {

		RRLTP = null;
		RRLTP = new RegisteredRoomListTablePanel();

		return RRLTP;
	}

	public static RegisteredRoomListTablePanel getRegisteredRoomListTablePanel2() {

		return RRLTP;
	}

	private RegisteredRoomListTablePanel() {
		makeRegisteredRoomListTablePanel();
	}

	private String[] colHeads = { "회의실 이름", "최대수용인원", "시", "가격", "시작날짜",
			"마지막날짜" };
	private Object[][] data;
	private JTable table;
	private JScrollPane jsp;
	private int EnterpriseKey;
	private ArrayList<DefaultRoom> registeredRoomList;
	private DefaultTableModel model;
	JPanel tablePanel = new JPanel();
	JPanel buttonPanel = new JPanel();
	JButton deleteButton = new JButton("회의실 삭제");
	JButton searchButton = new JButton("예약자 조회");
	JButton editButton = new JButton("회의실 수정");

	public void makeRegisteredRoomListTablePanel() {

		tablePanel.setLayout(new BorderLayout());

		UserClient UC = ClientConsole.getClient();

		EnterpriseKey = ClientConsole.getKey();
		registeredRoomList = UC.getRegisteredRoomListFromServer();

		setBackground(Color.WHITE);
		setLayout(new BorderLayout());

		model = null;
		model = new DefaultTableModel(colHeads, 0);

		dataTranslation();

		for (int i = 0; i < registeredRoomList.size(); i++) {
			model.addRow(data[i]);
		}

		table = null;
		table = new JTable(model);

		jsp = null;
		jsp = new JScrollPane(table,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		table.setFont(new Font("Gulim", Font.TRUETYPE_FONT, 21));
		table.setRowHeight(35);

		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		tablePanel.add(jsp, BorderLayout.CENTER);
		add(tablePanel, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);

		buttonPanel.add(searchButton);
		buttonPanel.add(editButton);
		buttonPanel.add(deleteButton);

		deleteButton.addActionListener(this);

		JTableHeader header = table.getTableHeader();
		header.setFont(new Font("Gulim", Font.TRUETYPE_FONT, 21));
		header.setPreferredSize(new Dimension(100, 32));
	}

	void dataTranslation() { // data에 변환해서 넣음
		data = new Object[registeredRoomList.size()][6];
		for (int i = 0; i < registeredRoomList.size(); i++) {
			data[i][0] = registeredRoomList.get(i).getName();
			data[i][1] = registeredRoomList.get(i).getMaxNumber();
			data[i][2] = registeredRoomList.get(i).getLocation().getCity();
			data[i][3] = registeredRoomList.get(i).getPrice().getPricePerHalf();
			data[i][4] = registeredRoomList.get(i).getStart();
			data[i][5] = registeredRoomList.get(i).getEnd();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String msg = e.getActionCommand();
		
		if (msg.equals("회의실 삭제")) {
			
			int temp = table.getSelectedRow();
			System.out.println("Selected Room for delete: " + data[temp][0]);
			
			int success = delete((String)data[temp][0]);
			
			if (success == 0) {
				
			} else {
				
			}
		}
	}
	
	private int delete(String name) {
		UserClient UC = ClientConsole.getClient();
		UC.deleteRoom(name);
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String temp = UC.getMessageFromServer();
		String message = temp.substring(0, 3);

		if (message.equals("##1"))
			return 1;
		else if (message.equals("##0"))
			return 0;

		return -1;
		
	}
}
