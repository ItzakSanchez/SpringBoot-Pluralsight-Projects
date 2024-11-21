package com.edgarsanchez.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edgarsanchez.model.Speaker;
import com.edgarsanchez.repository.SpeakerRepository;
import com.edgarsanchez.repository.SpeakerRepositoryImpl;

@Service("speakerSvc")
public class SpeakerServiceImpl implements SpeakerService{

	private SpeakerRepository speakerRepository;
	
	public SpeakerServiceImpl() {
		super();
	}

	public SpeakerServiceImpl(SpeakerRepository speakerRepository) {
		this.speakerRepository = speakerRepository;
	}
	
	
	public SpeakerRepository getSpeakerRepository() {
		return speakerRepository;
	}

	
	@Autowired
	public void setSpeakerRepository(SpeakerRepository speakerRepository) {
		this.speakerRepository = speakerRepository;
	}

	@Override
	public List<Speaker> findAll(){
		return speakerRepository.findAll();
	}
}
