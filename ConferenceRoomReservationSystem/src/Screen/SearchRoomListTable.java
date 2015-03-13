package Screen;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import java.util.ArrayList;

import javax.swing.table.*;

import Room.*;
import Client.UserClient;
import Console.*;

public class SearchRoomListTable extends JPanel implements ActionListener {

	public static SearchRoomListTable SRLT;

	public static SearchRoomListTable getSearchRoomListTablePanel() {
		SRLT = null;
		SRLT = new SearchRoomListTable();

		return SRLT;
	}

	public static SearchRoomListTable getSearchRoomListTablePanel2() {
		return SRLT;
	}

	private SearchRoomListTable() {
		makeSearchRoomListTablePanel();
	}

	private String[] colHeads = { "회의실 이름", "최대수용인원", "시", "가격", "시작날짜",
			"마지막날짜" };
	private Object[][] data;
	private JTable table;
	private JScrollPane jsp;
	private ArrayList<DefaultRoom> searchRoomList;
	private DefaultTableModel model;

	private JPanel buttonPanel = new JPanel();
	private JButton check = new JButton("예약상황보기");
	private JButton book = new JButton("예약하기");

	private int noramlKey;
	private ArrayList<DefaultRoom> list = new ArrayList<DefaultRoom>();

	private static int num;
	private static String city;
	private static int price;

	public void makeSearchPanel() {
		JTextField jnum = new JTextField();
		JTextField jcity = new JTextField();
		JTextField jprice = new JTextField();

		JLabel num1 = new JLabel("수용인원");
		JLabel num2 = new JLabel("1~200의 숫자를 입력 해주세요.");
		JLabel city1 = new JLabel("시");
		JLabel city2 = new JLabel("5자~20자까지 입력 해주세요.");
		JLabel price1 = new JLabel("대여비용");
		JLabel price2 = new JLabel("10000원 이상 입력 해주세요.");
		JLabel info1 = new JLabel("검색 조건을 빈칸으로 할 경우, ");
		JLabel info2 = new JLabel("해당 검색 조건의 모든 범위를 보여줍니다.");

		num1.setFont(new Font("Malgun Gothic", Font.BOLD, 20));
		num2.setFont(new Font("Malgun Gothic", Font.BOLD, 10));
		city1.setFont(new Font("Malgun Gothic", Font.BOLD, 20));
		city2.setFont(new Font("Malgun Gothic", Font.BOLD, 10));
		price1.setFont(new Font("Malgun Gothic", Font.BOLD, 20));
		price2.setFont(new Font("Malgun Gothic", Font.BOLD, 10));
		info1.setFont(new Font("Malgun Gothic", Font.BOLD, 12));
		info2.setFont(new Font("Malgun Gothic", Font.BOLD, 12));

		Object[] message = { num1, num2, jnum, city1, city2, jcity, price1,
				price2, jprice, info1, info2 };

		JOptionPane ask = new JOptionPane(message);
		ask.setFont(new Font("Malgun Gothic", Font.BOLD, 20));
		ask.createDialog(null, "회의실 검색").setVisible(true);

		try {
			if (jnum.getText().equals("")) {
				setNum(1);
			} else {
				num = Integer.parseInt(jnum.getText());
				setNum(num);
			}

			if ((num < 1) || (num > 200)) {
				makeSearchPanel();
			}
		} catch (Exception e2) {
			makeSearchPanel();
		}

		if (jcity.getText().equals("") || jcity.getText() == null) {
			setCity("");
		} else {
			if ((jcity.getText().length() < 5)
					|| (jcity.getText().length() > 20)) {
				makeSearchPanel();
			} else {
				setCity(jcity.getText());
			}
		}

		try {
			if (jprice.getText().equals("")) {
				setPrice(999999999);
			} else {
				price = Integer.parseInt(jprice.getText());
				setPrice(price);
			}

			if (price < 10000) {
				makeSearchPanel();
			}
		} catch (Exception e2) {
			makeSearchPanel();
		}
	}

