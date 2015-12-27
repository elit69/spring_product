package com.lit.app.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.lit.app.entities.Product;
import com.lit.app.repo.ProductDao;
import com.lit.app.services.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired 
	@Qualifier("ProductDaoImpl")
	ProductDao productDao;

	@Override
	public List<Product> list() {
		return productDao.list();
	}

	@Override
	public boolean delete(int id) {
		return productDao.delete(id);
	}
}
