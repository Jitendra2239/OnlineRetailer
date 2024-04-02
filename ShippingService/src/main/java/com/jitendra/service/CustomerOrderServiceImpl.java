package com.jitendra.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jitendra.dao.CustomerOrderRepo;
import com.jitendra.model.CustomerOrder;
@Service
public class CustomerOrderServiceImpl  implements CustomerOrderService{
   @Autowired
   private CustomerOrderRepo repo;
	@Override
	public CustomerOrder save(CustomerOrder customerOrder) {
		// TODO Auto-generated method stub
		return repo.save(customerOrder);
	}

	@Override
	public CustomerOrder find(int id) {
		// TODO Auto-generated method stub
		return repo.findById(id).orElse(null);
	}

	@Override
	public CustomerOrder findCustomerOrder(int id) {
		// TODO Auto-generated method stub
		return repo.findByCustomerId(id);
	}

}
