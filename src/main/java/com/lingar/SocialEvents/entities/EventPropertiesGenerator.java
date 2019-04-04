package com.lingar.SocialEvents.entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class EventPropertiesGenerator {
	
	public static Map<String, EventProperty> generateList(){
		Map<String, EventProperty> propsList = new LinkedHashMap<String, EventProperty>();
		EventProperty ev;
		List <EventProperty > allEvsProps = new ArrayList<EventProperty>();
		//ev = new EventPropertyMutiValues(name, values)
		//ev = new EventPropertySingleValue(name, values)
		List<String> list2 = new ArrayList<String>(
				Arrays.asList(
						"מפגש " , "שיעור תורה" , "הרצאה" , "טיול יומי", "ערב משחקים", "מסיבה",  "מאטצ'-אפ", "סדנה" , "נופש", "סופש" , "ספידייט" ,  "הופעה" 
						
						)
				);
		ev = new EventPropertySingleValue("name", "na");
		allEvsProps.add(ev);
		ev = new EventPropertySingleValue("isOrganizer", true);
		allEvsProps.add(ev);
		ev = new EventPropertySingleValue("publisherName", "Avraham");
		allEvsProps.add(ev);
		
		ev = new EventPropertySingleValue("date", new Date());
		allEvsProps.add(ev);
		
		ev = new EventPropertyMutiValues("eventType", list2);
		allEvsProps.add(ev);
		
		List <Integer >list3 = new ArrayList<Integer>(
				Arrays.asList(
						20,30
						)
				);
		
		
		ev = new EventPropertyMutiValues("time", list3);
		allEvsProps.add(ev);
		
		list2 = new ArrayList<String>(
				Arrays.asList(
						"NA","NA","NA"
						
						)
				);
		
		ev = new EventPropertyMutiValues("contactDetails", list2);
		allEvsProps.add(ev);
		
		list2 = new ArrayList<String>(
				Arrays.asList(
						"ספונטנית", "דרך גורם מקשר",  "יתבצע רישום אצל אחראי",  "מפגשים אישיים בפעילות"
						
						)
				);
		ev = new EventPropertyMutiValues("matchingIdea", list2);
		allEvsProps.add(ev);
		
		System.out.println(allEvsProps);
		//insert all to map 
		for (EventProperty e: allEvsProps){
			propsList.put(e.getName(), e);
		}
		System.out.println(propsList);
		
		propsList.put("publisherName", new EventPropertySingleValue("publisherName", "Izhark"));
		propsList.put("publisherName22", new EventPropertySingleValue("amm", "Sleep"));
		
		return propsList;
		
	}

}
