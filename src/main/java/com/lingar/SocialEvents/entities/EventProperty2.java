package com.lingar.SocialEvents.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class EventProperty2 {
	private @Id @GeneratedValue Long eventPropertyId;
	
	

	//parent
	@Column(name = "prop_name")
	String name;
	String evPropsVal;
	
	
	public EventProperty2(){}
	public EventProperty2(String name,String evPropsVal ) {
		
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	} 
	
	
}
