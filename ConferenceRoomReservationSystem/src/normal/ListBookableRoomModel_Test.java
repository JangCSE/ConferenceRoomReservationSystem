package normal;

import static org.junit.Assert.*;
import list.RoomList;

import org.junit.Test;

import room.Room;

public class ListBookableRoomModel_Test {

	@Test
	public void testSetMyList() {
		ListBookableRoomModel lbrm = new ListBookableRoomModel();
		RoomList myList = new RoomList();
		Room room = new Room("name", "city", "detailLocation", 50, 50, "detail");
		myList.add(room);
		lbrm.setMyList(myList);
		
		assertEquals(myList, lbrm.getMyList());
	}

}
