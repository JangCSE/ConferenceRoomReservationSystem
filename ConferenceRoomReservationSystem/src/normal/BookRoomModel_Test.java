package normal;

import static org.junit.Assert.*;

import java.util.Date;

import login.LoginModel;

import org.junit.Test;

import room.Room;

public class BookRoomModel_Test {

	@Test
	public void testSetMessage() {
		BookRoomModel brm = new BookRoomModel();
		brm.setMessage("message");
		
		assertEquals("message", brm.getMessage());
	}

	@Test
	public void testSetDate() {
		BookRoomModel brm = new BookRoomModel();
		Date date = new Date(2015, 5, 5);
		brm.setDate(date);
		
		assertEquals(date, brm.getDate());
	}

	@Test
	public void testSetSelectedRoom() {
		BookRoomModel brm = new BookRoomModel();
		Room room = new Room("name", "city", "detailLocation", 50, 50, "detail");
		brm.setSelectedRoom(room);
		
		assertEquals(room, brm.getSelectedRoom());
	}

	@Test
	public void testSetInstance() {
		BookRoomModel brm = new BookRoomModel();
		Date date = new Date(2015, 5, 5);
		Room room = new Room("name", "city", "detailLocation", 50, 50, "detail");
		brm.setInstance("message", date, room);
		
		assertEquals("message", brm.getMessage());
		assertEquals(date, brm.getDate());
		assertEquals(room, brm.getSelectedRoom());
	}

	@Test
	public void testValidCheck() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsRepeated() {
		fail("Not yet implemented");
	}

}
