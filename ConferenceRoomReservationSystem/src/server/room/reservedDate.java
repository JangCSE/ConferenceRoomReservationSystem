package server.room;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class reservedDate implements Serializable {
	private int userKey;
	private Date date;
	
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