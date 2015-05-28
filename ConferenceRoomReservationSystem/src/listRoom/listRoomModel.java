package listRoom;

import java.util.Observable;

import list.RoomList;

public class listRoomModel extends Observable  {
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
