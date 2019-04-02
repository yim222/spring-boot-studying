package com.lingar.SocialEvents.entities;
import java.util.List;

import javax.persistence.ElementCollection;
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
	private String description, moreValue;
	private int fromAge, toAge;
	@ElementCollection
	private List<String> myArray; 
	
	private @Version @JsonIgnore Long version;

	

	private SocialEvent() {}


	public SocialEvent( String description, int fromAge, int toAge, String moreValue, List<String> myArray) {
		
		this.description = description;
		this.fromAge = fromAge;
		this.toAge = toAge;
		this.moreValue = moreValue;
		this.myArray = myArray; 
	}


	@Override
	public String toString() {
		return "SocialEvent [id=" + id + ", description=" + description + ", fromAge=" + fromAge + ", toAge=" + toAge
				+ ", version=" + version + " moreValue = " + moreValue +"]";
	}
	
	
}