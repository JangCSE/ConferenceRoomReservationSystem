package user;

import static org.junit.Assert.*;

import org.junit.Test;

public class EPuser_Test {

	@Test
	public void testEPuser() {
		EPuser temp = new EPuser("id", "password", "name", "email", "contact");
		
		assertEquals("id", temp.getId());
		assertEquals("password", temp.getPassword());
		assertEquals("name", temp.getName());
		assertEquals("email", temp.getEmail());
		assertEquals("contact", temp.getContact());
	}

	@Test
	public void testSetEPuser() {
		EPuser temp = new EPuser("id", "password", "name", "email", "contact");
		assertEquals("id", temp.getId());
		assertEquals("password", temp.getPassword());
		assertEquals("name", temp.getName());
		assertEquals("email", temp.getEmail());
		assertEquals("contact", temp.getContact());
		
		EPuser newUser = new EPuser("newid", "newpassword", "newname", "newemail", "newcontact");
		temp.setEPuser(newUser);
		
		assertEquals("newid", temp.getId());
		assertEquals("newpassword", temp.getPassword());
		assertEquals("newname", temp.getName());
		assertEquals("newemail", temp.getEmail());
		assertEquals("newcontact", temp.getContact());
	}

	@Test
	public void testSetKey() {
		EPuser temp = new EPuser("id", "password", "name", "email", "contact");
		temp.setKey(100);
		
		assertEquals(100, temp.getKey());
	}

	@Test
	public void testSetId() {
		EPuser temp = new EPuser("id", "password", "name", "email", "contact");
		temp.setId("newid");
		
		assertEquals("newid", temp.getId());
	}

	@Test
	public void testSetPassword() {
		EPuser temp = new EPuser("id", "password", "name", "email", "contact");
		temp.setPassword("newpassword");
		
		assertEquals("newpassword", temp.getPassword());
	}

	@Test
	public void testSetName() {
		EPuser temp = new EPuser("id", "password", "name", "email", "contact");
		temp.setName("newname");
		
		assertEquals("newname", temp.getName());
	}

	@Test
	public void testSetEmail() {
		EPuser temp = new EPuser("id", "password", "name", "email", "contact");
		temp.setEmail("newemail");
		
		assertEquals("newemail", temp.getEmail());
	}

	@Test
	public void testSetContact() {
		EPuser temp = new EPuser("id", "password", "name", "email", "contact");
		temp.setContact("newcontact");
		
		assertEquals("newcontact", temp.getContact());
	}

	@Test
	public void testSetLogin() {
		EPuser temp = new EPuser("id", "password", "name", "email", "contact");
		
		temp.setLogin(true);
		assertEquals(true, temp.isLogin());
		
		temp.setLogin(false);
		assertEquals(false, temp.isLogin());
	}

}
