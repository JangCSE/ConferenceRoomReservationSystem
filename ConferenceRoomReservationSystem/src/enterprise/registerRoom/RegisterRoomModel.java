package enterprise.registerRoom;

import java.util.Observable;

public class RegisterRoomModel extends Observable {

	private String message;
	private String name;
	private String city;
	private String detailLocation;
	private int maxNumber;
	private int cost;
	private String detail;

	public RegisterRoomModel() {

	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
		setChanged();
		notifyObservers();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		setChanged();
		notifyObservers();
	}

	public int getMaxNumber() {
		return maxNumber;
	}

	public void setMaxNumber(int maxNumber) {
		this.maxNumber = maxNumber;
		setChanged();
		notifyObservers();
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
		setChanged();
		notifyObservers();
	}

	public String getCity() {
		return city;

	}

	public void setCity(String city) {
		this.city = city;
		setChanged();
		notifyObservers();
	}

	public String getDetailLocation() {
		return detailLocation;
	}

	public void setDetailLocation(String detailLocation) {
		this.detailLocation = detailLocation;
		setChanged();
		notifyObservers();
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
		setChanged();
		notifyObservers();
	}

	public void setInstance(String msg, String nm, String ct, String dl,
			int mn, int c, String dt) {
		setMessage(msg);
		setName(nm);
		setCity(ct);
		setDetailLocation(dl);
		setMaxNumber(mn);
		setCost(c);
		setDetail(dt);
	}

	int validCheck() {
		
		if(getCity() == null)
			return 1;
		
		if(getMaxNumber() < 2 || getMaxNumber() > 10000)
			return 2;
		
		if(getCost() < 10000 || (getCost() % 1000 != 0))
			return 3;
		
		if(getName().length() < 1 || getName().length() > 20)
			return 4;
		
		if(getDetail().length() > 500)
			return 5;
		
		return 0;
	}

}