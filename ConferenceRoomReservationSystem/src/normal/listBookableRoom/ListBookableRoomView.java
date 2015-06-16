package normal.listBookableRoom;

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

@SuppressWarnings("serial")
public class ListBookableRoomView extends JPanel implements Observer {

	private JPanel listBookableRoomPanel = new JPanel();
	private JPanel buttonPanel = new JPanel();
	private String[] colHeads = { "회의실 이름", "시", "상세주소", "최대수용인원", "가격", "부대시설" };
	private Object[][] data;
	private JTable table;
	private JScrollPane jsp;
	private DefaultTableModel model;
	private JButton askButton = new JButton("조회하기");
	private JButton bookButton = new JButton("예약하기");
	private JButton infoButton = new JButton("예약정보보기");
	private JButton downButton = new JButton("텍스트파일로 저장");

	public ListBookableRoomView() {
		this.setLayout(new BorderLayout());
		listBookableRoomPanel.setBackground(Color.WHITE);
		listBookableRoomPanel.setLayout(new BorderLayout());
		model = new DefaultTableModel(colHeads, 0);

		table = new JTable(model);

		jsp = new JScrollPane(table,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		listBookableRoomPanel.add(jsp, BorderLayout.CENTER);

		buttonPanel.setLayout(new GridBagLayout());
		buttonPanel.setBackground(Color.WHITE);
		buttonPanel.add(askButton);
		buttonPanel.add(bookButton);
		buttonPanel.add(infoButton);
		buttonPanel.add(downButton);

		listBookableRoomPanel.add(buttonPanel, BorderLayout.SOUTH);

		this.add(listBookableRoomPanel, BorderLayout.CENTER);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		for (int i = model.getRowCount() - 1; i > -1; i--) {
			model.removeRow(i);
		}

		dataTranslation(((ListBookableRoomModel) arg0).getMyList().getList());

		for (int i = 0; i < ((ListBookableRoomModel) arg0).getMyList()
				.getList().size(); i++) {
			model.addRow(data[i]);
		}
		model.fireTableDataChanged();

	}

	public void setListBookalbeRoomListener(ActionListener listener) {
		askButton.addActionListener(listener);
		bookButton.addActionListener(listener);
	}

	public void setShowRoominfoListener(ActionListener listener) {
		infoButton.addActionListener(listener);
		downButton.addActionListener(listener);
	}

	void dataTranslation(ArrayList<Room> rl) { // data에 변환해서 넣음
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

}