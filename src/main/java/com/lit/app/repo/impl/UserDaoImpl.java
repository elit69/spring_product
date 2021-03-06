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

import com.lit.app.entities.User;
import com.lit.app.entities.UserRole;
import com.lit.app.repo.UserDao;

@Repository("UserDaoImpl")
public class UserDaoImpl implements UserDao {

	@Autowired
	DataSource dataSource;
	
	@Override
	public User findUserByUserName(String username) {
		String sql = "SELECT id, username, password, email, enabled , position, approved_by, approved_date, created_date, "
				+ "created_by , updated_by, updated_date, locked FROM tbuser WHERE username = ?";
		try (Connection cnn = dataSource.getConnection(); PreparedStatement ps = cnn.prepareStatement(sql);) {
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setEmail(rs.getString("email"));
				user.setPosition(rs.getString("position"));
				user.setApprovedDate(rs.getDate("approved_date"));
				user.setApprovedBy(rs.getInt("approved_by"));
				user.setCreatedBy(rs.getInt("created_by"));
				user.setCreatedDate(rs.getDate("created_date"));
				user.setEnabled(rs.getBoolean("enabled"));
				user.setAccountNonLocked(rs.getBoolean("locked"));
				user.setRoles(this.findUserRoleByUserId(user.getId()));
				return user;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public List<UserRole> findUserRoleByUserId(int id) {
		List<UserRole> roles = new ArrayList<UserRole>();
		String sql = "SELECT tbrole.id , tbrole.role_name FROM tbuser "
				+ "LEFT JOIN tb_user_role ON tbuser.id = tb_user_role.user_id "
				+ "LEFT JOIN tbrole ON tbrole.id = tb_user_role.role_id WHERE tbuser.id = ?";
		try (Connection cnn = dataSource.getConnection(); PreparedStatement ps = cnn.prepareStatement(sql);) {
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				UserRole role = new UserRole();
				role.setId(rs.getInt("id"));
				role.setName("ROLE_" + rs.getString("role_name"));
				roles.add(role);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return roles;
	}

	@Override
	public User loadUserById(int id) {
		String sql = "SELECT id, username, password, email, enabled , position, approved_by, approved_date, created_date, "
				+ "created_by , updated_by, updated_date, locked FROM tbuser WHERE id = ?";
		try (Connection cnn = dataSource.getConnection(); PreparedStatement ps = cnn.prepareStatement(sql);) {
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setEmail(rs.getString("email"));
				user.setPosition(rs.getString("position"));
				user.setApprovedDate(rs.getDate("approved_date"));
				user.setApprovedBy(rs.getInt("approved_by"));
				user.setCreatedBy(rs.getInt("created_by"));
				user.setCreatedDate(rs.getDate("created_date"));
				user.setEnabled(rs.getBoolean("enabled"));
				user.setAccountNonLocked(rs.getBoolean("locked"));
				user.setRoles(this.findUserRoleByUserId(user.getId()));
				return user;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}


}
