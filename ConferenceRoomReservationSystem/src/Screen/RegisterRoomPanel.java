package Screen;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import Client.UserClient;
import Console.ClientConsole;
import Room.*;

public class RegisterRoomPanel extends JPanel implements ActionListener {

	public static RegisterRoomPanel RRP = new RegisterRoomPanel();

	public static RegisterRoomPanel getRegisterRoomPanel() {
		return RRP;
	}

	private JButton registerRoomButton = new JButton("ȸ�ǽ� ��� �Ϸ�");
	private JButton backButton = new JButton("���");

	private JPanel inputPanel = new JPanel();
	private JPanel buttonPanel = new JPanel();
	private JLabel name = new JLabel("ȸ�ǽ� �̸�");
	private JTextField nameInput = new JTextField("5��~20�ڱ��� �Է� ���ּ���.");
	private JLabel maxNumber = new JLabel("�ִ�����ο�");
	private JTextField maxNumberInput = new JTextField("1~200�� ���ڸ� �Է� ���ּ���.");
	private JLabel city = new JLabel("��");
	private JTextField cityInput = new JTextField("5��~20�ڱ��� �Է� ���ּ���.");
	private JLabel zipcode = new JLabel("�����ȣ");
	private JTextField zipcodeInput = new JTextField("1��~10�ڱ��� �Է� ���ּ���.");
	private JLabel detail = new JLabel("���ּ�");
	private JTextField detailInput = new JTextField("5��~30�ڱ��� �Է� ���ּ���.");
	private JLabel price = new JLabel("�Ϸ� �̿� ����");
	private JTextField priceInput = new JTextField("10000�� �̻� �Է� ���ּ���.");
	
	private JPanel startPanel = new JPanel();
	private JLabel start = new JLabel("���۳�¥");
	private JLabel end = new JLabel("��������¥");
	private JLabel startYear = new JLabel("��");
	private JLabel startMonth = new JLabel("��");
	private JLabel startDay = new JLabel("��");
	private JLabel endYear = new JLabel("��");
	private JLabel endMonth = new JLabel("��");
	private JLabel endDay = new JLabel("��");
	private JTextField startYearInput = new JTextField("");
	private JTextField startMonthInput = new JTextField("");
	private JTextField startDayInput = new JTextField("");
	private JPanel endPanel = new JPanel();
	private JTextField endYearInput = new JTextField("");
	private JTextField endMonthInput = new JTextField("");
	private JTextField endDayInput = new JTextField("");

