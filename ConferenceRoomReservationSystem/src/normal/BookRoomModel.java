package normal;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Observable;

import room.reservedDate;
import room.Room;
import transmission.TransmissionData;

public class BookRoomModel extends Observable {

	private String message;
	private Date date;
	private Room selectedRoom;
	private HashSet hs; // 중복 데이터 제거

	public BookRoomModel() {

	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
		setChanged();
		notifyObservers();
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
		setChanged();
		notifyObservers();
	}

	public Room getSelectedRoom() {
		return selectedRoom;
	}

	public void setSelectedRoom(Room room) {
		this.selectedRoom = room;
		setChanged();
		notifyObservers();
	}

	public void setInstance(String msg, Date date, Room room) {
		setMessage(msg);
		setDate(date);
		setSelectedRoom(room);
	}

	int validCheck() {
		return 0;// /???
	}

	public void isRepeated(ArrayList<reservedDate> rd) {
		Iterator it = rd.iterator();
		while (it.hasNext()) {
			if (it.equals(rd)) {
				System.out.println("다른 날짜를 선택하세요.");
				// 다른 날짜를 선택하게 하기.
			}
		}
	}
}