package register;

import static org.junit.Assert.*;

import org.junit.Test;

public class RegisterModel_Test {

	@Test
	public void testSetId() {
		RegisterModel rm = new RegisterModel();
		rm.setId("id");
		
		assertEquals("id", rm.getId());
	}

	@Test
	public void testSetPassword() {
		RegisterModel rm = new RegisterModel();
		rm.setPassword("pw");
		
		assertEquals("pw", rm.getPassword());
	}

	@Test
	public void testSetName() {
		RegisterModel rm = new RegisterModel();
		rm.setName("name");
		
		assertEquals("name", rm.getName());
	}

	@Test
	public void testSetEmail() {
		RegisterModel rm = new RegisterModel();
		rm.setEmail("email");
		
		assertEquals("email", rm.getEmail());
	}

	@Test
	public void testSetContact() {
		RegisterModel rm = new RegisterModel();
		rm.setContact("contact");
		
		assertEquals("contact", rm.getContact());
	}

	@Test
	public void testSetMessage() {
		RegisterModel rm = new RegisterModel();
		rm.setMessage("message");
		
		assertEquals("message", rm.getMessage());
	}

	@Test
	public void testSetConfirmPassword() {
		RegisterModel rm = new RegisterModel();
		rm.setConfirmPassword("confirmPassword");
		
		assertEquals("confirmPassword", rm.getConfirmPassword());
	}

}
