package normal.showRoominfo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import normal.listBookableRoom.ListBookableRoomModel;
import server.room.Room;
import server.room.reservedDate;

@SuppressWarnings("serial")
public class ShowRoominfoView extends JFrame implements Observer {
	private JPanel showRoominfoPanel = new JPanel();
	private String[] colHeads = { "예약된 날짜" };
	private Object[][] data;
	private JTable table;
	private JScrollPane jsp;
	private DefaultTableModel model;
	private Calendar cal = Calendar.getInstance();

	public ShowRoominfoView() {
		setTitle("회의실 예약 정보");

		this.setLayout(new BorderLayout());
		showRoominfoPanel.setBackground(Color.WHITE);
		showRoominfoPanel.setLayout(new BorderLayout());
		model = new DefaultTableModel(colHeads, 0);

		table = new JTable(model);

		jsp = new JScrollPane(table,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		showRoominfoPanel.add(jsp, BorderLayout.CENTER);

		this.add(showRoominfoPanel, BorderLayout.CENTER);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		
		for (int i = model.getRowCount() - 1; i > -1; i--) {
			model.removeRow(i);
		}

		dataTranslation(((ShowRoominfoModel) arg0).getSelectedRoom()
				.getBookingUserKeyList());

		for (int i = 0; i < ((ShowRoominfoModel) arg0).getSelectedRoom()
				.getBookingUserKeyList().size(); i++) {
			model.addRow(data[i]);
		}
		model.fireTableDataChanged();
		makePopup();

	}

	void dataTranslation(ArrayList<reservedDate> bukl) { // data에 변환해서 넣음
		data = new Object[bukl.size()][6];
		for (int i = 0; i < bukl.size(); i++) {
			cal.setTime(bukl.get(i).getDate());
			int month = cal.get(Calendar.MONTH) + 1;
			data[i][0] = cal.get(Calendar.YEAR) + " - " + month + " - "
					+ cal.get(Calendar.DAY_OF_MONTH);
		}
	}

	public void makePopup() {
		setSize(300, 300);
		setVisible(true);
	}

}