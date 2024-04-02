package com.jitendra.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jitendra.dao.CustomerCartRepo;
import com.jitendra.model.CustomerCart;
@Service
public class CustomerCartServiceImpl implements CustomerCartService {
    @Autowired
    CustomerCartRepo repo;
	@Override
	public CustomerCart save(CustomerCart customerCart) {
		// TODO Auto-generated method stub
		return repo.save(customerCart);
	}
	@Override
	public CustomerCart find(int id) {
		// TODO Auto-generated method stub
		return repo.findById(id).orElse(null);
	}

}
