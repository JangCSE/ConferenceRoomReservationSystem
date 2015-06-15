package normal.showRoominfo;

import java.util.Observable;

import server.room.Room;

public class ShowRoominfoModel extends Observable {

	private Room selectedRoom;

	public ShowRoominfoModel() {

	}

	public Room getSelectedRoom() {
		return selectedRoom;
	}

	public void setSelectedRoom(Room selectedRoom) {
		this.selectedRoom = selectedRoom;
		setChanged();
		notifyObservers();
	}

}