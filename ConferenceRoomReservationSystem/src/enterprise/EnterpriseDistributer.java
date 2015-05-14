package enterprise;

import transmission.TransmissionData;

public class EnterpriseDistributer {

	private RegisterRoomController rrc = new RegisterRoomController();
	
	public EnterpriseDistributer() {
		
	}

	public void distribute(TransmissionData data) {
		if (data.getFlags() < 30) {
			// room register
			rrc.controlModel(data);
		} else if (data.getFlags() < 40) {
			// registered room list
			
		} else if (data.getFlags() < 50) {
			// delete room

		}
	}

}