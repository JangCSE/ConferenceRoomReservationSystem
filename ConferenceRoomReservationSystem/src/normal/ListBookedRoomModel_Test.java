package normal;

import static org.junit.Assert.*;
import list.RoomList;

import org.junit.Test;

import room.Room;

public class ListBookedRoomModel_Test {

	@Test
	public void testSetMyList() {
		ListBookedRoomModel lbrm = new ListBookedRoomModel();
		RoomList myList = new RoomList();
		Room room = new Room("name", "city", "detailLocation", 50, 50, "detail");
		myList.add(room);
		lbrm.setMyList(myList);
		
		assertEquals(myList, lbrm.getMyList());
	}

}
