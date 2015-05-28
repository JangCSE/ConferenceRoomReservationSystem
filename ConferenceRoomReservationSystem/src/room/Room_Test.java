package room;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;

public class Room_Test {

	@Test
	public void testSetKey() {
		Room temp = new Room("name", "city", "detailLocation", 50, 50, "detail");
		temp.setKey(100);
		
		assertEquals(100, temp.getKey());
	}

	@Test
	public void testSetBookingUserKeyList() {
		Room temp = new Room("name", "city", "detailLocation", 50, 50, "detail");
		
		ArrayList<reservedDate> bookingUserKeyList = new ArrayList<reservedDate>();
		reservedDate rd = new reservedDate();
		Date date = new Date(2015, 5, 5);
		rd.setDate(date);
		bookingUserKeyList.add(rd);
		
		temp.setBookingUserKeyList(bookingUserKeyList);
		
		assertEquals(bookingUserKeyList, temp.getBookingUserKeyList());	
	}

	@Test
	public void testRoom() {
		Room temp = new Room("name", "city", "detailLocation", 50, 50, "detail");
		
		assertEquals("name", temp.getName());
		assertEquals("city", temp.getCity());
		assertEquals("detailLocation", temp.getDetailLocation());
		assertEquals(50, temp.getMaxNumber());
		assertEquals(50, temp.getCost());
		assertEquals("detail", temp.getDetail());
	}

	@Test
	public void testSetMessage() {
		Room temp = new Room("name", "city", "detailLocation", 50, 50, "detail");
		temp.setMessage("message");
		
		assertEquals("message", temp.getMessage());
	}

	@Test
	public void testSetName() {
		Room temp = new Room("name", "city", "detailLocation", 50, 50, "detail");
		temp.setName("newname");
		
		assertEquals("newname", temp.getName());
	}

	@Test
	public void testSetCity() {
		Room temp = new Room("name", "city", "detailLocation", 50, 50, "detail");
		temp.setCity("newcity");
		
		assertEquals("newcity", temp.getCity());
	}

	@Test
	public void testSetDetailLocation() {
		Room temp = new Room("name", "city", "detailLocation", 50, 50, "detail");
		temp.setDetailLocation("newdetailLocation");
		
		assertEquals("newdetailLocation", temp.getDetailLocation());

	}

	@Test
	public void testSetMaxNumber() {
		Room temp = new Room("name", "city", "detailLocation", 50, 50, "detail");
		temp.setMaxNumber(100);
		
		assertEquals(100, temp.getMaxNumber());
	}

	@Test
	public void testSetCost() {
		Room temp = new Room("name", "city", "detailLocation", 50, 50, "detail");
		temp.setCost(200);
		
		assertEquals(200, temp.getCost());
	}

	@Test
	public void testSetDetail() {
		Room temp = new Room("name", "city", "detailLocation", 50, 50, "detail");
		temp.setDetail("newdetail");
		
		assertEquals("newdetail", temp.getDetail());
	}

	@Test
	public void testSetEnterpriseKey() {
		Room temp = new Room("name", "city", "detailLocation", 50, 50, "detail");
		temp.setEnterpriseKey(20);
		
		assertEquals(20, temp.getEnterpriseKey());
	}

	@Test
	public void testAddbookingUserKeyList() {
		Room temp = new Room("name", "city", "detailLocation", 50, 50, "detail");
		
		ArrayList<reservedDate> bookingUserKeyList = new ArrayList<reservedDate>();
		reservedDate rd = new reservedDate();
		Date date = new Date(2015, 5, 5);
		rd.setDate(date);
		bookingUserKeyList.add(rd);
		
		temp.setBookingUserKeyList(bookingUserKeyList );
		
		assertEquals(bookingUserKeyList, temp.getBookingUserKeyList());
	}

	@Test
	public void testDeletebookingUserKeyList() {
		Room temp = new Room("name", "city", "detailLocation", 50, 50, "detail");
		
		ArrayList<reservedDate> bookingUserKeyList = new ArrayList<reservedDate>();
		reservedDate rd = new reservedDate();
		Date date = new Date(2015, 5, 5);
		rd.setDate(date);
		rd.setUserKey(10);
		bookingUserKeyList.add(rd);
		
		temp.setBookingUserKeyList(bookingUserKeyList);
		
		assertEquals(1, temp.getBookingUserKeyList().size());
		
		temp.deletebookingUserKeyList(10);
		assertEquals(0, temp.getBookingUserKeyList().size());

	}

}
