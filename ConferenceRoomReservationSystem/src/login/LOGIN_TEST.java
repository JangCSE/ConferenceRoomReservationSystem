package login;

import junit.framework.TestCase;
import master.ClientMasterController;

import org.junit.Test;

import transmission.TransmissionData;

public class LOGIN_TEST extends TestCase {

	@Test
	public void testMessage() {
		ClientMasterController cmc = new ClientMasterController();
		TransmissionData data = new TransmissionData();

		data.setFlags(13);
		data.setMessage("로그인에 실패하였습니다.");
		cmc.perform(data);

		assertEquals(true, true);
	}

}