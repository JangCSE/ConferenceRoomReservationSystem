package normal.listBookedRoom;

import java.util.Observable;

import server.list.RoomList;

public class ListBookedRoomModel extends Observable {
	private RoomList myList;

	public RoomList getMyList() {
		return myList;
	}

	public void setMyList(RoomList myList) {
		this.myList = myList;
		setChanged();
		notifyObservers();
	}

	public ListBookedRoomModel() {

	}
}