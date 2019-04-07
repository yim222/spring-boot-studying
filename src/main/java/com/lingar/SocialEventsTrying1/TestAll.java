package com.lingar.SocialEventsTrying1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.lingar.SocialEvents.entities.EventProperties;
import com.lingar.SocialEvents.entities.EventPropertiesGenerator;
import com.lingar.SocialEvents.entities.EventPropertyMutiValues;
import com.lingar.SocialEvents.entities.EventPropertySingleValue;
import com.lingar.SocialEvents.entities.SocialEvent;

public class TestAll {
	
	
	public static void main(String[] args) {
		
		
		System.out.println("Test EvpropMultiValues: \n");
		
		EventPropertyMutiValues e = new EventPropertyMutiValues("דגג");
		List<Object> list1 = new ArrayList<Object>(
				Arrays.asList(
						"מפגש " , "שיעור תורה" , "הרצאה" , "טיול יומי", "ערב משחקים", "מסיבה",  "מאטצ'-אפ", "סדנה" , "נופש", "סופש" , "ספידייט" ,  "הופעה" 
						
						)
				); 

				
		List<String> list2 = new ArrayList<String>(
				Arrays.asList(
						"מפגש " , "שיעור תורה" , "הרצאה" , "טיול יומי", "ערב משחקים", "מסיבה",  "מאטצ'-אפ", "סדנה" , "נופש", "סופש" , "ספידייט" ,  "הופעה" 
						
						)
				); 
		
		List<Integer> list3 = new ArrayList<Integer>(
				Arrays.asList(
						1,2,3
						
						)
				); 
		System.out.println(e);
		
		EventPropertyMutiValues e2 = new EventPropertyMutiValues("ev2", list2);
		System.out.println(e2);
		EventPropertySingleValue e3 = new EventPropertySingleValue("name", "Avraham");
		System.out.println(e3);
		
        
        EventPropertySingleValue e4 = new EventPropertySingleValue("isOrganizer", "false");
		System.out.println(e4);
        
		System.out.println("Test EventPropsGenerator");
		EventPropertiesGenerator eventPropertiesGenerator = new EventPropertiesGenerator();
		EventPropertiesGenerator.generateList();
		EventProperties e5 = new EventProperties(EventPropertiesGenerator.generateList());	
		System.out.println(e5);
		SocialEvent e6 = new SocialEvent("ddd","dddxxx",  2, 5, e5);
		System.out.println(e6);
		
		
		System.out.println("Example of running");
		
		List<SocialEvent> allEvents = new ArrayList<SocialEvent>(); 
		allEvents.add(e6);
		e3 = new EventPropertySingleValue("name", "Lingar");
		e5.updateData(e3);
		
		e6 = new SocialEvent("b", "xzc", 44, 55, e5);
		allEvents.add(e6);
		e3 = new EventPropertySingleValue("isPublisher", "false");
		e5.updateData(e3);
		e6 = new SocialEvent("C", "ZZZ", 44, 55, e5);
		allEvents.add(e6);
		
		System.out.println("All Social Events : \n" + allEvents);
		
		

        
        
	}

}