	public void makeRegisterRoomPanel() {
		RRP.setBackground(Color.WHITE);
		RRP.setLayout(new BorderLayout());

		inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.PAGE_AXIS));
		
		inputPanel.add(name);
		name.setFont(new Font("Malgun Gothic", Font.BOLD, 20));
		nameInput.addFocusListener(new java.awt.event.FocusAdapter() {
		    public void focusGained(java.awt.event.FocusEvent evt) {
		        SwingUtilities.invokeLater(new Runnable() {
		            @Override
		            public void run() {
		            	nameInput.selectAll();
		            }
		        });
		    }
		});
		inputPanel.add(nameInput);
		
		inputPanel.add(maxNumber);
		maxNumber.setFont(new Font("Malgun Gothic", Font.BOLD, 20));
		maxNumberInput.addFocusListener(new java.awt.event.FocusAdapter() {
		    public void focusGained(java.awt.event.FocusEvent evt) {
		        SwingUtilities.invokeLater(new Runnable() {
		            @Override
		            public void run() {
		            	maxNumberInput.selectAll();
		            }
		        });
		    }
		});
		inputPanel.add(maxNumberInput);
		
		inputPanel.add(city);
		city.setFont(new Font("Malgun Gothic", Font.BOLD, 20));
		cityInput.addFocusListener(new java.awt.event.FocusAdapter() {
		    public void focusGained(java.awt.event.FocusEvent evt) {
		        SwingUtilities.invokeLater(new Runnable() {
		            @Override
		            public void run() {
		            	cityInput.selectAll();
		            }
		        });
		    }
		});
		inputPanel.add(cityInput);
		
		inputPanel.add(zipcode);
		zipcode.setFont(new Font("Malgun Gothic", Font.BOLD, 20));
		zipcodeInput.addFocusListener(new java.awt.event.FocusAdapter() {
		    public void focusGained(java.awt.event.FocusEvent evt) {
		        SwingUtilities.invokeLater(new Runnable() {
		            @Override
		            public void run() {
		            	zipcodeInput.selectAll();
		            }
		        });
		    }
		});
		inputPanel.add(zipcodeInput);
		
		inputPanel.add(detail);
		detail.setFont(new Font("Malgun Gothic", Font.BOLD, 20));
		detailInput.addFocusListener(new java.awt.event.FocusAdapter() {
		    public void focusGained(java.awt.event.FocusEvent evt) {
		        SwingUtilities.invokeLater(new Runnable() {
		            @Override
		            public void run() {
		            	detailInput.selectAll();
		            }
		        });
		    }
		});
		inputPanel.add(detailInput);
		
		inputPanel.add(price);
		price.setFont(new Font("Malgun Gothic", Font.BOLD, 20));
		priceInput.addFocusListener(new java.awt.event.FocusAdapter() {
		    public void focusGained(java.awt.event.FocusEvent evt) {
		        SwingUtilities.invokeLater(new Runnable() {
		            @Override
		            public void run() {
		            	priceInput.selectAll();
		            }
		        });
		    }
		});
		inputPanel.add(priceInput);
		// startDate
		inputPanel.add(start);
		start.setFont(new Font("Malgun Gothic", Font.BOLD, 20));
		startPanel.setLayout(new BoxLayout(startPanel, BoxLayout.LINE_AXIS));
		
		inputPanel.add(startPanel);
		
		startYearInput.addFocusListener(new java.awt.event.FocusAdapter() {
		    public void focusGained(java.awt.event.FocusEvent evt) {
		        SwingUtilities.invokeLater(new Runnable() {
		            @Override
		            public void run() {
		            	startYearInput.selectAll();
		            }
		        });
		    }
		});
		startPanel.add(startYearInput);
		startPanel.add(startYear);
		startYear.setFont(new Font("Malgun Gothic", Font.BOLD, 20));
		
		startMonthInput.addFocusListener(new java.awt.event.FocusAdapter() {
		    public void focusGained(java.awt.event.FocusEvent evt) {
		        SwingUtilities.invokeLater(new Runnable() {
		            @Override
		            public void run() {
		            	startMonthInput.selectAll();
		            }
		        });
		    }
		});
		startPanel.add(startMonthInput);
		startPanel.add(startMonth);
		startMonth.setFont(new Font("Malgun Gothic", Font.BOLD, 20));
		
		startDayInput.addFocusListener(new java.awt.event.FocusAdapter() {
		    public void focusGained(java.awt.event.FocusEvent evt) {
		        SwingUtilities.invokeLater(new Runnable() {
		            @Override
		            public void run() {
		            	startDayInput.selectAll();
		            }
		        });
		    }
		});
		startPanel.add(startDayInput);
		startPanel.add(startDay);
		startDay.setFont(new Font("Malgun Gothic", Font.BOLD, 20));
		
		// endDate
		inputPanel.add(end);
		end.setFont(new Font("Malgun Gothic", Font.BOLD, 20));
		endPanel.setLayout(new BoxLayout(endPanel, BoxLayout.LINE_AXIS));
		endYearInput.addFocusListener(new java.awt.event.FocusAdapter() {
		    public void focusGained(java.awt.event.FocusEvent evt) {
		        SwingUtilities.invokeLater(new Runnable() {
		            @Override
		            public void run() {
		            	endYearInput.selectAll();
		            }
		        });
		    }
		});
		endPanel.add(endYearInput);
		endPanel.add(endYear);
		endYear.setFont(new Font("Malgun Gothic", Font.BOLD, 20));
		
		endMonthInput.addFocusListener(new java.awt.event.FocusAdapter() {
		    public void focusGained(java.awt.event.FocusEvent evt) {
		        SwingUtilities.invokeLater(new Runnable() {
		            @Override
		            public void run() {
		            	endMonthInput.selectAll();
		            }
		        });
		    }
		});
		endPanel.add(endMonthInput);
		endPanel.add(endMonth);
		endMonth.setFont(new Font("Malgun Gothic", Font.BOLD, 20));
		
		endDayInput.addFocusListener(new java.awt.event.FocusAdapter() {
		    public void focusGained(java.awt.event.FocusEvent evt) {
		        SwingUtilities.invokeLater(new Runnable() {
		            @Override
		            public void run() {
		            	endDayInput.selectAll();
		            }
		        });
		    }
		});
		endPanel.add(endDayInput);
		endPanel.add(endDay);
		endDay.setFont(new Font("Malgun Gothic", Font.BOLD, 20));
		
		inputPanel.add(endPanel);

		RRP.add(inputPanel, BorderLayout.CENTER);

		buttonPanel.add(backButton);
		buttonPanel.add(registerRoomButton);
		registerRoomButton.addActionListener(this);

		RRP.add(buttonPanel, BorderLayout.SOUTH);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String buttonName = e.getActionCommand();

		if (buttonName.equals("ȸ�ǽ� ��� �Ϸ�")) {

			int max = 0;
			int pri = 0;

			if ((nameInput.getText().length() < 5)
					|| (nameInput.getText().length() > 20)) {
				nameInput.setText("5��~20�ڱ��� �Է� ���ּ���.");
				return;
			}

			try {
				max = Integer.parseInt(maxNumberInput.getText());

				if ((max < 1) || (max > 200)) {
					maxNumberInput.setText("1~200�� ���ڸ� �Է� ���ּ���.");
					return;
				}
			} catch (Exception e2) {
				maxNumberInput.setText("1~200�� ���ڸ� �Է� ���ּ���.");
				return;
			}

			if ((cityInput.getText().length() < 5)
					|| (cityInput.getText().length() > 20)) {
				cityInput.setText("5��~20�ڱ��� �Է� ���ּ���.");
				return;
			}

			if ((zipcodeInput.getText().length() < 1)
					|| (zipcodeInput.getText().length() > 10)) {
				zipcodeInput.setText("1��~10�ڱ��� �Է� ���ּ���.");
				return;
			}

			if ((detailInput.getText().length() < 5)
					|| (detailInput.getText().length() > 10)) {
				detailInput.setText("5��~30�ڱ��� �Է� ���ּ���.");
				return;
			}

			try {
				pri = Integer.parseInt(priceInput.getText());

				if (pri < 10000) {
					priceInput.setText("10000�� �̻� �Է� ���ּ���.");
					return;
				}
			} catch (Exception e3) {
				priceInput.setText("10000�� �̻� �Է� ���ּ���.");
				return;
			}
			
			// Date
			Date start = new Date();
			Date end = new Date();
			int year;
			int month;
			int day;
			
			try {
				year = Integer.parseInt(startYearInput.getText());
				start.setYear(year);
				month = Integer.parseInt(startMonthInput.getText());
				start.setMonth(month);
				day = Integer.parseInt(startDayInput.getText());
				start.setDay(day);
				
				if (year!=start.getYear() || month!=start.getMonth() || day!=start.getDay()) {
					startYearInput.setText(String.valueOf(start.getYear()));
					startMonthInput.setText(String.valueOf(start.getMonth()));
					startDayInput.setText(String.valueOf(start.getDay()));
					return;
				}
			} catch (Exception e4) {
				startYearInput.setText(String.valueOf(start.getYear()));
				startMonthInput.setText(String.valueOf(start.getMonth()));
				startDayInput.setText(String.valueOf(start.getDay()));
				return;
			}
			try {
				year = Integer.parseInt(endYearInput.getText());
				end.setYear(year);
				month = Integer.parseInt(endMonthInput.getText());
				end.setMonth(month);
				day = Integer.parseInt(endDayInput.getText());
				end.setDay(day);
				
				if (year!=end.getYear() || month!=end.getMonth() || day!=end.getDay()) {
					endYearInput.setText(String.valueOf(end.getYear()));
					endMonthInput.setText(String.valueOf(end.getMonth()));
					endDayInput.setText(String.valueOf(end.getDay()));
					return;
				}
				if(!new Date().compareDate(start, end)){
					endYearInput.setText(String.valueOf(start.getYear()));
					endMonthInput.setText(String.valueOf(start.getMonth()));
					endDayInput.setText(String.valueOf(start.getDay()));
					return;
				}
			} catch (Exception e5) {
				endYearInput.setText(String.valueOf(end.getYear()));
				endMonthInput.setText(String.valueOf(end.getMonth()));
				endDayInput.setText(String.valueOf(end.getDay()));
				return;
			}
			
			ClientConsole.getClient().registerRoom(nameInput.getText(), max,
					cityInput.getText(), zipcodeInput.getText(),
					detailInput.getText(), new Price(pri), start, end);

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			}

			UserClient UC = ClientConsole.getClient();
			String message = UC.getMessageFromServer();

			if (message.equals("$$$1")) {
				nameInput.setText("ȸ�ǽ� �̸��� �ߺ��Ǿ����ϴ�.");
				return;
			} else if (message.equals("$$$0")) {
				nameInput.setText("ȸ�ǽ��� ���������� ��ϵǾ����ϴ�.");
				return;
			}
		}
	}

}