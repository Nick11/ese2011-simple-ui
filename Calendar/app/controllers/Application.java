package controllers;

import play.*;
import play.mvc.*;
import play.test.Fixtures;

import java.util.*;

import org.jboss.netty.bootstrap.Bootstrap;

import models.*;
import models.Calendar;

@With(Secure.class)
public class Application extends Controller {
private static Database database = Database.getInstance();
    public static void index() {
    	
    	User user = database.getUserByName(Security.connected());
    	ArrayList<User> users = database.getUsers();
    	render(user,users);
    }
    
    public static void calendar(int index, String otherUserName){
    	
    	User otherUser = database.getUserByName(otherUserName);
    	User user = database.getUserByName(Security.connected());
    	Calendar calendar = otherUser.getCalendars().get(index);
    	ArrayList<Event> list = calendar.getListOfAllEvents(user);
    	
    	render(calendar, list, user);
    }
    public static void userPage(String userName){
    	User otherUser = database.getUserByName(userName);
    	User user = database.getUserByName(Security.connected());
    	render(otherUser, user);
    }

    public static void addEvent(String calendarName, String name, String startDate, String endDate, String isPrivate){
    	boolean privateEvent = (isPrivate.equals("y"));
    	
    	User user = database.getUserByName(Security.connected());
    	Calendar calendar = null;
    	for(Calendar cal:user.getCalendars()){
    		if(cal.getName().equals(calendarName)){
    			calendar = cal;
    			break;
    		}
    	}
    	calendar.addEvent(new Event(name, convertToCalendar(startDate), convertToCalendar(endDate), privateEvent), user);
    	Application.calendar(user.getCalendars().indexOf(calendar), user.getName());
    }
    private static GregorianCalendar convertToCalendar(String string){
    	
    	int year = Integer.valueOf(string.substring(6,10));
    	int month = Integer.valueOf(string.substring(3,5))-1;
    	int day = Integer.valueOf(string.substring(0,2));
    	int hour = Integer.valueOf(string.substring(11,13));
    	int minute = Integer.valueOf(string.substring(14,16));
    	
    	
    	
    	
    	
    	GregorianCalendar calendar = new GregorianCalendar(year,month,day,hour,minute);
    	return calendar;
    }
}