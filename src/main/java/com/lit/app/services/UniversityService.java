package com.lit.app.services;

import java.util.List;

import com.lit.app.entities.University;

public interface UniversityService {

	public List<University> findAllUniversityByName(String universityName);
	
}
