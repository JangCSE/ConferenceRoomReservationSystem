package normal;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
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

import listRoom.listRoomModel;
import room.Room;

public class ListBookableRoomView extends JPanel implements Observer {

	private JPanel listBookableRoomPanel = new JPanel();
	private JPanel buttonPanel = new JPanel();
	private String[] colHeads = { "ȸ�ǽ� �̸�", "��", "���ּ�", "�ִ�����ο�",  "����", "�δ�ü�" };
	private Object[][] data;
	private JTable table;
	private JScrollPane jsp;
	private DefaultTableModel model;
	private JButton askButton = new JButton("��ȸ�ϱ�");
	private JButton bookButton = new JButton("�����ϱ�");
	private JButton infoButton = new JButton("������������");
	private final Insets insets = new Insets(10, 10, 10, 10);
	
	
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
		buttonPanel.add(askButton);
		buttonPanel.add(bookButton);
		buttonPanel.add(infoButton);
		
		listBookableRoomPanel.add(buttonPanel, BorderLayout.SOUTH);

		this.add(listBookableRoomPanel, BorderLayout.CENTER);
	}
	
	
	@Override
	public void update(Observable arg0, Object arg1) {
		for (int i = 0; i < model.getRowCount(); i++) {
			model.removeRow(i);
		}
		
		dataTranslation(((listRoomModel)arg0).getRL().getList());
		
		for (int i = 0; i < ((listRoomModel)arg0).getRL().getList().size(); i++) {
			model.addRow(data[i]);
		}
		model.fireTableDataChanged();
		
		
		
	}
	
	public void setListBookalbeRoomListener(ActionListener listener) {
		askButton.addActionListener(listener);
		bookButton.addActionListener(listener);
		infoButton.addActionListener(listener);
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


}
