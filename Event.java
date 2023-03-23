package event;

import java.sql.Date;

import user.*;

public class Event {
	
	public enum Type { nunta, botez, aniversare }
	User forUser;
    String eventType;
	//Type eventType;
	int invites;
	String eventDate;//Date eventDate;
	Room room;
	Menu food;
	
	public Event() {
	}

	public Event(String eventType, int invites, String eventDate) {
		super();
		this.eventType = eventType;
		this.invites = invites;
		this.eventDate = eventDate;
	}

	/*
	public Event(User forUser, Type eventType, int invites, Date eventDate, Room room, Menu food) {
		super();
		this.forUser = forUser;
		this.eventType = eventType;
		this.invites = invites;
		this.eventDate = eventDate;
		this.room = room;
		this.food = food;
	}
*/
	@Override
	public String toString() {
		String eveniment = Character.toUpperCase(eventType.charAt(0)) + eventType.substring(1);
		return eveniment + " [ eventDate=" + eventDate + ", invites=" + invites + ", room=" + room
				+ ", food=" + food + "]";
	}
	
	
		
}
