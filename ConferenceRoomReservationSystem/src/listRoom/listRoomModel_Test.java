package listRoom;

import static org.junit.Assert.*;
import list.RoomList;

import org.junit.Test;

import room.Room;

public class listRoomModel_Test {

	@Test
	public void testSetRL() {
		listRoomModel lrm = new listRoomModel();
		
		RoomList RL = new RoomList();
		
		Room temp = new Room("name", "city", "detailLocation", 50, 50, "detail");
		RL.add(temp);
		
		lrm.setRL(RL);
		
		assertEquals(RL, lrm.getRL());
	}

}
