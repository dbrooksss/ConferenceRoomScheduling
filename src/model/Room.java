package model;

public class Room{
	private int roomID;
	private String roomName;
	
	public Room(int roomID, String roomName){
		this.setRoomID(roomID);
		this.setRoomName(roomName);
	}

	public String getRoomName(){
		return roomName;
	}

	public void setRoomName(String roomName){
		this.roomName = roomName;
	}

	public int getRoomID(){
		return roomID;
	}

	public void setRoomID(int roomID){
		this.roomID = roomID;
	}
	
	@Override
	public String toString(){
		return roomName;
	}
}
