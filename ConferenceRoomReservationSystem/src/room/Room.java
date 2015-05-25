package room;

import java.io.Serializable;

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

}