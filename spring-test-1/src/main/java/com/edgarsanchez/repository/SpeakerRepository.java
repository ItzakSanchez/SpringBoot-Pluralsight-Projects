package com.edgarsanchez.repository;

import java.util.List;

import com.edgarsanchez.model.Speaker;

public interface SpeakerRepository {

	List<Speaker> findAll();
}
