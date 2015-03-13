package Room;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Date implements Serializable{

	private int year;
	private int month;
	private int day;
	
	private boolean booked = false; // 이 날짜에 예약이 되었는지 안 되었는지 확인
	private int normalKey; // 이 날짜에 예약한 일반사용자 키
	
	
	public Date(){
		setYear(0);
		setMonth(0);
		setDay(0);
	}
	public Date(int y,int m,int d){
		setYear(y);
		setMonth(m);
		setDay(d);
	}
	
	public Date nextDate(Date d){
		int year = d.getYear();
		int month = d.getMonth();
		int day = d.getDay()+1;
		int last = getLastDay(year, month);
		
		if(day>last){
			day = 1;
			month += 1;
		}
		if(month>12){
			month = 1;
			year += 1;
		}
		
		Date next = new Date(year,month,day);
		return next;
	}
	
	public boolean compareDate(Date start, Date end){
		int s = start.getYear()*10000 + start.getMonth()*100 + start.getDay();
		int e = end.getYear()*10000 + end.getMonth()*100 + end.getDay();
		
		if(s>e) return false;
		else return true;
	}
	
	public boolean equalDate(Date d1,Date d2){
		if(d1.getYear()!=d2.getYear())
			return false;
		if(d1.getMonth()!=d2.getMonth())
			return false;
		if(d1.getDay()!=d2.getDay())
			return false;
		return true;
	}
	
	@Override
	public String toString(){
		return String.format("%d/%d/%d", getYear(),getMonth(),getDay());
	}
	
	// current year month day
	int currentYear(){
		String Year = new SimpleDateFormat("yyyy").format(new java.util.Date());
		int year = (int)Integer.valueOf(Year);
		return year;
	}
	int currentMonth(){
		String Month = new SimpleDateFormat("M").format(new java.util.Date());
		int month = (int)Integer.valueOf(Month);
		return month;
	}
	int currentDay(){
		String Day = new SimpleDateFormat("d").format(new java.util.Date());
		int day = (int)Integer.valueOf(Day);
		return day;
	}
	
	//last day
	int getLastDay(int y, int m){
		int lastDay = 1;
		Calendar cal = Calendar.getInstance();
		cal.set(y, m-1,lastDay);
		lastDay = cal.getActualMaximum(Calendar.DATE);
		return lastDay;
	}
	
	// set get function
	public void setYear(int y){
		int curYear = currentYear();
		if(y>=curYear){
			if(y>curYear+1)
				year = curYear+1;
			else year = y;
		}
		else year = curYear;
	}
	public int getYear(){
		return year;
	}
	
	public void setMonth(int m){
		int curMonth = currentMonth();
		if(m>=1 && m<=12){
			if(getYear()==currentYear() && m<curMonth)
				month = curMonth;
			else
				month = m;
		}
		else month = curMonth;
	}
	public int getMonth(){
		return month;
	}
	
	public void setDay(int d){
		int curDay = currentDay();
		int lastDay = getLastDay(getYear(), getMonth());
		if(d>lastDay)
			day = lastDay;
		else {
			if(getYear()==currentYear() && getMonth()==currentMonth() && d<curDay)
				day = curDay;
			else day = d;
		}
	}
	public int getDay(){
		return day;
	}
	
	public void setNormalKey(int normalKey){
		this.normalKey = normalKey;
		setBooked(true);
	}
	public int getNormalKey(){
		return normalKey;
	}
	
	public void setBooked(boolean booked){
		this.booked = booked;
	}
	public boolean getBooked(){
		return booked;
	}
	
}
