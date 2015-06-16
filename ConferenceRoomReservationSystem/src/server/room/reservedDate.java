package server.room;

import java.io.Serializable;
import java.util.Date;

/*
 * This class is about booked user data in room
 */
@SuppressWarnings("serial")
public class reservedDate implements Serializable {
	private int userKey;
	private Date date;
	private int dateKey;

	public int getDateKey() {
		return dateKey;
	}

	public void setDateKey(int dateKey) {
		this.dateKey = dateKey;
	}

	public int getUserKey() {
		return userKey;
	}

	public void setUserKey(int userKey) {
		this.userKey = userKey;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}