package Room;

import java.io.Serializable;

public class BookedRoom implements Serializable{

	private String name;
	private Date date;
	private int normalKey;
	private boolean isbooked;
	
	public BookedRoom(){
		
	}
	
	//setget function
	public void setName(String n){
		name =n;
	}
	public String getName(){
		return name;
	}
	public void setDate(Date d){
		date = d;
	}
	public Date getDate(){
		return date;
	}
	public void setNormalKey(int nk){
		normalKey = nk;
	}
	public int getNormalKey(){
		return normalKey;
	}
	public void setIsbooked(boolean ib){
		isbooked = ib;
	}
	public boolean getIsbooked(){
		return isbooked;
	}
}
