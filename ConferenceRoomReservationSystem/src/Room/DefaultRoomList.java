package Room;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.*;

public class DefaultRoomList {

	private ArrayList<DefaultRoom> list = new ArrayList<DefaultRoom>();
	private ArrayList<DefaultRoom> regList = null;
	private static DefaultRoomList DRL = new DefaultRoomList();
	private int key;

	OutputStream out = null;
	ObjectOutputStream oos = null;

	InputStream in = null;
	ObjectInputStream ois = null;

	public DefaultRoomList() {
		try {
			in = new FileInputStream("DefaultRoomList.ser"); // 입력파일을 받음
			ois = new ObjectInputStream(in); // 입력파일에 있는 오브젝트를 받음

			try {
				list = (ArrayList<DefaultRoom>) ois.readObject(); // list에 오브젝트를
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
				setKey(0);
				out = new FileOutputStream("DefaultRoomList.ser");
				oos = new ObjectOutputStream(out);
				oos.close();// 종료
				out.close();// 종료
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}

	}

	public static DefaultRoomList getDefaultRoomList() {
		return DRL;
	}

	public void addNewRoom(DefaultRoom dr) {
		dr.setKey(this.key);
		increaseKey();
		list.add(dr);

		dr.print();

		saveToFile();
	}

	public void deleteRoom(int key) {
		DefaultRoom dr = findRoom(key);
		list.remove(dr);
		saveToFile();
	}

	public int deleteRoom(String name) {
		DefaultRoom dr = findRoom(name);

		if (dr != null) {
			list.remove(dr);
			saveToFile();
			return 0;
		} else
			return 1;
	}

	public int isItOverlapped(DefaultRoom r) {
		DefaultRoom temp = null;

		temp = findRoom(r.getName());

		if (temp == null)
			return 0;
		else
			return 1;

		// 0은 중복 없음
		// 1은 중복 있음
	}

	public DefaultRoom findRoom(DefaultRoom r) {
		Iterator<DefaultRoom> itr = list.iterator();

		while (itr.hasNext()) {
			DefaultRoom temp = itr.next();

			if (temp.equals(r)) {
				return temp;
			}
		}

		System.err.println("Cannot find......");
		return null;
	}

	public DefaultRoom findRoom(int key) {
		Iterator<DefaultRoom> itr = list.iterator();

		while (itr.hasNext()) {
			DefaultRoom temp = itr.next();

			if (temp.getKey() == key) {
				return temp;
			}
		}

		System.err.println("Cannot find......");
		return null;
	}

	public DefaultRoom findRoom(String name) {

		Iterator<DefaultRoom> itr = list.iterator();

		while (itr.hasNext()) {
			DefaultRoom temp = itr.next();

			if (temp.getName().equals(name)) {
				return temp;
			}
		}

		System.err.println("Cannot find......");
		return null;
	}

	public ArrayList<DefaultRoom> findRegisteredRoom(int enterpriseKey) {

		regList = new ArrayList<DefaultRoom>();

		Iterator<DefaultRoom> itr = list.iterator();

		while (itr.hasNext()) {
			DefaultRoom temp = itr.next();

			if (temp.getEnterpriseKey() == enterpriseKey) {
				regList.add(temp);
			}
		}

		return this.regList;

	}

	public int getKey() {
		return key;
	}

	private void setKey(int key) {
		this.key = key;
	}

	public void increaseKey() {
		this.key++;
	}

	public void print() {
		Iterator<DefaultRoom> itr = list.iterator();

		while (itr.hasNext()) {
			DefaultRoom temp = itr.next();

			temp.print();
		}

		System.out.println();
	}

	public ArrayList<DefaultRoom> getList() {
		return this.list;
	}

	public void saveToFile() {
		try {
			out = new FileOutputStream("DefaultRoomList.ser"); // 출력할 장소
			oos = new ObjectOutputStream(out); // 출력할 오브젝트
			oos.writeObject(list); // DRL을 저장
			oos.writeObject(key);
			oos.close();// 종료
			out.close();// 종료
		} catch (IOException e) {
			System.err.println("IOError");
		}
	}
	
	public void modifyBook(String name, Date date, int nk, boolean ib){
		for(int i=0; i<list.size(); i++){
			if(list.get(i).getName().equals(name)){
				for(int j=0; j<list.get(i).getPeriod().size(); j++){
					if(list.get(i).getPeriod().get(j).equalDate(
							list.get(i).getPeriod().get(j),date)){
						list.get(i).getPeriod().get(j).setBooked(ib);
						list.get(i).getPeriod().get(j).setNormalKey(nk);
					}
				}
			}
		}
		saveToFile();
	}

}