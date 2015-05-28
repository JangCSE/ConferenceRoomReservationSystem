package room;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

public class reservedDate_Test {

	@Test
	public void testSetUserKey() {
		reservedDate rd = new reservedDate();
		rd.setUserKey(1);
		
		assertEquals(1, rd.getUserKey());
	}

	@Test
	public void testSetDate() {
		reservedDate rd = new reservedDate();
		Date date = new Date(2015, 5, 5);
		rd.setDate(date);
		
		assertEquals(date, rd.getDate());
	}

}
