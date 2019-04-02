package com.lingar.SocialEvents.studyingSpring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudyingBean {
	
	
	
	@Bean
	public static void saySomething(){
		System.out.println("Hey, I am method of bean");
	}
	
	@Bean
	public static void saySomething2(){
		System.out.println("It's getting automatically ? - NOt think");
	}
	
	@Bean
	public static void saySomething3(){
		System.out.println("It's It on the upload running all this class automatically. Apperantly because it's belong to configuration");
	}
	
	
	
}
