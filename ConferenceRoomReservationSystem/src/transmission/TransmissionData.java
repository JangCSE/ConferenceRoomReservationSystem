package transmission;

import room.Room;

public class TransmissionData {
	
	public void TranmissionData() {
		
	}

	private int flags;
	private String message;
	private Room room;

	public int getFlags() {
		return flags;
	}

	public void setFlags(int flags) {
		this.flags = flags;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}