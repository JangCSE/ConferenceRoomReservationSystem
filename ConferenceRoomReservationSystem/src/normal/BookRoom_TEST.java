package normal;

import junit.framework.TestCase;
import master.ClientMasterController;

import org.junit.Test;

import transmission.TransmissionData;

public class BookRoom_TEST extends TestCase {

	@Test
	public void testMessage() {
		ClientMasterController cmc = new ClientMasterController();
		TransmissionData data = new TransmissionData();

		data.setFlags(51);
		data.setMessage("회의실 예약에 성공하였습니다.");
		cmc.perform(data);

		assertEquals(true, true);
	}

}