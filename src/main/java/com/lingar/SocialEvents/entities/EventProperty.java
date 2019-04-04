package com.lingar.SocialEvents.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class EventProperty {
	private @Id @GeneratedValue Long id;
	
	

	//parent
	String name;
	
	public EventProperty(){}
	public EventProperty(String name) {
		
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	} 
	
	
}
