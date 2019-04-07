package com.lingar.SocialEvents.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class EventPropertySingleValue extends EventProperty {
	//private @Id @GeneratedValue Long id2;
	//@OneToOne(cascade=CascadeType.ALL)
	@Column(name = "prop_value")
	String value;
	
	
	public EventPropertySingleValue() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EventPropertySingleValue(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	
	
	public EventPropertySingleValue(String name, String value) {
		super(name);
		this.value = value;
	}


	public Object getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "EventPropertySingleValue [value=" + value + ", name=" + name + "]";
	}
	
	
	
	
}
