package com.lit.app.repo;

import java.util.List;

import com.lit.app.entities.Product;

public interface ProductDao {
	public List<Product> list();
	public boolean delete(int id);
}
