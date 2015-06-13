package normal;

import java.util.Observable;

import list.RoomList;

public class ListBookedRoomModel extends Observable {
	private RoomList myList;

	public RoomList getMyList() {
		return myList;
	}

	public void setMyList(RoomList myList) {
		this.myList = myList;
	}

	public ListBookedRoomModel() {

	}
}