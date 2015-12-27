package com.lit.app.services;

import java.util.List;

import com.lit.app.entities.Product;

public interface ProductService {
	public List<Product> list();
	public boolean delete(int id);
}
