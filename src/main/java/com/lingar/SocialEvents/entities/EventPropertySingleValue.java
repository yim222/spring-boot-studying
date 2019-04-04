package com.lingar.SocialEvents.entities;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Entity
public class EventPropertySingleValue extends EventProperty {
	private @Id @GeneratedValue Long id;
	//@OneToOne(cascade=CascadeType.ALL)
	String value;
	
	
	public EventPropertySingleValue() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EventPropertySingleValue(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	public EventPropertySingleValue(Object value) {
		super();
		this.value = value;
	}
	
	public EventPropertySingleValue(String name, Object value) {
		super(name);
		this.value = value;
	}


	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "EventPropertySingleValue [value=" + value + ", name=" + name + "]";
	}
	
	
	
	
}
