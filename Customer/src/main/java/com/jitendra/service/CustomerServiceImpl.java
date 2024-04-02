package com.jitendra.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.jitendra.dao.CustomerRepo;
import com.jitendra.exception.CustomerAddressNotFoundException;
import com.jitendra.exception.CustomerNotFoundException;
import com.jitendra.model.Customer;
import com.jitendra.model.CustomerAddress;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepo repository;


	@Override
	public Customer addCustomer(Customer customer) {
		// TODO Auto-generated method stub
		return repository.save(customer);
	}

	@Override
	public String deleteCustomer(int id) {
		// TODO Auto-generated method stub
		repository.deleteById(id);
		return null;
	}

	@Override
	public Customer searchCustomer(int id) {
		// TODO Auto-generated method stub
		return repository.findById(id).orElse(null);

	}

	@Override
	public Customer updateCustomer(Customer customer) {
		Customer customer1=repository.save(customer);
		return customer1;
	}

	@Override
	public List<CustomerAddress > searchCustomerAddres(int id) {
		// TODO Auto-generated method stub
		Customer custo=repository.findById(id).get();
		List<CustomerAddress> addr=new ArrayList<>();
		for(CustomerAddress addrs:custo.getCustomerBillingAddres()) {
	     addr.add(addrs);
		}
		return addr;
	}

	@Override
	public List<CustomerAddress > searchCustomerAddres1(int id) {
		// TODO Auto-generated method stub
		Customer custo=repository.findById(id).get();
		List<CustomerAddress> addr=new ArrayList<>();
		for(CustomerAddress addrs:custo.getCustomerShipingAddres()) {
	     addr.add(addrs);
		}
		return addr;
	}

	@Override
	public List<Customer> getCustomer() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

}
