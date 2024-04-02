package com.jitendra.service;

import org.springframework.stereotype.Service;

import com.jitendra.model.CustomerCart;
@Service
public interface CustomerCartService {

	public CustomerCart save(CustomerCart customerCart) ;
	public CustomerCart find(int id) ;
}
