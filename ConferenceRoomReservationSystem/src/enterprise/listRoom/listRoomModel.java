package enterprise.listRoom;

/*
 * this is MVC model
 */
import java.util.Observable;

import server.list.RoomList;

public class listRoomModel extends Observable {
	private RoomList RL;

	public listRoomModel() {

	}

	public RoomList getRL() {
		return RL;
	}

	public void setRL(RoomList rL) {
		RL = rL;
		setChanged();
		notifyObservers();
	}

}