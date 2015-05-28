package login;

import static org.junit.Assert.*;

import org.junit.Test;

public class LoginModel_Test {

	@Test
	public void testSetId() {
		LoginModel lm = new LoginModel();
		lm.setId("id");
		
		assertEquals("id", lm.getId());
	}

	@Test
	public void testSetPw() {
		LoginModel lm = new LoginModel();
		lm.setPw("pw");
		
		assertEquals("pw", lm.getPw());
	}

	@Test
	public void testSetMessage() {
		LoginModel lm = new LoginModel();
		lm.setMessage("message");
		
		assertEquals("message", lm.getMessage());
	}

}
