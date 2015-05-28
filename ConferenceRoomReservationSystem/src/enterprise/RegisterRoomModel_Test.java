package enterprise;

import static org.junit.Assert.*;

import org.junit.Test;

public class RegisterRoomModel_Test {

	@Test
	public void testSetMessage() {
		RegisterRoomModel rrm = new RegisterRoomModel();
		rrm.setMessage("message");
		
		assertEquals("message", rrm.getMessage());
	}

	@Test
	public void testSetName() {
		RegisterRoomModel rrm = new RegisterRoomModel();
		rrm.setName("name");
		
		assertEquals("name", rrm.getName());
	}

	@Test
	public void testSetMaxNumber() {
		RegisterRoomModel rrm = new RegisterRoomModel();
		rrm.setMaxNumber(100);
		
		assertEquals(100, rrm.getMaxNumber());
	}

	@Test
	public void testSetCost() {
		RegisterRoomModel rrm = new RegisterRoomModel();
		rrm.setCost(10);
		
		assertEquals(10, rrm.getCost());
	}

	@Test
	public void testSetCity() {
		RegisterRoomModel rrm = new RegisterRoomModel();
		rrm.setCity("city");
		
		assertEquals("city", rrm.getCity());
	}

	@Test
	public void testSetDetailLocation() {
		RegisterRoomModel rrm = new RegisterRoomModel();
		rrm.setDetailLocation("detailLocation");
		
		assertEquals("detailLocation", rrm.getDetailLocation());
	}

	@Test
	public void testSetDetail() {
		RegisterRoomModel rrm = new RegisterRoomModel();
		rrm.setDetail("detail");
		
		assertEquals("detail", rrm.getDetail());
	}
	
	@Test
	public void testSetInstance() {
		RegisterRoomModel rrm = new RegisterRoomModel();
		rrm.setInstance("message", "name", "city", "detailLocation", 100, 10, "detail");
		
		assertEquals("message", rrm.getMessage());
		assertEquals("name", rrm.getName());
		assertEquals(100, rrm.getMaxNumber());
		assertEquals(10, rrm.getCost());
		assertEquals("city", rrm.getCity());
		assertEquals("detailLocation", rrm.getDetailLocation());
		assertEquals("detail", rrm.getDetail());
	}

}
