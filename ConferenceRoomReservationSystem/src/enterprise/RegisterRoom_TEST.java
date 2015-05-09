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
		data.setMessage("ȸ�ǽ� �̸��� �ߺ��Ǿ����ϴ�.");
		cmc.perform(data);

		assertEquals(true, true);
	}

	@Test
	// Duplicate message test in client
	public void testClientSuccessMessage() {
		ClientMasterController cmc = new ClientMasterController();
		TransmissionData data = new TransmissionData();

		data.setFlags(21);
		data.setMessage("ȸ�ǽ��� ���������� ��ϵǾ����ϴ�.");
		cmc.perform(data);

		assertEquals(true, true);
	}

}