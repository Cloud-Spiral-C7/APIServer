package moe.lisp.jaxrswebapp.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.eclipse.persistence.nosql.annotations.DataFormatType;
import org.eclipse.persistence.nosql.annotations.Field;
import org.eclipse.persistence.nosql.annotations.NoSql;


@Entity
@NoSql(dataFormat = DataFormatType.MAPPED)
public class User implements Serializable{

	@Id
	@GeneratedValue
	@Field(name = "id")
	private String id;

	@Field(name = "name")
	private String name;

	@Field(name = "roomId")
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
