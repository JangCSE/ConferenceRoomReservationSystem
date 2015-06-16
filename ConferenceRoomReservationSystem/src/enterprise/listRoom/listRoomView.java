package enterprise.listRoom;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import server.room.Room;

/*
 * This is MVC View
 */
@SuppressWarnings("serial")
public class listRoomView extends JPanel implements Observer {

	private String[] colHeads = { "회의실 이름", "시", "상세주소", "최대수용인원", "가격", "부대시설" };
	private Object[][] data;
	private JTable table;
	private JScrollPane jsp;
	private DefaultTableModel model;
	private JPanel buttonPanel = new JPanel();
	private JButton askButton = new JButton("조회하기");
	private JButton logoutButton = new JButton("로그아웃");
	private JButton deleteRoom = new JButton("삭제하기");
	private JButton fileButton = new JButton("텍스트 파일로 저장");

	/*
	 * Constructor
	 */
	public listRoomView() {
		setLayout(new BorderLayout());

		model = new DefaultTableModel(colHeads, 0);

		table = new JTable(model);

		jsp = new JScrollPane(table,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		add(jsp, BorderLayout.CENTER);
		buttonPanel.setLayout(new GridBagLayout());
		buttonPanel.setBackground(Color.WHITE);
		buttonPanel.add(askButton);
		buttonPanel.add(deleteRoom);
		buttonPanel.add(logoutButton);
		buttonPanel.add(fileButton);
		add(buttonPanel, BorderLayout.SOUTH);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 * MVC Observer pattern update
	 */
	@Override
	public void update(Observable arg0, Object arg1) {
		for (int i = model.getRowCount() - 1; i > -1; i--) {
			model.removeRow(i);
		}

		dataTranslation(((listRoomModel) arg0).getRL().getList());

		int size = ((listRoomModel) arg0).getRL().getList().size();

		for (int i = 0; i < size; i++) {
			model.addRow(data[i]);
		}
		model.fireTableDataChanged();

	}

	/*
	 * Add Listener
	 */
	public void setListRoomListener(ActionListener listener) {
		askButton.addActionListener(listener);
		logoutButton.addActionListener(listener);
		fileButton.addActionListener(listener);
		deleteRoom.addActionListener(listener);
	}

	/*
	 * This function makes ArrayList of room to JTable data
	 */
	void dataTranslation(ArrayList<Room> rl) {
		data = new Object[rl.size()][6];
		for (int i = 0; i < rl.size(); i++) {
			data[i][0] = rl.get(i).getName();
			data[i][1] = rl.get(i).getCity();
			data[i][2] = rl.get(i).getDetailLocation();
			data[i][3] = rl.get(i).getMaxNumber();
			data[i][4] = rl.get(i).getCost();
			data[i][5] = rl.get(i).getDetail();
		}
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

}