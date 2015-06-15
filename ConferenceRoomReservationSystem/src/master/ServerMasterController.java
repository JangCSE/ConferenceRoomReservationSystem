package master;

import java.io.IOException;

import server.ConnectionToClient;
import server.management.EPuserManagement;
import server.management.NMuserManagement;
import server.management.RoomManagement;
import server.room.reservedDate;
import server.user.EPuser;
import server.user.NMuser;
import transmission.TransmissionData;

public class ServerMasterController {

	private EPuserManagement EPM = new EPuserManagement();
	private NMuserManagement NMM = new NMuserManagement();
	private RoomManagement RM = new RoomManagement();
	private TransmissionData sendingData;
	private EPuser loginedEPuser;
	private NMuser loginedNMuser;
	private reservedDate bufrd;

	public void perform(TransmissionData data, ConnectionToClient client) {
		sendingData = new TransmissionData();

		if (data.getFlags() < 10) {
			// user register
			if (data.getFlags() == 0) {
				// request register
				sendingData.setFlags(1);
			} else if (data.getFlags() == 2) {
				// request register cancel
				sendingData.setFlags(3);
			} else if (data.getFlags() == 4) {
				// send nmuser data
				if (EPM.isItduplicated(data.getLoginData())
						|| NMM.isItduplicated(data.getLoginData())) {
					sendingData.setFlags(6);
					sendingData.setMessage("이미 가입된 아이디 입니다.");
				} else {
					NMM.addNMuser(data.getNMuser());
					sendingData.setFlags(7);
					sendingData.setMessage("회원가입에 성공하였습니다.");
				}
			} else if (data.getFlags() == 5) {
				// send epuser data
				if (EPM.isItduplicated(data.getLoginData())
						|| NMM.isItduplicated(data.getLoginData())) {
					sendingData.setFlags(6);
					sendingData.setMessage("이미 가입된 아이디 입니다.");
				} else {
					EPM.addEPuser(data.getEPuser());
					sendingData.setFlags(7);
					sendingData.setMessage("회원가입에 성공하였습니다.");
				}
			}

		} else if (data.getFlags() < 20) {
			// login

			if (data.getFlags() == 10) {
				// normal user login
				if (NMM.login(data.getLoginData())) {
					sendingData.setFlags(12); // success
					sendingData.setMessage("로그인에 성공하였습니다.");
					loginedNMuser = new NMuser("", "", "", "", "");
					loginedNMuser.setNMuser(NMM.getNMuserByID(data
							.getLoginData().getId()));
					loginedNMuser.setKey(NMM.getNMuserByID(data
							.getLoginData().getId()).getKey());
				} else {
					sendingData.setFlags(14); // fail
					sendingData.setMessage("로그인에 실패하였습니다.");
				}
			} else if (data.getFlags() == 11) {
				// enterprise user login
				if (EPM.login(data.getLoginData())) {
					sendingData.setFlags(13); // success
					sendingData.setMessage("로그인에 성공하였습니다.");
					loginedEPuser = new EPuser("", "", "", "", "");
					loginedEPuser.setEPuser(EPM.getEPuserByID(data
							.getLoginData().getId()));
					loginedEPuser.setKey(EPM.getEPuserByID(data
							.getLoginData().getId()).getKey());
				} else {
					sendingData.setFlags(14); // fail
					sendingData.setMessage("로그인에 실패하였습니다.");
				}
			}

		} else if (data.getFlags() < 30) {
			// room register
			if (data.getFlags() == 20) {
				// room register
				if (RM.isItduplicated(data.getRoom())) {
					sendingData.setFlags(21);
					sendingData.setMessage("이미 등록된 회의실 입니다.");
				} else {
					data.getRoom().setEnterpriseKey(loginedEPuser.getKey());
					RM.addRoom(data.getRoom());
					sendingData.setFlags(22);
					sendingData.setMessage("회의실 등록에 성공하엿습니다.");
				}
			}

		} else if (data.getFlags() < 40) {
			// registered room list
			if (data.getFlags() == 30) {
				sendingData.setRoomList(RM.getRegisteredRoomList(loginedEPuser
						.getKey()));
				sendingData.setFlags(31);
				sendingData.setMessage("등록한 회의실 정보입니다.");
			}

		} else if (data.getFlags() < 50) {
			// delete room
			if (data.getFlags() == 40) {
				RM.deleteRoom(data.getRoom());
				sendingData.setFlags(41);
				sendingData.setMessage("회의실을 삭제했습니다.");
			}
		} else if (data.getFlags() < 60) {
			// book room
			if (data.getFlags() == 50) {
				if(NMM.getNMuserByKey(loginedNMuser.getKey()).getBookedRoomKeyList().size() < 3) {
					bufrd = new reservedDate();
					bufrd.setDate(data.getDate());
					bufrd.setUserKey(loginedNMuser.getKey());
					RM.getRoom(data.getRoom().getKey())
							.addbookingUserKeyList(bufrd);
					NMM.getNMuserByKey(loginedNMuser.getKey())
							.addBookedRoomKeyList(data.getRoom().getKey());
					sendingData.setFlags(51);
					sendingData.setMessage("회의실을 예약했습니다.");
				} else {
					sendingData.setFlags(54);
					sendingData.setMessage("이미 세개의 회의실을 예약하셨습니다.");
				}
			} else if (data.getFlags() == 52) {
				sendingData.setRoom(data.getRoom());
				sendingData.setFlags(53);
				sendingData.setMessage("회의실 예약 기능을 실행합니다.");
			}
		} else if (data.getFlags() < 70) {
			// bookable room list
			if (data.getFlags() == 60) {
				sendingData.setRoomList(RM.getBookableRoomList(data.getRoom(),
						data.getDate()));
				sendingData.setFlags(61);
			}
		} else if (data.getFlags() < 80) {
			// room info
			if (data.getFlags() == 70) {
				sendingData.setFlags(71);
				sendingData.setRoom(RM.getRoomList().findByKey(data.getRoom().getKey()));
				sendingData.setMessage("회의실 정보입니다.");
			}
		} else if (data.getFlags() < 90) {
			// booked room list
			if (data.getFlags() == 80) {
				int end = loginedNMuser.getBookedRoomKeyList().size();

				for (int i = 0; i < end; i++) {
					sendingData.getRoomList().add(
							RM.getRoom(loginedNMuser.getBookedRoomKeyList()
									.get(i)));
				}
				sendingData.setFlags(81);
				sendingData.setMessage("예약한 회의실 목록입니다.");
			}
		} else if (data.getFlags() < 100) {
			// cancel booking

			if (data.getFlags() == 90) {
				RM.getRoom(data.getRoom().getKey()).deletebookingUserKeyList(
						loginedNMuser.getKey());
				NMM.getNMuserByKey(loginedNMuser.getKey())
						.deleteBookedRoomKeyList(loginedNMuser.getKey());
				sendingData.setFlags(91);
				sendingData.setMessage("예약이 취소되었습니다.");
			}
		} else if (data.getFlags() < 110) {
			// log out

			if (data.getFlags() == 100) {
				sendingData.setFlags(101);
				sendingData.setMessage("로그아웃에 성공하였습니다.");
			}
		}

		try {
			client.sendToClient(sendingData);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void saveToFile() {

	}

	public void loadFromFile() {

	}

}
