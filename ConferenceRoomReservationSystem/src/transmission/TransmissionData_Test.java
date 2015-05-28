package transmission;

import static org.junit.Assert.*;

import java.util.Date;

import list.RoomList;

import org.junit.Test;

import room.Room;
import user.EPuser;
import user.NMuser;

public class TransmissionData_Test {

	@Test
	public void testSetDate() {
		TransmissionData temp = new TransmissionData();
		Date date = new Date(2015, 5, 5);
		temp.setDate(date);
		
		assertEquals(date, temp.getDate());
	}

	@Test
	public void testSetFlags() {
		TransmissionData temp = new TransmissionData();
		temp.setFlags(10);
		
		assertEquals(10, temp.getFlags());
	}

	@Test
	public void testSetRoom() {
		TransmissionData temp = new TransmissionData();
		Room tempRoom = new Room("name", "city", "detailLocation", 50, 50, "detail");
		temp.setRoom(tempRoom);
		
		assertEquals(tempRoom, temp.getRoom());
	}

	@Test
	public void testSetMessage() {
		TransmissionData temp = new TransmissionData();
		temp.setMessage("test");
		
		assertEquals("test", temp.getMessage());
	}

	@Test
	public void testSetLoginData() {
		TransmissionData temp = new TransmissionData();
		LoginData LD = new LoginData("id", "pw");
		temp.setLoginData(LD);
		
		assertEquals(LD, temp.getLoginData());
	}

	@Test
	public void testSetEPuser() {
		TransmissionData temp = new TransmissionData();
		EPuser tempUser = new EPuser("id", "password", "name", "email", "contact");
		temp.setEPuser(tempUser);
		
		assertEquals(tempUser, temp.getEPuser());
	}

	@Test
	public void testSetNMuser() {
		TransmissionData temp = new TransmissionData();
		NMuser tempUser = new NMuser("id", "password", "name", "email", "contact");
		temp.setNMuser(tempUser);
		
		assertEquals(tempUser, temp.getNMuser());
	}

	@Test
	public void testSetRoomList() {
		TransmissionData temp = new TransmissionData();
		RoomList RL = new RoomList();
		Room tempRoom = new Room("name", "city", "detailLocation", 50, 50, "detail");
		
		RL.add(tempRoom);
		
		temp.setRoomList(RL);
		
		assertEquals(tempRoom, temp.getRoomList().getList().get(0));
	}

}
