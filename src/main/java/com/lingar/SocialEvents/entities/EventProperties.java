package com.lingar.SocialEvents.entities;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
@Data //A Project Lombok annotation to autogenerate getters, setters, constructors, toString, hash, equals, and other things.
//It cuts down on the boilerplate.
@Entity ///@Entity(name = "sss")
public class EventProperties {
	
	//id for JPA
	
	private @Id @GeneratedValue Long id;
	
	
	@OneToMany(cascade = CascadeType.ALL)
	@MapKey(name = "name")
	//Map object
	Map<String, EventProperty> data  = new LinkedHashMap<>();
	private @Version @JsonIgnore Long version;

	public EventProperties (){
		//EvetntProperty e 
	}
	public EventProperties(Map<String, EventProperty> data) {
		super();
		
		this.data = data;
	}

	

	@Override
	public String toString() {
		return "EventProperties [data=" + data + "]";
	}
	
	public void  updateData(EventProperty evProp){
		
		data.put(evProp.name, evProp);
		
	}
	
	
	
	
	
}
