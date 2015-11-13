package jp.kobe_u.cspiral.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="room")
public class ResponseRoom{

	private String roomId;

	public ResponseRoom(){

	}

	@XmlElement(name="id")
	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

}
