package normal.listBookableRoom;

import java.util.Observable;

import server.list.RoomList;

public class ListBookableRoomModel extends Observable {
	private RoomList myList;

	public RoomList getMyList() {
		return myList;
	}

	public void setMyList(RoomList myList) {
		this.myList = myList;
	}

	public ListBookableRoomModel() {

	}

}