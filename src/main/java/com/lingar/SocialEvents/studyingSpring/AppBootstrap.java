package com.lingar.SocialEvents.studyingSpring;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.lingar.SocialEvents.SocialEventsTrying1Application;

@SpringBootApplication
public class AppBootstrap {
    public static void main(String[] args) {
		SpringApplication.run(AppBootstrap.class, args);

    	System.out.println("??");
        //JavaConfigApplicationContext ctx = new JavaConfigApplicationContext(StudyingBean.class);
    	ApplicationContext context = new AnnotationConfigApplicationContext(StudyingBean.class);
        context.getBean("saySomething");
        System.out.println("The context is printing... when it's created. The get Bean don't print nothing. ");
        //Object x = context.getBean("saySomething2");
    	ApplicationContext context2 = new AnnotationConfigApplicationContext(OnlyBean.class);
    	context2.getBean("amm");
    

       //Service service = ctx.getBean(Service.class);
       // service.doSomething();
    }
}