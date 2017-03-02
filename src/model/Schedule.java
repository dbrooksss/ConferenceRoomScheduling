package model;

import java.sql.Time;
import java.util.Date;

public class Schedule{
	private String name;
	private Date date;
	private Time time;

	public Schedule(String name, Date date, Time time){
		this.name = name;
		this.date = date;
		this.time = time;
	}

	public Date getDate(){
		return date;
	}

	public void setDate(Date date){
		this.date = date;
	}

	public Time getTime(){
		return time;
	}

	public void setTime(Time time){
		this.time = time;
	}

	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}
}