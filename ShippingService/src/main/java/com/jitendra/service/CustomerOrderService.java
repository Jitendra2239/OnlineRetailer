package com.jitendra.service;

import org.springframework.stereotype.Service;

import com.jitendra.model.CustomerOrder;
@Service
public interface CustomerOrderService {

	public CustomerOrder save(CustomerOrder  customerOrder);
	public CustomerOrder find(int id);
    public CustomerOrder findCustomerOrder(int id);
}
