package com.lingar.SocialEvents.entities;
/**
 * Need to be for String and integers too . 
 */
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;
@Data
@Entity
public class EventPropertyMutiValues extends EventProperty {
	private @Id @GeneratedValue Long id;
	String value = "I am property";
	@ElementCollection
	List<Object>  values;
	public EventPropertyMutiValues(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}
	
	public EventPropertyMutiValues(String name, List values) {
		super(name);
		// TODO Auto-generated constructor stub
		this.values = values;
	}
	
	

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public List<Object> getValues() {
		return values;
	}

	public void setValues(List<Object> values) {
		this.values = values;
	}

	@Override
	public String toString() {
		return "EventPropertyMutiValues [value=" + value + ", values=" + values + ", name=" + name + "]";
	}

	

	
}
