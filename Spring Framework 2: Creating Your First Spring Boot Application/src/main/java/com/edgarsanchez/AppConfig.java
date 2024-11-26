package com.edgarsanchez;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.edgarsanchez.repository.SpeakerRepositoryImpl;
import com.edgarsanchez.service.SpeakerService;
import com.edgarsanchez.service.SpeakerServiceImpl;
import com.edgarsanchez.repository.SpeakerRepository;

@Configuration
@ComponentScan({"com.edgarsanchez"})
public class AppConfig {
	
//	@Bean(name = "speakerSvc")
//	public SpeakerService getSpeakerService() {
//		//return new SpeakerServiceImpl(getSpeakerRepository());
//		return new SpeakerServiceImpl();
//	}
//		
//	@Bean(name = "speakerRepo")
//	public SpeakerRepository getSpeakerRepository() {
//		return new SpeakerRepositoryImpl();
//	}
	

}
