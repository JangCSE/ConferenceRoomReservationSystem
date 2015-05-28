package normal;

import static org.junit.Assert.*;

import org.junit.Test;

import room.Room;

public class ShowRoominfoModel_Test {

	@Test
	public void testSetSelectedRoom() {
		ShowRoominfoModel srm = new ShowRoominfoModel();
		Room room = new Room("name", "city", "detailLocation", 50, 50, "detail");
		srm.setSelectedRoom(room);
		
		assertEquals(room, srm.getSelectedRoom());
	}

}
