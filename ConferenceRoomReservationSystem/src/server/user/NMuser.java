package server.user;

import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class NMuser extends GenericUser implements Serializable {
	
	private int dateKey = 0;

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
		setDateKey(nmuser.getDateKey());
		setLogin(true);
		
	}

	private ArrayList<BookedRoomKey> bookedRoomkeyList = new ArrayList<BookedRoomKey>();

	public ArrayList<BookedRoomKey> getBookedRoomKeyList() {
		return bookedRoomkeyList;
	}

	public void setBookedRoomKeyList(ArrayList<BookedRoomKey> bookedRoomKeyList) {
		this.bookedRoomkeyList = bookedRoomKeyList;
	}

	public void addBookedRoomKeyList(int key) {
		BookedRoomKey brk = new BookedRoomKey();
		brk.setBookedRoomkey(key);
		brk.setDateKey(getDateKey());
		increasingKey();
		
		getBookedRoomKeyList().add(brk);
	}

	public void deleteBookedRoomKeyList(int key,int datekey) {
		int end = getBookedRoomKeyList().size();

		for (int i = 0; i < end; i++) {
			if (getBookedRoomKeyList().get(i).getBookedRoomkey() == key) {
				if(getBookedRoomKeyList().get(i).getDateKey() == datekey) {
					getBookedRoomKeyList().remove(i);
					break;
				}
			}
		}
	}

	public int getDateKey() {
		return dateKey;
	}

	public void setDateKey(int dateKey) {
		this.dateKey = dateKey;
	}
	
	public void increasingKey() {
		dateKey++;
	}

}