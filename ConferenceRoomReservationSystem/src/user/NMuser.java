package user;

import java.util.ArrayList;

public class NMuser extends GenericUser {

	private ArrayList<Integer> bookedRoomKeyList = new ArrayList<Integer>();

	public ArrayList<Integer> getBookedRoomKeyList() {
		return bookedRoomKeyList;
	}

	public void setBookedRoomKeyList(ArrayList<Integer> bookedRoomKeyList) {
		this.bookedRoomKeyList = bookedRoomKeyList;
	}
	
}