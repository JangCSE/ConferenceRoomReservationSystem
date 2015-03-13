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
			in = new FileInputStream("NormalUserList.ser"); // �Է������� ����
			ois = new ObjectInputStream(in); // �Է����Ͽ� �ִ� ������Ʈ�� ����

			try {
				list = (ArrayList<NormalUser>) ois.readObject(); // list�� ������Ʈ��
																	// ����
				key = (Integer) ois.readObject();
				in.close();
				ois.close();
			} catch (ClassNotFoundException e) { // ������Ʈ�� ������ �ڵ鸵
				e.printStackTrace();
			}

		} catch (IOException e) { // �Է������� ������
			// System.err.println("Error opening file.");
			try {
				//setKey(0);
				out = new FileOutputStream("NormalUserList.ser");
				oos = new ObjectOutputStream(out);
				oos.close();// ����
				out.close();// ����
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
			out = new FileOutputStream("NormalUserList.ser"); // ����� ���
			oos = new ObjectOutputStream(out); // ����� ������Ʈ
			oos.writeObject(list); // NUL�� ����
			oos.writeObject(key);
			oos.close();// ����
			out.close();// ����
		} catch (IOException e) {
			System.err.println("IOError");
		}
	}
}
