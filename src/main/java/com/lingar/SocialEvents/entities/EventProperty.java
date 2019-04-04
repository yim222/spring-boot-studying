package com.lingar.SocialEvents.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
//@Embeddable
public class EventProperty {
	private @Id @GeneratedValue Long id;
	

	//parent
	String name;
	
	private @Version @JsonIgnore Long version;
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
