package com.jitendra.service;

import org.springframework.stereotype.Service;

import com.jitendra.model.Products;
@Service
public interface ProductsService {
public Products addProducts(Products products);
public String deletProducts(int id);
public Products searchProducts(int id);
public Products  updateProducts(Products products);
}
