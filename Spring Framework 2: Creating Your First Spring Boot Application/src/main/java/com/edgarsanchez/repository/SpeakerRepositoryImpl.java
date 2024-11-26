package com.edgarsanchez.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.edgarsanchez.model.Speaker;

@Repository
public class SpeakerRepositoryImpl implements SpeakerRepository {
	
	@Override
	public List<Speaker> findAll(){
		List<Speaker> speakers = new ArrayList<>();
		speakers.add(new Speaker("Edgar","Sanchez"));
		return speakers;
	}
}
