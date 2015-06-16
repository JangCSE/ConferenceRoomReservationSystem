package master;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import server.ConnectionToClient;
import server.list.EnterpriseUserList;
import server.list.NormalUserList;
import server.list.RoomList;
import server.management.EPuserManagement;
import server.management.NMuserManagement;
import server.management.RoomManagement;
import server.room.Room;
import server.room.reservedDate;
import server.user.EPuser;
import server.user.NMuser;
import transmission.TransmissionData;

/*
 * This class is server master controller.
 * If client send transmission data, this controls methods by data's flag.
 */
public class ServerMasterController {

	private EPuserManagement EPM = new EPuserManagement();
	private NMuserManagement NMM = new NMuserManagement();
	private RoomManagement RM = new RoomManagement();
	private TransmissionData sendingData;
	private EPuser loginedEPuser;
	private NMuser loginedNMuser;
	private reservedDate bufrd;

	private OutputStream out = null;
	private ObjectOutputStream oos = null;

	private InputStream in = null;
	private ObjectInputStream ois = null;

	public ServerMasterController() {
		loadFromFile();
	}

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
					loginedNMuser.setKey(NMM.getNMuserByID(
							data.getLoginData().getId()).getKey());
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
					loginedEPuser.setKey(EPM.getEPuserByID(
							data.getLoginData().getId()).getKey());
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
			}

		} else if (data.getFlags() < 50) {
			// delete room
			if (data.getFlags() == 40) {
				RM.deleteRoom(data.getKey());
				sendingData.setFlags(41);
			}
		} else if (data.getFlags() < 60) {
			// book room
			if (data.getFlags() == 50) {
				if (NMM.getNMuserByKey(loginedNMuser.getKey())
						.getBookedRoomKeyList().size() < 3) {
					if (RM.getRoom(data.getKey())
							.isReservedDate(data.getDate())) {
						sendingData.setFlags(53);
					} else {
						bufrd = new reservedDate();
						bufrd.setDate(data.getDate());
						bufrd.setUserKey(loginedNMuser.getKey());
						bufrd.setDateKey(NMM.getNMuserByKey(
								loginedNMuser.getKey()).getDateKey());
						NMM.getNMuserByKey(loginedNMuser.getKey())
								.addBookedRoomKeyList(data.getKey());
						RM.getRoom(data.getKey()).addbookingUserKeyList(bufrd);
						sendingData.setFlags(51);
					}
				} else {
					sendingData.setFlags(52);
				}
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
				Room temp = RM.getRoom(data.getKey());
				Room rm = new Room(temp.getName(), temp.getCity(),
						temp.getDetailLocation(), temp.getMaxNumber(),
						temp.getCost(), temp.getDetail());

				for (int i = 0; i < temp.getBookingUserKeyList().size(); i++) {
					reservedDate temp2 = new reservedDate();
					temp2.setDate(temp.getBookingUserKeyList().get(i).getDate());
					temp2.setUserKey(temp.getBookingUserKeyList().get(i)
							.getUserKey());
					rm.addbookingUserKeyList(temp2);
				}

				sendingData.setRoom(rm);

			}
		} else if (data.getFlags() < 90) {
			// show booked room list
			if (data.getFlags() == 80) {
				int end = loginedNMuser.getBookedRoomKeyList().size();
				RoomList temp = new RoomList();
				for (int i = 0; i < end; i++) {
					temp.add(RM.getRoom(loginedNMuser.getBookedRoomKeyList()
							.get(i).getBookedRoomkey()));
					temp.getTempDateKey().add(
							loginedNMuser.getBookedRoomKeyList().get(i)
									.getDateKey());
				}
				sendingData.setRoomList(temp);
				sendingData.setFlags(81);
			} else if (data.getFlags() == 82) {
				int end = RM.getRoom(data.getKey()).getBookingUserKeyList()
						.size();
				for (int i = 0; i < end; i++) {
					if (RM.getRoom(data.getKey()).getBookingUserKeyList()
							.get(i).getUserKey() == loginedNMuser.getKey()
							&& RM.getRoom(data.getKey())
									.getBookingUserKeyList().get(i)
									.getDateKey() == data.getDateKey()) {
						sendingData.setDate(RM.getRoom(data.getKey())
								.getBookingUserKeyList().get(i).getDate());
						sendingData.setFlags(83);
					}
				}
			}
		} else if (data.getFlags() < 100) {
			// cancel booking

			if (data.getFlags() == 90) {
				RM.getRoom(data.getRoom().getKey()).deletebookingUserKeyList(
						loginedNMuser.getKey(), data.getDateKey());
				NMM.getNMuserByKey(loginedNMuser.getKey())
						.deleteBookedRoomKeyList(loginedNMuser.getKey(),
								data.getDateKey());
				int end = loginedNMuser.getBookedRoomKeyList().size();
				RoomList temp = new RoomList();
				for (int i = 0; i < end; i++) {
					temp.add(RM.getRoom(loginedNMuser.getBookedRoomKeyList()
							.get(i).getBookedRoomkey()));
					temp.getTempDateKey().add(
							loginedNMuser.getBookedRoomKeyList().get(i)
									.getDateKey());
				}
				sendingData.setRoomList(temp);
				
				System.out.println(temp.getList().size());
				sendingData.setFlags(91);
			}
		} else if (data.getFlags() < 110) {
			// log out
			if (data.getFlags() == 100) {
				sendingData.setFlags(101);
			}
		}

		try {
			client.sendToClient(sendingData);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		saveToFile();
	}

	/*
	 * save data to file for next use.
	 */
	public void saveToFile() {
		try {
			out = new FileOutputStream("database.ser"); // 출력할 장소
			oos = new ObjectOutputStream(out); // 출력할 오브젝트
			oos.writeObject(EPM.getEnterpriseUserList()); // DRL을 저장
			oos.writeObject(NMM.getNormalUserList());
			oos.writeObject(RM.getRoomList());
			oos.close();// 종료
			out.close();// 종료
		} catch (IOException e) {
			System.err.println("IOError");
		}
	}

	/*
	 * when server starts, server loads data file.
	 */
	public void loadFromFile() {
		try {
			in = new FileInputStream("database.ser"); // 입력파일을 받음
			ois = new ObjectInputStream(in); // 입력파일에 있는 오브젝트를 받음

			try {
				EPM.setEnterpriseUserList((EnterpriseUserList) ois.readObject());
				NMM.setNormalUserList((NormalUserList) ois.readObject());
				RM.setRoomList((RoomList) ois.readObject());

				in.close();
				ois.close();
			} catch (ClassNotFoundException e) { // 오브젝트가 없을때 핸들링
				e.printStackTrace();
			}

		} catch (IOException e) { // 입력파일이 없을때
			try {
				out = new FileOutputStream("database.ser");
				oos = new ObjectOutputStream(out);

				oos.close();// 종료
				out.close();// 종료
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

}
