package list;

import java.util.ArrayList;

public abstract class GenericList<T> {

	private int key = 0;
	private ArrayList<T> list = new ArrayList<T>();

	public int getKey() {
		return key;
	}

	public ArrayList<T> getList() {
		return list;
	}

	void increaseKey() {
		key++;
	}

	void deleteByKey(int k) {

	}

	T findByName(String name) {
		return null;
	}

	T findByID(String ID) {
		return null;
	}

	T findByKey(int k) {
		return null;
	}

	boolean isItDuplicated(String str) {
		return false;
	}

	void add(T t) {
		list.add(t);
	}

}