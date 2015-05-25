package transmission;

import java.io.Serializable;

import room.Room;

@SuppressWarnings("serial")
public class TransmissionData implements Serializable {
	
	public void TranmissionData() {
	}

	private int flags;
	private String message;
	private Room room;
	private LoginData loginData;

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

	public LoginData getLoginData() {
		return loginData;
	}

	public void setLoginData(LoginData loginData) {
		this.loginData = loginData;
	}
	
}