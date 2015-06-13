package transmission;

import java.io.Serializable;
import java.util.Date;

import server.list.RoomList;
import server.room.Room;
import server.user.EPuser;
import server.user.NMuser;

@SuppressWarnings("serial")
public class TransmissionData implements Serializable {

	public void TranmissionData() {
	}

	private int flags;
	private String message;
	private Room room;
	private LoginData loginData;
	private EPuser epuser;
	private NMuser nmuser;
	private RoomList roomlist;
	private Date date;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

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

	public EPuser getEPuser() {
		return epuser;
	}

	public void setEPuser(EPuser epuser) {
		this.epuser = epuser;
	}

	public NMuser getNMuser() {
		return nmuser;
	}

	public void setNMuser(NMuser nmuser) {
		this.nmuser = nmuser;
	}

	public RoomList getRoomList() {
		return roomlist;
	}

	public void setRoomList(RoomList roomlist) {
		this.roomlist = roomlist;
	}

}