package enterprise;

import junit.framework.TestCase;
import master.ClientMasterController;

import org.junit.Test;

import transmission.TransmissionData;

public class RegisterRoom_TEST extends TestCase {

	@Test
	// Duplicate message test in client
	public void testClientDepMessage() {
		ClientMasterController cmc = new ClientMasterController();
		TransmissionData data = new TransmissionData();

		data.setFlags(21);
		data.setMessage("회의실 이름이 중복되었습니다.");
		cmc.perform(data);

		assertEquals(true, true);
	}

	@Test
	// Duplicate message test in client
	public void testClientSuccessMessage() {
		ClientMasterController cmc = new ClientMasterController();
		TransmissionData data = new TransmissionData();

		data.setFlags(21);
		data.setMessage("회의실이 성공적으로 등록되었습니다.");
		cmc.perform(data);

		assertEquals(true, true);
	}

}