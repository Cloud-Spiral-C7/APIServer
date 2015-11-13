package jp.kobe_u.cspiral.entity;

public class Answer {

	private String id;

	private String placeName;

	private String kane;

	private String userId;

	private String roomId;

	public String getId() {
		return id;
	}

	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	public String getKane() {
		return kane;
	}

	public void setKane(String kane) {
		this.kane = kane;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

}
