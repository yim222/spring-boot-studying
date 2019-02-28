package com.lingar.SocialEvents.entities;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;





@Data //A Project Lombok annotation to autogenerate getters, setters, constructors, toString, hash, equals, and other things.
//It cuts down on the boilerplate.
@Entity ///@Entity(name = "sss")
public class SocialEvent {
	
	private @Id @GeneratedValue Long id;
	private String description;
	private int fromAge, toAge;
	
	private @Version @JsonIgnore Long version;

	

	private SocialEvent() {}


	public SocialEvent( String description, int fromAge, int toAge) {
		
		this.description = description;
		this.fromAge = fromAge;
		this.toAge = toAge;
	}


	@Override
	public String toString() {
		return "SocialEvent [id=" + id + ", description=" + description + ", fromAge=" + fromAge + ", toAge=" + toAge
				+ ", version=" + version + "]";
	}
	
	
}