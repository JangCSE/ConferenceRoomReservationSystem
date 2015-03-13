package Room;

import java.io.Serializable;

public class Location implements Serializable {

	// Location
	private String city;
	private String zipcode;
	private String detail;

	// Constructor
	public Location() {
		setCity("Default");
		setZipcode("Default");
		setDetail("FUCKYOU");
	}

	public Location(String city, String zipcode, String detail) {
		setCity(city);
		setZipcode(zipcode);
		setDetail(detail);
	}

	// SetGet Function
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		if (city.length() < 5) {
			System.err.println("Too short city......");
		} else if (city.length() > 20) {
			System.err.println("Too long city......");
		} else {
			this.city = city;
		}
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		if (zipcode.length() < 1) {
			System.err.println("Too short zipcode......");
		} else if (zipcode.length() > 10) {
			System.err.println("Too long zipcode......");
		} else {
			this.zipcode = zipcode;
		}
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		if (detail.length() < 1) {
			System.err.println("Too short detail......");
		} else if (detail.length() > 30) {
			System.err.println("Too long detail......");
		} else {
			this.detail = detail;
		}
	}

}