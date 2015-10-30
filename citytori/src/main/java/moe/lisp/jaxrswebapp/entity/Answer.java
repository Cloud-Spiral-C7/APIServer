package moe.lisp.jaxrswebapp.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.eclipse.persistence.nosql.annotations.DataFormatType;
import org.eclipse.persistence.nosql.annotations.Field;
import org.eclipse.persistence.nosql.annotations.NoSql;

@Entity
@NoSql(dataFormat = DataFormatType.MAPPED)
public class Answer {

	@Id
	@GeneratedValue
	@Field(name = "id")
	private String id;

	@Field(name = "placeName")
	private String placeName;

	@Field(name = "kane")
	private String kane;

	@Field(name = "userId")
	private String userId;

	@Field(name = "roomId")
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
