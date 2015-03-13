package Room;

import java.io.Serializable;
import java.util.ArrayList;

public class DefaultRoom implements Serializable {

	// attribute
	private String name;
	private int maxNumber = 0;
	private Location location;
	private Price price;
	private int key;
	private int enterpriseKey;
	private Date start;
	private Date end;
	private ArrayList<Date> period;

	// Constructor
	public DefaultRoom() {
		setKey(0);
		setEnterpriseKey(0);
		setName("Default");
		setMaxNumber(10);
	}

	public DefaultRoom(String name, int maxNumber) {
		setKey(0);
		setEnterpriseKey(0);
		setName(name);
		setMaxNumber(maxNumber);
	}

	// SetGet Function
	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (name.length() < 5) {
			System.err.println("Too short name......");
		} else if (name.length() > 20) {
			System.err.println("Too long name......");
		} else {
			this.name = name;
		}
	}

	public int getMaxNumber() {
		return maxNumber;
	}

	public void setMaxNumber(int maxNumber) {
		if (maxNumber < 1) {
			System.err.println("Unavailable Max Number......");
		} else if (maxNumber > 200) {
			System.err.println("Unavailable Max Number......");
		} else {
			this.maxNumber = maxNumber;
		}
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(String city, String zipcode, String detail) {
		this.location = new Location(city, zipcode, detail);
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Price getPrice() {
		return price;
	}

	public void setPrice(Price price) {
		this.price = price;
	}

	public void setPrice(int pricePerHalf) {
		this.price = new Price(pricePerHalf);
	}

	public boolean isBooked() {
		return false;
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public void print() {
		System.out.println(getName() + ", " + getEnterpriseKey() + ", " + getKey());
	}

	public int getEnterpriseKey() {
		return enterpriseKey;
	}

	public void setEnterpriseKey(int enterpriseKey) {
		this.enterpriseKey = enterpriseKey;
	}
	
	public void setStart(Date s){
		this.start = s;
	}
	
	public Date getStart(){
		return start;
	}
	
	public void setEnd(Date e){
		this.end =e;
	}
	
	public Date getEnd(){
		return end;
	}
	
	public void setPeriod(){
		Date start = this.getStart();
		Date end = this.getEnd();
		
		period = new ArrayList<Date>();
		Date d = start;
		if(start==end) period.add(d);
		else{
			while(new Date().compareDate(d, end)){
				period.add(d);
				d = d.nextDate(d);
			}
		}
	}
	public ArrayList<Date> getPeriod(){
		return period;
	}
}