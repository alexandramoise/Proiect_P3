package event;

public class Room {
	String roomName;
	int roomPlaces;
	public enum DecorationColors { red, blue, white, purple, pink, gold }
	DecorationColors roomDecoration;
	
	public Room() {
		//super();
	}

	public Room(String roomName, int roomPlaces, DecorationColors roomDecoration) {
		//super();
		this.roomName = roomName;
		this.roomPlaces = roomPlaces;
		this.roomDecoration = roomDecoration;
	}

	@Override
	public String toString() {
		return "Room [roomName=" + roomName + ", roomPlaces=" + roomPlaces + ", roomDecoration=" + roomDecoration + "]";
	}
	
	
}
