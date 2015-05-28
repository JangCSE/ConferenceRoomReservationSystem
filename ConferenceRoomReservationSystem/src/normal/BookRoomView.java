package normal;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import enterprise.RegisterRoomModel;

public class BookRoomView extends JPanel implements Observer{
	//메시지 중복???
	private JPanel BookRoomPanel = new JPanel();
	private JTextField msgField = new JTextField("메세지");
	private JTextField dateField = new JTextField("날짜");
	private JTextField selelctedRoomField = new JTextField("Selected Room");
	private JTextArea messageArea = new JTextArea("메시지");
	private JButton backButton = new JButton("되돌아가기");
	private final Insets insets = new Insets(10, 10, 10, 10);
	
	public BookRoomView(){
		this.setBackground(Color.orange);
		messageArea.setEditable(false);
		BookRoomPanel.setBackground(Color.WHITE);
		BookRoomPanel.setLayout(new GridBagLayout());
		
		addComponent(BookRoomPanel, new JLabel("메세지 : "), 0, 1, 1, 1,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH);
		addComponent(BookRoomPanel, new JLabel("날짜 : "), 0, 2, 1, 1,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH);
		addComponent(BookRoomPanel, new JLabel("Selected Room : "), 0, 3, 1, 1,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH);
		addComponent(BookRoomPanel, messageArea, 1, 4,
				GridBagConstraints.REMAINDER, 1, GridBagConstraints.CENTER,
				GridBagConstraints.BOTH);
	}
	
	public String getMsgStr(){
		return msgField.getText();
	}
	
	public String getDateStr(){
		return dateField.getText();
	}
	
	public String getSelectedRoomStr(){
		return selelctedRoomField.getText();
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		messageArea.setText(((RegisterRoomModel) arg0).getMessage());
	}	

	public void setListener(ActionListener listener) {
		backButton.addActionListener(listener);		
	}
	private void addComponent(JPanel container, Component component, int gridx,
			int gridy, int gridwidth, int gridheight, int anchor, int fill) {
		GridBagConstraints gbc = new GridBagConstraints(gridx, gridy,
				gridwidth, gridheight, 1.0, 1.0, anchor, fill, insets, 0, 0);
		container.add(component, gbc);
	}
}
