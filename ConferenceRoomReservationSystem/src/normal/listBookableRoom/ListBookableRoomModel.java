package normal.listBookableRoom;

import java.util.Observable;

import server.list.RoomList;

/*
 * This class is about to list bookable room
 * MVC model
 */
public class ListBookableRoomModel extends Observable {
	private RoomList myList;

	public RoomList getMyList() {
		return myList;
	}

	public void setMyList(RoomList myList) {
		this.myList = myList;
		setChanged();
		notifyObservers();
	}

	public ListBookableRoomModel() {

	}

}