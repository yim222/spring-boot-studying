package com.lingar.SocialEvents.entities;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
@Data //A Project Lombok annotation to autogenerate getters, setters, constructors, toString, hash, equals, and other things.
//It cuts down on the boilerplate.
@Entity ///@Entity(name = "sss")
public class EventProperties {
	
	//id for JPA
	
	private @Id @GeneratedValue Long eventPropertiesId;
	
	//here is the problem now 
	//trying this : https://stackoverflow.com/a/17076608/9727918
	//@ManyToMany(cascade = CascadeType.ALL, targetEntity = SocialEvent.class)
	//@MapKey(name = "EventProperties")
	@ElementCollection
	@MapKeyColumn(name = "address_type")
	@OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	//@MapKeyEnumerated(EnumType.STRING)
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
