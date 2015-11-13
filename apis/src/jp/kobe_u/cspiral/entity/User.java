package jp.kobe_u.cspiral.entity;

import java.io.Serializable;

public class User implements Serializable{

	private String id;

	private String name;

	private String roomId;

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

}
