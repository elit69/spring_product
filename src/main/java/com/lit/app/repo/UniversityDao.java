package com.lit.app.repo;

import java.util.List;

import com.lit.app.entities.University;

public interface UniversityDao {
	
	public List<University> findAllUniversityByName(String universityName);

}
