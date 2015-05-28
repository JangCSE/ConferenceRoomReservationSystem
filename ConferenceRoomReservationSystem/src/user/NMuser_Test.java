package user;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class NMuser_Test {

	@Test
	public void testNMuser() {
		NMuser temp = new NMuser("id", "password", "name", "email", "contact");
		
		assertEquals("id", temp.getId());
		assertEquals("password", temp.getPassword());
		assertEquals("name", temp.getName());
		assertEquals("email", temp.getEmail());
		assertEquals("contact", temp.getContact());
	}

	@Test
	public void testSetNMuser() {
		NMuser temp = new NMuser("id", "password", "name", "email", "contact");
		assertEquals("id", temp.getId());
		assertEquals("password", temp.getPassword());
		assertEquals("name", temp.getName());
		assertEquals("email", temp.getEmail());
		assertEquals("contact", temp.getContact());
		
		NMuser newUser = new NMuser("newid", "newpassword", "newname", "newemail", "newcontact");
		temp.setNMuser(newUser);
		
		assertEquals("newid", temp.getId());
		assertEquals("newpassword", temp.getPassword());
		assertEquals("newname", temp.getName());
		assertEquals("newemail", temp.getEmail());
		assertEquals("newcontact", temp.getContact());
	}

	@Test
	public void testSetBookedRoomKeyList() {
		NMuser temp = new NMuser("id", "password", "name", "email", "contact");
		ArrayList<Integer> bookedRoomkeyList = new ArrayList<Integer>();
		bookedRoomkeyList.add(1);
		temp.setBookedRoomKeyList(bookedRoomkeyList);
		
		assertEquals(bookedRoomkeyList, temp.getBookedRoomKeyList());
	}

	@Test
	public void testAddBookedRoomKeyList() {
		NMuser temp = new NMuser("id", "password", "name", "email", "contact");
		ArrayList<Integer> bookedRoomkeyList = new ArrayList<Integer>();
		bookedRoomkeyList.add(1);
		temp.setBookedRoomKeyList(bookedRoomkeyList);
		
		assertEquals(1, temp.getBookedRoomKeyList().size());
		
		bookedRoomkeyList.add(2);
		temp.setBookedRoomKeyList(bookedRoomkeyList);
		
		assertEquals(2, temp.getBookedRoomKeyList().size());
	}

	@Test
	public void testDeleteBookedRoomKeyList() {
		NMuser temp = new NMuser("id", "password", "name", "email", "contact");
		ArrayList<Integer> bookedRoomkeyList = new ArrayList<Integer>();
		bookedRoomkeyList.add(1);
		bookedRoomkeyList.add(2);
		temp.setBookedRoomKeyList(bookedRoomkeyList);

		assertEquals(2, temp.getBookedRoomKeyList().size());
		
		temp.deleteBookedRoomKeyList(1);
		assertEquals(1, temp.getBookedRoomKeyList().size());
	}

	@Test
	public void testSetKey() {
		NMuser temp = new NMuser("id", "password", "name", "email", "contact");
		temp.setKey(100);
		
		assertEquals(100, temp.getKey());
	}

	@Test
	public void testSetId() {
		NMuser temp = new NMuser("id", "password", "name", "email", "contact");
		temp.setId("newid");
		
		assertEquals("newid", temp.getId());
	}

	@Test
	public void testSetPassword() {
		NMuser temp = new NMuser("id", "password", "name", "email", "contact");
		temp.setPassword("newpassword");
		
		assertEquals("newpassword", temp.getPassword());
	}

	@Test
	public void testSetName() {
		NMuser temp = new NMuser("id", "password", "name", "email", "contact");
		temp.setName("newname");
		
		assertEquals("newname", temp.getName());
	}

	@Test
	public void testSetEmail() {
		NMuser temp = new NMuser("id", "password", "name", "email", "contact");
		temp.setEmail("newemail");
		
		assertEquals("newemail", temp.getEmail());
	}

	@Test
	public void testSetContact() {
		NMuser temp = new NMuser("id", "password", "name", "email", "contact");
		temp.setContact("newcontact");
		
		assertEquals("newcontact", temp.getContact());
	}

	@Test
	public void testSetLogin() {
		NMuser temp = new NMuser("id", "password", "name", "email", "contact");
		
		temp.setLogin(true);
		assertEquals(true, temp.isLogin());
		
		temp.setLogin(false);
		assertEquals(false, temp.isLogin());
	}

}
