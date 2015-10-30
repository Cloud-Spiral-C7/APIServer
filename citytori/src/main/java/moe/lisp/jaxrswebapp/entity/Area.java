package moe.lisp.jaxrswebapp.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.eclipse.persistence.nosql.annotations.DataFormatType;
import org.eclipse.persistence.nosql.annotations.Field;
import org.eclipse.persistence.nosql.annotations.NoSql;

@Entity
@NoSql(dataFormat = DataFormatType.MAPPED)
public class Area {

	@Id
	@Field(name = "id")
	private int id;

	@Field(name = "name")
	private String name;

	@Field(name = "latLon")
	private String latLon;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLatLon() {
		return latLon;
	}

	public void setLatLon(String latLon) {
		this.latLon = latLon;
	}

}
