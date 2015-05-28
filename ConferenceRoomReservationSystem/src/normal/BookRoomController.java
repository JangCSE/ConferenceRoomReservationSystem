package normal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import master.ClientMasterController;
import room.Room;
import room.reservedDate;
import transmission.TransmissionData;

public class BookRoomController implements ActionListener{

	private BookRoomModel rrm = new BookRoomModel();
	private BookRoomView rrv = new BookRoomView();
	private TransmissionData data;
	private Room room;
	private ArrayList<reservedDate> rd;
	public BookRoomController(){
		
	}
	
	public void controlModel(TransmissionData data) {

		if (data.getFlags() == 50) {
			// successfully registered
			rrm.setMessage(data.getMessage());
		}
			
	}	

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
		if (arg0.getActionCommand().equals("되돌아가기")) {
			data = new TransmissionData();
			data.setFlags(50); // 무슨 값 해야되지???
			
			rrm.isRepeated(rd); // 중복 검사
			
			try {
				ClientMasterController.getClient().sendToServer(data);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
		
		
		rrm.setMessage(rrv.getMsgStr() );
		rrm.setDate(stringTodate(rrv.getDateStr() ) );
		rrm.setSelectedRoom(room);
		
	}
	
	public Date stringTodate(String strDate){
		Date to = null;
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyyMMdd");
		try{
			to=(Date)transFormat.parse(strDate);			
		}catch(Exception e){
			e.printStackTrace();
		}
		return to;
	}
}


