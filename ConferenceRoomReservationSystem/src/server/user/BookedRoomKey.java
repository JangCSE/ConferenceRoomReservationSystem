package server.user;

import java.io.Serializable;

@SuppressWarnings("serial")
public class BookedRoomKey implements Serializable {
	private int bookedRoomkey;
	private int dateKey;

	public int getBookedRoomkey() {
		return bookedRoomkey;
	}

	public void setBookedRoomkey(int bookedRoomkey) {
		this.bookedRoomkey = bookedRoomkey;
	}

	public int getDateKey() {
		return dateKey;
	}

	public void setDateKey(int dateKey) {
		this.dateKey = dateKey;
	}

}