	public void makeSearchRoomListTablePanel() {
		
		System.out.println("Start!!");

		UserClient UC = ClientConsole.getClient();

		noramlKey = ClientConsole.getKey();

		searchRoomList = UC.getRegisteredRoomListFromServer();

		for (int i = 0; i < searchRoomList.size(); i++) {
			Date cur = new Date();
			if (cur.compareDate(cur, searchRoomList.get(i).getEnd())) {
				list.add(searchRoomList.get(i));
			}
		}
		System.out.println(list.size());
		System.out.println(num);
		System.out.println(price);
		System.out.println("city" + city);
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getMaxNumber() < num
					|| list.get(i).getPrice().getPricePerHalf() > price) {
				list.remove(i);
				i--;
			}
		}
		if (city != null && !city.equals("")) {
			for (int i = 0; i < list.size(); i++) {
				if (!list.get(i).getLocation().getCity().equals(city)) {
					list.remove(i);
					i--;
				}
			}
		}

		if (list.size() == 0 && (city != null && !city.equals(""))) {
			JOptionPane.showMessageDialog(null, "검색 조건에 맞는 회의실이 없습니다.\n"
					+ "차선책으로 검색 조건 중 '수용인원'과 '가격'에 맞는 결과만 보여드리겠습니다.",
					"회의실 검색 결과", JOptionPane.PLAIN_MESSAGE);
			for (int i = 0; i < searchRoomList.size(); i++) {
				Date cur = new Date();
				if (cur.compareDate(cur, searchRoomList.get(i).getEnd())) {
					list.add(searchRoomList.get(i));
				}
			}
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getMaxNumber() < num
						|| list.get(i).getPrice().getPricePerHalf() > price) {
					list.remove(i);
					i--;
				}
			}
		}

		// setBackground(Color.WHITE);
		setLayout(new BorderLayout());

		model = null;
		model = new DefaultTableModel(colHeads, 0);

		dataTranslation(list);

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
		buttonPanel.add(check);
		check.setFont(new Font("Malgun Gothic", Font.PLAIN, 30));
		check.addActionListener(this);

		buttonPanel.add(book);
		book.setFont(new Font("Malgun Gothic", Font.PLAIN, 30));
		book.addActionListener(this);
		table.setFont(new Font("Gulim", Font.TRUETYPE_FONT, 21));
		table.setRowHeight(35);
		add(buttonPanel, BorderLayout.SOUTH);

		JTableHeader header = table.getTableHeader();
		header.setFont(new Font("Gulim", Font.TRUETYPE_FONT, 21));
		header.setPreferredSize(new Dimension(100, 32));
	}

	void dataTranslation(ArrayList<DefaultRoom> detail) { // data에 변환해서 넣음
		data = new Object[detail.size()][6];
		for (int i = 0; i < detail.size(); i++) {
			data[i][0] = detail.get(i).getName();
			data[i][1] = detail.get(i).getMaxNumber();
			data[i][2] = detail.get(i).getLocation().getCity();
			data[i][3] = detail.get(i).getPrice().getPricePerHalf();
			data[i][4] = detail.get(i).getStart();
			data[i][5] = detail.get(i).getEnd();
		}
	}

	void detailTabel(ArrayList<DefaultRoom> detail) {
		for (int i = 0; i < model.getRowCount(); i++) {
			model.removeRow(i);
			i--;
		}
		dataTranslation(detail);
		for (int i = 0; i < detail.size(); i++) {
			model.addRow(data[i]);
		}
		table = new JTable(model);
		jsp.add(table);
	}

	@Override
	public void actionPerformed(ActionEvent e) { // 버튼기능구현필요

		JButton b = (JButton) e.getSource();

		try {
			int select = table.getSelectedRow();

			String name = String.valueOf(table.getValueAt(select, 0));
			String maxnum = String.valueOf(table.getValueAt(select, 1));
			String city = String.valueOf(table.getValueAt(select, 2));
			String price = String.valueOf(table.getValueAt(select, 3));

			Date cur = new Date();
			ArrayList<Date> seletionDate = new ArrayList<Date>(); // 예약가능한 날짜
			ArrayList<Date> seletionDate2 = new ArrayList<Date>(); // 이미 예약된 날짜
			for (int i = 0; i < searchRoomList.get(select).getPeriod().size(); i++) {
				if (!searchRoomList.get(select).getPeriod().get(i).getBooked()
						&& cur.compareDate(cur, searchRoomList.get(select)
								.getPeriod().get(i)))
					seletionDate.add(searchRoomList.get(select).getPeriod()
							.get(i));
				else {
					if (cur.compareDate(cur, searchRoomList.get(select)
							.getPeriod().get(i)))
						seletionDate2.add(searchRoomList.get(select)
								.getPeriod().get(i));
				}
			}
			String head = "회의실이름: " + name + " | " + "최대수용인원: " + maxnum
					+ " | " + "시: " + city + " | " + "가격: " + price + "\n\n";

			if (b.getText().equals("예약하기")) {
				JDialog.setDefaultLookAndFeelDecorated(true);

				Object[] seletionValues = new Object[seletionDate.size()];
				for (int i = 0; i < seletionDate.size(); i++) {
					seletionValues[i] = seletionDate.get(i);
				}
				Object selection = JOptionPane.showInputDialog(null,
						"예약날짜를 선택해주세요.", "예약하기", JOptionPane.PLAIN_MESSAGE,
						null, seletionValues, null);

				if (selection != null) {
					String d = String.valueOf(selection);
					int year = Integer.valueOf(d.substring(0, d.indexOf('/')));
					int month = Integer.valueOf(d.substring(d.indexOf('/') + 1,
							d.lastIndexOf('/')));
					int day = Integer.valueOf(d.substring(
							d.lastIndexOf('/') + 1, d.length()));
					Date date = new Date(year, month, day);

					// 예약하기
					ClientConsole.getClient().bookRoom(name, date);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e3) {
						// TODO Auto-generated catch block
						e3.printStackTrace();
					}
				}

			} else if (b.getText().equals("예약상황보기")) {
				String msg = "";
				if (seletionDate.size() <= 30) {
					msg += "예약가능한 날짜: \n";
					for (int i = 0; i < seletionDate.size(); i++) {
						msg += seletionDate.get(i) + "    ";
						if (i % 5 == 4)
							msg += "\n";
					}
				} else {
					msg += "이미 예약된 날짜: \n";
					if (seletionDate2.size() == 0)
						msg += cur + " 이후로 예약한 사용자는 없습니다.\n";
					else {
						for (int i = 0; i < seletionDate2.size(); i++) {
							msg += seletionDate2.get(i) + "    ";
							if (i % 5 == 4)
								msg += "\n";
						}
					}
				}

				JOptionPane.showMessageDialog(null, head + msg, "예약상황보기",
						JOptionPane.PLAIN_MESSAGE);
			}

		} catch (Exception e2) {
			// TODO: handle exception
		}

	}

	public void setNum(int n) {
		num = n;
	}

	public int getNum() {
		return num;
	}

	public void setCity(String c) {
		city = c;
	}

	public String getCity() {
		return city;
	}

	public void setPrice(int p) {
		price = p;
	}

	public int getPrice() {
		return price;
	}

}
