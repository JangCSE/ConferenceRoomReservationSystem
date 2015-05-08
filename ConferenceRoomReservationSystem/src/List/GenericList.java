package List;

import java.util.ArrayList;

public abstract class GenericList<T> {

	private int key;
	private ArrayList<T> list;

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public ArrayList<T> getList() {
		return list;
	}

	public void setList(ArrayList<T> list) {
		this.list = list;
	}
	
	void increaseKey() {
		
	}
	
	void deleteByName(String name) {
		
	}
	
	void deleteByKey(int k) {
		
	}
	
	boolean isItDuplicated(String str) {
		return true;
	}
	
	void add(T t) {
		
	}

}