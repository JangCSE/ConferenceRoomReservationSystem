package transmission;

import static org.junit.Assert.*;

import org.junit.Test;

public class LoginData_Test {

	@Test
	public void testSetId() {
		LoginData LD = new LoginData("id", "pw");
		
		assertEquals("id", LD.getId());
	}

	@Test
	public void testSetPw() {
		LoginData LD = new LoginData("id", "pw");
		
		assertEquals("pw", LD.getPw());
	}

}
