package com.edgarsanchez;

import com.edgarsanchez.service.SpeakerServiceImpl;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.edgarsanchez.service.SpeakerService;

public class Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext appContext = new AnnotationConfigApplicationContext(AppConfig.class);
		SpeakerService speakerSvc;
		speakerSvc = appContext.getBean("speakerSvc",SpeakerService.class);
		
		System.out.println(speakerSvc.findAll().get(0).getFirstName());
	}

}
