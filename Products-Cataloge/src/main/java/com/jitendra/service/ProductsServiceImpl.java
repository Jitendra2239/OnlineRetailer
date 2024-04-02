package com.jitendra.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jitendra.dao.ProductsDao;
import com.jitendra.exception.ProductNotFoundException;
import com.jitendra.model.Products;
@Service
public class ProductsServiceImpl implements ProductsService {
   @Autowired 
   ProductsDao productsDao;
	@Override
	public Products addProducts(Products products) {
		// TODO Auto-generated method stub
		Products products1=productsDao.save(products);
		return products1;
	}

	@Override
	public String deletProducts(int id) {
		// TODO Auto-generated method stub
		productsDao.deleteById(id);
		return "deleted";
	}

	@Override
	public Products searchProducts(int id) {
		// TODO Auto-generated method stub
		Products products1=productsDao.findById(id).orElseThrow(()->new ProductNotFoundException("Procuct not found!"));
		return products1;
	}

	@Override
	public Products updateProducts(Products products) {
		// TODO Auto-generated method stub
		Products products1=productsDao.save(products);
		return products1;
	}

}
