package enterprise.listRoom;

import java.awt.BorderLayout;
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
public class listRoomView extends JPanel implements Observer {

	private String[] colHeads = { "ȸ�ǽ� �̸�", "��", "���ּ�", "�ִ�����ο�", "����", "�δ�ü�" };
	private Object[][] data;
	private JTable table;
	private JScrollPane jsp;
	private DefaultTableModel model;
	private JButton askButton = new JButton("��ȸ�ϱ�");

	public listRoomView() {
		setLayout(new BorderLayout());

		model = new DefaultTableModel(colHeads, 0);

		table = new JTable(model);

		jsp = new JScrollPane(table,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		add(jsp, BorderLayout.CENTER);
		add(askButton, BorderLayout.SOUTH);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		for (int i = model.getRowCount() - 1; i > -1; i--) {
			model.removeRow(i);
		}

		dataTranslation(((listRoomModel) arg0).getRL().getList());

		for (int i = 0; i < ((listRoomModel) arg0).getRL().getList().size(); i++) {
			model.addRow(data[i]);
		}
		model.fireTableDataChanged();

	}

	public void setListRoomListener(ActionListener listener) {
		askButton.addActionListener(listener);
	}

	void dataTranslation(ArrayList<Room> rl) { // data�� ��ȯ�ؼ� ����
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