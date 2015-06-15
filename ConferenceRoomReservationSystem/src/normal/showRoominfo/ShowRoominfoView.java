package normal.showRoominfo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import server.room.Room;
import server.room.reservedDate;

@SuppressWarnings("serial")
public class ShowRoominfoView extends JFrame implements Observer {
	private ArrayList<reservedDate> bookingUserKeyList;
	
	private JPanel showRoominfoPanel = new JPanel();
	private String[] colHeads = { "����� ��¥" };
	private Object[][] data;
	private JTable table;
	private JScrollPane jsp;
	private DefaultTableModel model;
	
	public ShowRoominfoView() {
		
	}
	@Override
	public void update(Observable arg0, Object arg1) {
		bookingUserKeyList = ((ShowRoominfoModel)arg0).getSelectedRoom().getBookingUserKeyList();
		makePopup();
	}
	
	void dataTranslation(ArrayList<reservedDate>  bukl) { // data�� ��ȯ�ؼ� ����
		data = new Object[bukl.size()][6];
		for (int i = 0; i < bukl.size(); i++) {
			data[i][0] = bukl.get(i).getDate().getDate();
		}
	}
	
	public void makePopup() {
		setTitle("ȸ�ǽ� ���� ����");
		
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
		
		setSize(300,300);
		setVisible(true);
	}
	
}