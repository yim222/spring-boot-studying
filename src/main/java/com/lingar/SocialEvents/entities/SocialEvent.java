package com.lingar.SocialEvents.entities;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;





@Data //A Project Lombok annotation to autogenerate getters, setters, constructors, toString, hash, equals, and other things.
//It cuts down on the boilerplate.
@Entity ///@Entity(name = "sss")
public class SocialEvent {
	
	private @Id @GeneratedValue Long socialEventId;
	private String description, moreValue;
	private int fromAge, toAge;
	//@ElementCollection(targetClass=EventProperty.class)
	
	//@OneToOne(cascade=CascadeType.ALL)
	//@ManyToOne(cascade=CascadeType.ALL)
	//private EventProperty eventProperty = new EventProperty("something");
	@ElementCollection(fetch = FetchType.EAGER)
	private List<String> myArray; 
	
	@OneToOne(cascade=CascadeType.ALL)
	private EventProperties eventProps;
	
	private @Version @JsonIgnore Long version;

	

	private SocialEvent() {}
	
	public SocialEvent(String description, String moreValue, int fromAge, int toAge, EventProperties eventProps) {
		super();
		this.description = description;
		this.moreValue = moreValue;
		this.fromAge = fromAge;
		this.toAge = toAge;
		this.eventProps = eventProps;
	}


	public SocialEvent( String description, int fromAge, int toAge, String moreValue, List<String> myArray) {
		
		this.description = description;
		this.fromAge = fromAge;
		this.toAge = toAge;
		this.moreValue = moreValue;
		//eventProperty = new EventProperyMutiValues("something");
		
		
		this.myArray = myArray; 
	}
	
	//trying
	public SocialEvent( String description, int fromAge, int toAge, String moreValue) {
		
		this.description = description;
		this.fromAge = fromAge;
		this.toAge = toAge;
		this.moreValue = moreValue;
		
		 List<String> myArray2 = new ArrayList<String>();
		 myArray2.add("no values");
		this.myArray = myArray2; 
	}

	@Override
	public String toString() {
		return "SocialEvent [socialEventId=" + socialEventId + ", description=" + description + ", moreValue="
				+ moreValue + ", fromAge=" + fromAge + ", toAge=" + toAge + ", myArray=" + myArray + ", eventProps="
				+ eventProps + ", version=" + version + "]";
	}
	
	
}