package user;

import java.util.ArrayList;

public class NMuser extends GenericUser {

	public NMuser(String id, String pw, String name, String email, String ct) {
		super(id, pw, name, email, ct);
	}

	private ArrayList<Integer> bookedRoomKeyList = new ArrayList<Integer>();

	public ArrayList<Integer> getBookedRoomKeyList() {
		return bookedRoomKeyList;
	}

	public void setBookedRoomKeyList(ArrayList<Integer> bookedRoomKeyList) {
		this.bookedRoomKeyList = bookedRoomKeyList;
	}

}