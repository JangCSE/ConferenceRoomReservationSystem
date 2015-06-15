package server.room;

import java.io.Serializable;
import java.util.Date;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class Room implements Serializable {

	private String message;
	private String name;
	private String city;
	private String detailLocation;
	private int maxNumber;
	private int cost;
	private String detail;
	private int enterpriseKey;
	private int key;
	private ArrayList<reservedDate> bookingUserKeyList = new ArrayList<reservedDate>();

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public ArrayList<reservedDate> getBookingUserKeyList() {
		return bookingUserKeyList;
	}

	public void setBookingUserKeyList(ArrayList<reservedDate> bookingUserKeyList) {
		this.bookingUserKeyList = bookingUserKeyList;
	}

	public Room(String name, String city, String detailLocation, int maxNumber,
			int cost, String detail) {
		setName(name);
		setCity(city);
		setDetailLocation(detailLocation);
		setMaxNumber(maxNumber);
		setCost(cost);
		setDetail(detail);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDetailLocation() {
		return detailLocation;
	}

	public void setDetailLocation(String detailLocation) {
		this.detailLocation = detailLocation;
	}

	public int getMaxNumber() {
		return maxNumber;
	}

	public void setMaxNumber(int maxNumber) {
		this.maxNumber = maxNumber;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public int getEnterpriseKey() {
		return enterpriseKey;
	}

	public void setEnterpriseKey(int enterpriseKey) {
		this.enterpriseKey = enterpriseKey;
	}

	public void addbookingUserKeyList(reservedDate rd) {
		getBookingUserKeyList().add(rd);
	}

	public void deletebookingUserKeyList(int key) {
		int end = getBookingUserKeyList().size();

		for (int i = 0; i < end; i++) {
			if (getBookingUserKeyList().get(i).getUserKey() == key) {
				getBookingUserKeyList().remove(i);
				break;
			}
		}
	}
	
	public boolean isReservedDate(Date date) {
		int end = getBookingUserKeyList().size();
		
		for(int i=0;i<end;i++) {
			if(getBookingUserKeyList().get(i).getDate().equals(date))
				return true;
		}
		
		return false;
	}

}