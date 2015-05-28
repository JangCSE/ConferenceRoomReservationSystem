package list;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import room.Room;
import room.reservedDate;

public class RoomList_Test {

	@Test
	public void testDeleteByKey() {
		RoomList RL = new RoomList();
		Room temp = new Room("name", "city", "detailLocation", 50, 50, "detail");
		temp.setKey(100);
		
		RL.add(temp);
		
		RL.deleteByKey(1);
		assertEquals(true, RL.isItDuplicated("name"));
		
		RL.deleteByKey(100);
		assertEquals(false, RL.isItDuplicated("name"));
	}

	@Test
	public void testIsItDuplicated() {
		RoomList RL = new RoomList();
		Room temp = new Room("name", "city", "detailLocation", 50, 50, "detail");
		
		RL.add(temp);
		
		assertEquals(true, RL.isItDuplicated("name"));
		
		assertEquals(false, RL.isItDuplicated("notname"));
	}

	@Test
	public void testFindByEPKey() {
		RoomList RL = new RoomList();
		Room temp = new Room("name", "city", "detailLocation", 50, 50, "detail");
		temp.setEnterpriseKey(100);
		
		RL.add(temp);
		
		assertEquals(temp, RL.findByEPKey(100).getList().get(0));
	}

	@Test
	public void testFindBookableRoomList() {
		RoomList RL = new RoomList();
		Room temp = new Room("name", "city", "detailLocation", 50, 50, "detail");
		Date date = new Date(2015, 5, 5);
		
		RL.add(temp);
		
		assertEquals(temp, RL.findBookableRoomList(temp, date).getList().get(0));
	}

	@Test
	public void testFindByKeyInt() {
		RoomList RL = new RoomList();
		Room temp = new Room("name", "city", "detailLocation", 50, 50, "detail");
		temp.setKey(100);
		RL.add(temp);
		
		assertEquals(temp, RL.findByKey(100));
		assertEquals(null, RL.findByKey(1));
	}

	

	@Test
	public void testGetKey() {
		RoomList RL = new RoomList();
		
		assertEquals(0, RL.getKey());
	}

	@Test
	public void testGetList() {
		RoomList RL = new RoomList();
		Room temp = new Room("name", "city", "detailLocation", 50, 50, "detail");
		
		RL.add(temp);
		
		assertEquals(temp, RL.getList().get(0));
	}

	@Test
	public void testIncreaseKey() {
		RoomList RL = new RoomList();
		
		
		assertEquals(0, RL.getKey());
		RL.increaseKey();
		assertEquals(1, RL.getKey());
	}


	@Test
	public void testAdd() {
		RoomList RL = new RoomList();
		Room temp = new Room("name", "city", "detailLocation", 50, 50, "detail");
		
		RL.add(temp);
		
		assertEquals(1, RL.getList().size());
		
		Room temp2 = new Room("name", "city", "detailLocation", 50, 50, "detail");
		RL.add(temp2);
		
		assertEquals(2, RL.getList().size());
	}

}
