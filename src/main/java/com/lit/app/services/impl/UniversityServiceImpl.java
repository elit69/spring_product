package com.lit.app.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.lit.app.entities.University;
import com.lit.app.repo.UniversityDao;
import com.lit.app.services.UniversityService;

@Service
/*
 * Services are the next layer above repositories. Services encapsulate the
 * business logic of the application and consume other services and repositories
 */
public class UniversityServiceImpl implements UniversityService{

	@Autowired
	@Qualifier("UniversityDaoImpl")
	UniversityDao universityDao;
	
	@Override
	public List<University> findAllUniversityByName(String universityName) {
		return universityDao.findAllUniversityByName(universityName);
	}
	
}
