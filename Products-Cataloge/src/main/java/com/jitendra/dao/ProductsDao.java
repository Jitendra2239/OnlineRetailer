package com.jitendra.dao;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.jitendra.model.Products;
@Repository
public interface ProductsDao extends MongoRepository<Products, Integer>{

}
