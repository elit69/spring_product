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

import com.lit.app.entities.Product;
import com.lit.app.entities.User;
import com.lit.app.repo.ProductDao;

@Repository("ProductDaoImpl")
public class ProductDaoImpl implements ProductDao {

	@Autowired
	public DataSource dataSource;

	@Override
	public List<Product> list() {
		String sql = "SELECT * from v_list_product";
		try (Connection cnn = dataSource.getConnection(); 
			PreparedStatement ps = cnn.prepareStatement(sql);) {

			ResultSet rs = ps.executeQuery();
			System.out.println(ps);
			ArrayList<Product> arr = new ArrayList<Product>();
			Product a = null;
			while (rs.next()) {
				a = new Product();
				a.setId(rs.getInt("id"));
				a.setName(rs.getString("name"));
				a.setPrice(rs.getFloat("price"));
				a.setStock(rs.getInt("stock"));
				a.setCdate(rs.getDate("created_date"));
				a.setUdate(rs.getDate("updated_date"));
				a.setCby(new User(rs.getInt("created_by"), rs.getString("username_creater")));
				a.setUby(new User(rs.getInt("updated_by"), rs.getString("username_updater")));
				arr.add(a);
			}
			return arr;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean delete(int id) {
		String sql = "DELETE FROM tbproduct WHERE id = ?";
		try (Connection cnn = dataSource.getConnection(); 
			PreparedStatement ps = cnn.prepareStatement(sql);) {
			
			ps.setInt(1, id);
			if (ps.executeUpdate() > 0) {
				System.out.println(ps);
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
