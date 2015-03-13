package User;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;

public class NormalUserList {

	private static NormalUserList NOR = new NormalUserList();
	public static NormalUserList getNormalUserList() {
		return NOR;
	}
	
	private ArrayList<NormalUser> list = new ArrayList<NormalUser>();
	private int key;
	
	OutputStream out = null;
	ObjectOutputStream oos = null;

	InputStream in = null;
	ObjectInputStream ois = null;

	public NormalUserList() {
		setKey(0);
		addUser(new NormalUser("aa", "aa", "aa", "aa", "aa"));
		
		try {
			in = new FileInputStream("NormalUserList.ser"); // 입력파일을 받음
			ois = new ObjectInputStream(in); // 입력파일에 있는 오브젝트를 받음

			try {
				list = (ArrayList<NormalUser>) ois.readObject(); // list에 오브젝트를
																	// 받음
				key = (Integer) ois.readObject();
				in.close();
				ois.close();
			} catch (ClassNotFoundException e) { // 오브젝트가 없을때 핸들링
				e.printStackTrace();
			}

		} catch (IOException e) { // 입력파일이 없을때
			// System.err.println("Error opening file.");
			try {
				//setKey(0);
				out = new FileOutputStream("NormalUserList.ser");
				oos = new ObjectOutputStream(out);
				oos.close();// 종료
				out.close();// 종료
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	public void addUser(NormalUser normalUser) {
		normalUser.setKey(this.getKey());
		list.add(normalUser);
		this.increaseKey();
		
		this.saveToFile();
	}

	public void deleteUser(NormalUser normalUser) {
		Iterator<NormalUser> itr = list.iterator();

		while (itr.hasNext()) {
			if (itr.next().equals(normalUser)) {
				itr.remove();
			}
		}
		
		this.saveToFile();
	}

	public NormalUser findUser(NormalUser normalUser) {
		Iterator<NormalUser> itr = list.iterator();

		while (itr.hasNext()) {
			Object temp = itr.next();

			if (temp.equals(normalUser)) {
				return (NormalUser) temp;
			}
		}

		return null;
	}
	
	public NormalUser findUser(String id) {
		Iterator<NormalUser> itr = list.iterator();

		while (itr.hasNext()) {
			NormalUser temp = itr.next();

			if (temp.getId().equals(id)) {
				return (NormalUser) temp;
			}
		}

		return null;
	}

	public void showUser() {
		Iterator<NormalUser> itr = list.iterator();

		while (itr.hasNext()) {
			NormalUser temp = itr.next();
			System.out.println(temp.getId() + " " + temp.getKey() + " "
					+ temp.getPassword());
		}
	}

	public int getKey() {
		return key;
	}

	private void setKey(int key) {
		this.key = key;
	}

	public void increaseKey() {
		key++;
	}
	
	public void saveToFile() {
		try {
			out = new FileOutputStream("NormalUserList.ser"); // 출력할 장소
			oos = new ObjectOutputStream(out); // 출력할 오브젝트
			oos.writeObject(list); // NUL을 저장
			oos.writeObject(key);
			oos.close();// 종료
			out.close();// 종료
		} catch (IOException e) {
			System.err.println("IOError");
		}
	}
}
