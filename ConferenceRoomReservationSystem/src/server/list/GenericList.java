package server.list;

import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public abstract class GenericList<T> implements Serializable {

	private int key = 0;
	private ArrayList<T> list = new ArrayList<T>();

	public int getKey() {
		return key;
	}

	public ArrayList<T> getList() {
		return list;
	}

	public void increaseKey() {
		key++;
	}

	public void deleteByKey(int k) {

	}

	public T findByName(String name) {
		return null;
	}

	public T findByID(String ID) {
		return null;
	}

	public T findByKey(int k) {
		return null;
	}

	boolean isItDuplicated(String str) {
		return false;
	}

	public void add(T t) {
		list.add(t);
	}

}