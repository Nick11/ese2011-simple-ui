package models;

import java.util.GregorianCalendar;
import java.util.Locale;


public class Event {

	private String name;
	private GregorianCalendar startDate;
	private GregorianCalendar endDate;
	private boolean isPrivate;
	/**
	 * 
	 * @param name
	 * @param startDate
	 * @param endDate
	 * @param isPrivate
	 */
	public Event(String name, GregorianCalendar startDate, GregorianCalendar endDate, boolean isPrivate){
		assert(!startDate.after(endDate)); //startDate <= endDate should be possible.
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.isPrivate = isPrivate;
	}
	public boolean isPrivate(){
		return this.isPrivate;
	}
	
	//getters, setters
	public GregorianCalendar getStartDate(){
		return this.startDate;
	}
	public GregorianCalendar getEndDate(){
		return this.endDate;
	}
	public String getName(){
		return this.name;
	}
	public String getStartDateString(){
		String date = "";
		date = date+this.startDate.get(GregorianCalendar.DATE)+". ";
		date = date+this.startDate.getDisplayName(GregorianCalendar.MONTH, GregorianCalendar.LONG, Locale.GERMAN)+" ";
		date = date+this.startDate.get(GregorianCalendar.YEAR)+"; ";
		date = date+this.startDate.get(GregorianCalendar.HOUR_OF_DAY)+":";
		date = date+this.startDate.get(GregorianCalendar.MINUTE);
		return date;
	}
	public String getEndDateString(){
		String date = "";
		date = date+this.endDate.get(GregorianCalendar.DATE)+". ";
		date = date+this.endDate.getDisplayName(GregorianCalendar.MONTH, GregorianCalendar.LONG, Locale.GERMAN)+" ";
		date = date+this.endDate.get(GregorianCalendar.YEAR)+"; ";
		date = date+this.endDate.get(GregorianCalendar.HOUR_OF_DAY)+":";
		date = date+(this.endDate.get(GregorianCalendar.MINUTE)==0?"00":this.endDate.get(GregorianCalendar.MINUTE)) ;
		return date;
	}
}
