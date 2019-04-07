package com.lingar.SocialEvents.entities;
/**
 * Need to be for String and integers too . 
 */
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;
@Data
@Entity 
public class EventPropertyMutiValues extends EventProperty {
	//private @Id @GeneratedValue Long id3;
	
	@ElementCollection
	@Column(name = "prop_values")
	List<String>  values;
	public EventPropertyMutiValues(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}
	
	public EventPropertyMutiValues(String name, List values) {
		super(name);
		// TODO Auto-generated constructor stub
		this.values = values;
	}
	
	

	

	public List<String> getValues() {
		return values;
	}

	public void setValues(List<String> values) {
		this.values = values;
	}

	@Override
	public String toString() {
		return "EventPropertyMutiValues [, values=" + values + ", name=" + name + "]";
	}

	

	
}
