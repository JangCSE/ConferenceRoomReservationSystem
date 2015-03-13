package Screen;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import Client.UserClient;
import Console.ClientConsole;
import Room.Date;
import Room.DefaultRoom;

public class BookedRoomListTable extends JPanel implements ActionListener {

	public static BookedRoomListTable BRLT;

	public static BookedRoomListTable getBookedRoomListTablePanel() {
		BRLT = null;
		BRLT = new BookedRoomListTable();

		return BRLT;
	}

	public static BookedRoomListTable getBookedRoomListTablePanel2() {
		return BRLT;
	}

	private BookedRoomListTable() {
		makeBookedRoomListTablePanel();
	}

	private String[] colHeads = { "회의실 이름", "최대수용인원", "시", "가격", "예약날짜" };
	private Object[][] data;
	private JTable table;
	private JScrollPane jsp;
	private ArrayList<DefaultRoom> bookRoomList;
	private ArrayList<DefaultRoom> list = new ArrayList<DefaultRoom>();
	private ArrayList<Date> date = new ArrayList<Date>();
	private DefaultTableModel model;

	private JPanel buttonPanel = new JPanel();
	private JButton cancel = new JButton("예약취소");

	private int normalKey;

	public void makeBookedRoomListTablePanel() {

		UserClient UC = ClientConsole.getClient();

		normalKey = ClientConsole.getKey();

		bookRoomList = UC.getRegisteredRoomListFromServer();

		for (int i = 0; i < bookRoomList.size(); i++) {
			for (int j = 0; j < bookRoomList.get(i).getPeriod().size(); j++) {
				if (bookRoomList.get(i).getPeriod().get(j).getNormalKey() == normalKey
						&& bookRoomList.get(i).getPeriod().get(j).getBooked()) {
					list.add(bookRoomList.get(i));
					date.add(bookRoomList.get(i).getPeriod().get(j));
				}
			}
		}

		setLayout(new BorderLayout());

		model = null;
		model = new DefaultTableModel(colHeads, 0);

		dataTranslation(list, date);

		for (int i = 0; i < list.size(); i++) {
			model.addRow(data[i]);
		}

		table = null;
		table = new JTable(model);

		jsp = null;
		jsp = new JScrollPane(table,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		table.setFont(new Font("Malgun Gothic", Font.PLAIN, 20));

		add(jsp, BorderLayout.CENTER);

		// buttonPanel.setLayout(new BoxLayout(buttonPanel,
		// BoxLayout.LINE_AXIS));
		buttonPanel.add(cancel);
		cancel.setFont(new Font("Malgun Gothic", Font.PLAIN, 30));
		cancel.addActionListener(this);

		add(buttonPanel, BorderLayout.SOUTH);

		table.setFont(new Font("Gulim", Font.TRUETYPE_FONT, 21));
		table.setRowHeight(35);
		JTableHeader header = table.getTableHeader();
		header.setFont(new Font("Gulim", Font.TRUETYPE_FONT, 21));
		header.setPreferredSize(new Dimension(100, 32));

	}

	void dataTranslation(ArrayList<DefaultRoom> detail, ArrayList<Date> date) { // data에
																				// 변환해서
																				// 넣음
		data = new Object[detail.size()][5];
		for (int i = 0; i < detail.size(); i++) {
			data[i][0] = detail.get(i).getName();
			data[i][1] = detail.get(i).getMaxNumber();
			data[i][2] = detail.get(i).getLocation().getCity();
			data[i][3] = detail.get(i).getPrice().getPricePerHalf();
			data[i][4] = date.get(i);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton b = (JButton) e.getSource();
		if (b.getText().equals("예약취소")) {

		}
	}

}
