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


public class EnterpriseUserList {

	private static EnterpriseUserList EUL = new EnterpriseUserList();
	public static EnterpriseUserList getEnterpriseUserList() {
		return EUL;
	}
	
	private ArrayList<EnterpriseUser> list = new ArrayList<EnterpriseUser>();
	private int key;
	
	OutputStream out = null;
	ObjectOutputStream oos = null;

	InputStream in = null;
	ObjectInputStream ois = null;

	public EnterpriseUserList() {
		setKey(0);
		addUser(new EnterpriseUser("admin", "admin", "admin", "admin", "admin"));
		addUser(new EnterpriseUser("qq", "qq", "qq", "qq", "qq"));
		
		try {
			in = new FileInputStream("EnterpriseUserList.ser"); // �Է������� ����
			ois = new ObjectInputStream(in); // �Է����Ͽ� �ִ� ������Ʈ�� ����

			try {
				list = (ArrayList<EnterpriseUser>) ois.readObject(); // list�� ������Ʈ��
																	// ����
				key =  (Integer) ois.readObject();
				in.close();
				ois.close();
			} catch (ClassNotFoundException e) { // ������Ʈ�� ������ �ڵ鸵
				e.printStackTrace();
			}

		} catch (IOException e) { // �Է������� ������
			// System.err.println("Error opening file.");
			try {
				//setKey(0);
				out = new FileOutputStream("EnterpriseUserList.ser");
				oos = new ObjectOutputStream(out);
				oos.close();// ����
				out.close();// ����
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	public void addUser(EnterpriseUser enterpriseUser) {
		enterpriseUser.setKey(this.getKey());
		list.add(enterpriseUser);
		this.increaseKey();
		
		this.saveToFile();
	}

	public void deleteUser(EnterpriseUser enterpriseUser) {
		Iterator<EnterpriseUser> itr = list.iterator();

		while (itr.hasNext()) {
			if (itr.next().equals(enterpriseUser)) {
				itr.remove();
			}
		}
		
		this.saveToFile();
	}

	public EnterpriseUser findUser(EnterpriseUser enterpriseUser) {
		Iterator<EnterpriseUser> itr = list.iterator();

		while (itr.hasNext()) {
			Object temp = itr.next();

			if (temp.equals(enterpriseUser)) {
				return (EnterpriseUser) temp;
			}
		}

		return null;
	}
	
	public EnterpriseUser findUser(String id) {
		Iterator<EnterpriseUser> itr = list.iterator();

		while (itr.hasNext()) {
			EnterpriseUser temp = itr.next();

			if (temp.getId().equals(id)) {
				return (EnterpriseUser) temp;
			}
		}

		return null;
	}

	public void showUser() {
		Iterator<EnterpriseUser> itr = list.iterator();

		while (itr.hasNext()) {
			EnterpriseUser temp = itr.next();
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
			out = new FileOutputStream("EnterpriseUserList.ser"); // ����� ���
			oos = new ObjectOutputStream(out); // ����� ������Ʈ
			oos.writeObject(list); // EUL�� ����
			oos.writeObject(key);
			oos.close();// ����
			out.close();// ����
		} catch (IOException e) {
			System.err.println("IOError");
		}
	}

}