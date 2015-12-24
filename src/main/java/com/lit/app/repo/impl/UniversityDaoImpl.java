package com.lit.app.repo.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lit.app.entities.University;
import com.lit.app.repo.UniversityDao;

@Repository("UniversityDaoImpl")
/*
 * repositories are the lowest layer, responsible for all logic related to
 * saving data to a data store and retrieving saved data from a data store.
 */
public class UniversityDaoImpl implements UniversityDao{
	
	@Autowired
	DataSource dataSoruce;
	
	 @Override
	public List<University> findAllUniversityByName(String universityName) {
			String sql = "SELECT * FROM university WHERE uni_name like ?";
		try(
				Connection cnn = dataSoruce.getConnection();
				PreparedStatement ps = cnn.prepareStatement(sql);
		){
			ps.setString(1, "%"+universityName+"%");
			ResultSet rs = ps.executeQuery();
			List<University> list = new ArrayList<University>();
			University dto = null;
			while(rs.next()){
				dto = new University();
				dto.setUniversityId(rs.getInt(1));
				dto.setUniversityName(rs.getString(2));
				list.add(dto);
			}
			return list;
		}catch(SQLException e){
			e.printStackTrace();
		}
		 
		 return null;
	}
	

}
