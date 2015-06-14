package server.user;

import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class NMuser extends GenericUser implements Serializable {

	public NMuser(String id, String pw, String name, String email, String ct) {
		super(id, pw, name, email, ct);
	}

	public void setNMuser(NMuser nmuser) {
		// using save logined user data in server
		setId(nmuser.getId());
		setPassword(nmuser.getPassword());
		setName(nmuser.getName());
		setEmail(nmuser.getEmail());
		setContact(nmuser.getContact());
		setKey(nmuser.getKey());
		setBookedRoomKeyList(nmuser.getBookedRoomKeyList());
		setLogin(true);
	}

	private ArrayList<Integer> bookedRoomkeyList = new ArrayList<Integer>();

	public ArrayList<Integer> getBookedRoomKeyList() {
		return bookedRoomkeyList;
	}

	public void setBookedRoomKeyList(ArrayList<Integer> bookedRoomKeyList) {
		this.bookedRoomkeyList = bookedRoomKeyList;
	}

	public void addBookedRoomKeyList(int key) {
		getBookedRoomKeyList().add(key);
	}

	public void deleteBookedRoomKeyList(int key) {
		int end = getBookedRoomKeyList().size();

		for (int i = 0; i < end; i++) {
			if (getBookedRoomKeyList().get(i) == key) {
				getBookedRoomKeyList().remove(i);
				break;
			}
		}
	}

}