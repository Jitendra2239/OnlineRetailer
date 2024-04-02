package com.jitendra.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.jitendra.model.Customer;
import com.jitendra.model.CustomerAddress;
@Service
public interface CustomerService {
 public Customer addCustomer(Customer customer);
 public String deleteCustomer(int id);
 public Customer  searchCustomer(int id);
 public Customer updateCustomer(Customer customer);
// public CustomerAddress addCustomerAddress(CustomerAddress customerAddress);
// public String deleteCustomerAddres(int id);
 public List<CustomerAddress> searchCustomerAddres(int id);
 public List<CustomerAddress> searchCustomerAddres1(int id);
// public CustomerAddress  updateCustomerAddres(CustomerAddress customerAddress);
 public List<Customer> getCustomer();
}
