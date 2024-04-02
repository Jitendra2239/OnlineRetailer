package com.jitendra.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jitendra.dto.CustomeResponse;
import com.jitendra.dto.CustomerAddressResponse;
import com.jitendra.exception.BadRequestException;
import com.jitendra.exception.CustomerAddressNotFoundException;
import com.jitendra.exception.CustomerNotFoundException;
import com.jitendra.exception.HttpStatusExceptionHandler;
import com.jitendra.model.Customer;
import com.jitendra.model.CustomerAddress;
import com.jitendra.service.CustomerService;
@RestController
@RequestMapping("/api")
public class CustomerController {
	@Autowired
	private CustomerService customerService;
	@GetMapping("/hello/{id}")
	public Customer hello(@PathVariable int id) {
	Customer customer1 = customerService.searchCustomer(id);
	  return customer1 ;	
	}
@PostMapping("/customer")
	public ResponseEntity<CustomeResponse> addCustomer(@RequestBody Customer customer) {
		// TODO Auto-generated method stub
		if (customer.getCustomerName() == null || customer.getCustomerName().isEmpty()) {
			throw new BadRequestException("Add Customer Name");
		}
		Customer customer2 = new Customer();
	
		customer2.setCustomerEmail(customer.getCustomerEmail());
		customer2.setCustomerId(customer.getCustomerId());
		customer2.setCustomerName(customer.getCustomerName());
		List<CustomerAddress> list1 = new ArrayList<>();
		for (CustomerAddress add : customer.getCustomerBillingAddres()) {
			CustomerAddress add1 = new CustomerAddress();
			add1.setCity(add.getCity());

			add1.setDoorNo(add.getDoorNo());
			add1.setPinCode(add.getPinCode());
			add1.setStreetName(add.getStreetName());
			add1.setLayout(add.getLayout());

		      list1.add(add1);
		}
		customer2.setCustomerBillingAddres(list1);
		List<CustomerAddress> list2 = new ArrayList<>();
		for (CustomerAddress add : customer.getCustomerShipingAddres()) {
			CustomerAddress add1 = new CustomerAddress();
			add1.setCity(add.getCity());

			add1.setDoorNo(add.getDoorNo());
			add1.setPinCode(add.getPinCode());
			add1.setStreetName(add.getStreetName());
			add1.setLayout(add.getLayout());

			list2.add(add1);
		}
		customer2.setCustomerShipingAddres(list2);
		Customer customer1 = customerService.addCustomer(customer2);
		CustomeResponse response = new CustomeResponse();

		List<CustomerAddressResponse> list = new ArrayList<>();
		CustomerAddressResponse response1 = new CustomerAddressResponse();
		for (CustomerAddress add : customer1.getCustomerBillingAddres()) {
			
			response1.setCity(add.getCity());

			response1.setDoorNo(add.getDoorNo());
			response1.setPinCode(add.getPinCode());
			response1.setStreetName(add.getStreetName());
			response1.setLayout(add.getLayout());

			list.add(response1);
		}
		response.setCustomerBillingAddres(list);
		List<CustomerAddressResponse> list3 = new ArrayList<>();
		CustomerAddressResponse response2 = new CustomerAddressResponse();
		for (CustomerAddress add : customer1.getCustomerShipingAddres()) {
			
			response2.setCity(add.getCity());

			response2.setDoorNo(add.getDoorNo());
			response2.setPinCode(add.getPinCode());
			response2.setStreetName(add.getStreetName());
			response2.setLayout(add.getLayout());

			list3.add(response2);
		}
		response.setCustomerShipingAddres(list3);

		response.setCustomerEmail(customer1.getCustomerEmail());
		response.setCustomerId(customer1.getCustomerId());
		response.setCustomerName(customer1.getCustomerName());

		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@DeleteMapping("/deletecustomer/{id}")
	 public ResponseEntity<String> deleteCustomer(@PathVariable int id) {
		// TODO Auto-generated method stub
	    	Customer customer = customerService.searchCustomer(id);
	  	if (customer == null) {
			throw new CustomerNotFoundException("Customer not exists with this Id!");
		}
		  customerService.deleteCustomer(id);
   
		return new ResponseEntity<>("Customer with id" + " " + id + " " + "Deleted", HttpStatus.OK);
	}
	@GetMapping("/customer/{id}")
	public ResponseEntity<CustomeResponse> searchCustomer(@PathVariable int id) {
		// TODO Auto-generated method stub
		Customer customer1 = customerService.searchCustomer(id);
		if (customer1 == null) {
			throw new CustomerNotFoundException("Customer not exists with this Id!");
		}
		CustomeResponse response = new CustomeResponse();

		List<CustomerAddressResponse> list = new ArrayList<>();

		for (CustomerAddress add : customer1.getCustomerBillingAddres()) {
			CustomerAddressResponse response1 = new CustomerAddressResponse();
			response1.setCity(add.getCity());

			response1.setDoorNo(add.getDoorNo());
			response1.setPinCode(add.getPinCode());
			response1.setStreetName(add.getStreetName());
			response1.setLayout(add.getLayout());

			list.add(response1);
		}
		response.setCustomerBillingAddres(list);

		for (CustomerAddress add : customer1.getCustomerShipingAddres()) {
			CustomerAddressResponse response1 = new CustomerAddressResponse();
			response1.setCity(add.getCity());

			response1.setDoorNo(add.getDoorNo());
			response1.setPinCode(add.getPinCode());
			response1.setStreetName(add.getStreetName());
			response1.setLayout(add.getLayout());

			list.add(response1);
		}
		response.setCustomerEmail(customer1.getCustomerEmail());
		response.setCustomerId(customer1.getCustomerId());
		response.setCustomerName(customer1.getCustomerName());

		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@PatchMapping("/updateCustomer/{id}")
	public ResponseEntity<CustomeResponse> updateCustomer(@PathVariable int id, @RequestBody Customer customer) {
		// TODO Auto-generated method stub

		Customer customer1 = customerService.searchCustomer(id);
		if (customer1 == null) {
			throw new CustomerNotFoundException("Customer not exists with this Id!");
		}
		List<CustomerAddress> list1 = new ArrayList<>();
		for (CustomerAddress add : customer.getCustomerBillingAddres()) {
			CustomerAddress add1 = new CustomerAddress();
			add1.setCity(add.getCity());

			add1.setDoorNo(add.getDoorNo());
			add1.setPinCode(add.getPinCode());
			add1.setStreetName(add.getStreetName());
			add1.setLayout(add.getLayout());

			list1.add(add1);
		}
		customer1.setCustomerBillingAddres(list1);
		List<CustomerAddress> list2 = new ArrayList<>();
		for (CustomerAddress add : customer.getCustomerShipingAddres()) {
			CustomerAddress add1 = new CustomerAddress();
			add1.setCity(add.getCity());

			add1.setDoorNo(add.getDoorNo());
			add1.setPinCode(add.getPinCode());
			add1.setStreetName(add.getStreetName());
			add1.setLayout(add.getLayout());

			list2.add(add1);
		}
		customer1.setCustomerShipingAddres(list2);
	
		customer1.setCustomerEmail(customer.getCustomerEmail());
	
	    customer1.setCustomerId(id);
		customer1.setCustomerName(customer.getCustomerName());

		Customer customer2 = customerService.updateCustomer(customer1);
		CustomeResponse response = new CustomeResponse();

		List<CustomerAddressResponse> list = new ArrayList<>();

		for (CustomerAddress add : customer1.getCustomerBillingAddres()) {
			CustomerAddressResponse response1 = new CustomerAddressResponse();
			response1.setCity(add.getCity());

			response1.setDoorNo(add.getDoorNo());
			response1.setPinCode(add.getPinCode());
			response1.setStreetName(add.getStreetName());
			response1.setLayout(add.getLayout());

			list.add(response1);
		}
		response.setCustomerBillingAddres(list);

		for (CustomerAddress add : customer1.getCustomerShipingAddres()) {
			CustomerAddressResponse response1 = new CustomerAddressResponse();
			response1.setCity(add.getCity());

			response1.setDoorNo(add.getDoorNo());
			response1.setPinCode(add.getPinCode());
			response1.setStreetName(add.getStreetName());
			response1.setLayout(add.getLayout());

			list.add(response1);
		}
		response.setCustomerEmail(customer1.getCustomerEmail());
		response.setCustomerId(customer1.getCustomerId());
		response.setCustomerName(customer1.getCustomerName());
	
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/addCustomerbillingAddress/{id}")
	public ResponseEntity<CustomerAddressResponse> addCustomerAddress(@PathVariable int id,@RequestBody CustomerAddress customerAddress) {
		// TODO Auto-generated method stub
		Customer customer1 = customerService.searchCustomer(id);
		if (customer1 == null) {
			throw new CustomerNotFoundException("Customer not exists with this Id!");
		}
		else if (customerAddress == null) {
			throw new BadRequestException("Add Customer Address");
		}
		List<CustomerAddress> list1=new ArrayList<>();
	
        if(customer1.getCustomerBillingAddres()!=null) {
		list1=customer1.getCustomerBillingAddres();
        }
        list1.add(customerAddress);
		customer1.setCustomerBillingAddres(list1);
		Customer customer=customerService.updateCustomer(customer1);

		List<CustomerAddress> customerAddress1 =  customer.getCustomerBillingAddres();
       Iterator<CustomerAddress>itr=customerAddress1.iterator();
		CustomerAddressResponse response1 = new CustomerAddressResponse();
		while(itr.hasNext()) {
			CustomerAddress add=itr.next();
			if(add.getCity()==customerAddress.getCity() && add.getDoorNo()==customerAddress.getDoorNo()&& add.getLayout()
					==customerAddress.getLayout() && add.getPinCode()==customerAddress.getPinCode()) {
		
		response1.setCity(add.getCity());
		response1.setCustomerId(id);
		response1.setDoorNo(add.getDoorNo());
		response1.setPinCode(add.getPinCode());
		response1.setStreetName(add.getStreetName());
		response1.setLayout(add.getLayout());
			}
		}
		return new ResponseEntity<>(response1, HttpStatus.CREATED);
			
	}

	@GetMapping("/searchCustomerbillingAddress/{id}")
	public ResponseEntity<List<CustomerAddress>> searchCustomerAddress(@PathVariable int id) {
		// TODO Auto-generated method stub
		List<CustomerAddress>  customerAddress = customerService.searchCustomerAddres(id);
		if (customerAddress == null) {
			throw new CustomerAddressNotFoundException("CustomerAddress not exists with this Id!");
		}
		CustomerAddress address=new CustomerAddress();
	
			List<CustomerAddressResponse> response1=new ArrayList<>();
			CustomerAddressResponse response =new CustomerAddressResponse();
			Iterator<CustomerAddress> itr=customerAddress.iterator();
			while(itr.hasNext() ) {
				CustomerAddress sss=itr.next();
				
			response.setCity(sss.getCity());
			response.setCustomerId(id);
			response.setDoorNo(sss.getDoorNo());
			response.setPinCode(sss.getPinCode());
			response.setStreetName(sss.getStreetName());
			response.setLayout(sss.getLayout());
			response1.add(response);
			}
		
	

		return new ResponseEntity<>(customerAddress, HttpStatus.OK);

	}

	@PutMapping("/updateCustomerbillingAddress/{id}")
	public ResponseEntity<CustomerAddressResponse> updateCustomerAddress(@PathVariable int id, @RequestBody CustomerAddress customerAddress) {
		// TODO Auto-generated method stub


		Customer customer= customerService.searchCustomer(id);
		if (customer == null) {
			throw new CustomerAddressNotFoundException("CustomerAddress not exists with this Id!");
		}
		List<CustomerAddress>  customerAddress1=customer.getCustomerBillingAddres();

		List<CustomerAddress>ss=new ArrayList<>();
		 
		Iterator<CustomerAddress> itr=customerAddress1.iterator();
		while(itr.hasNext() ) {
			CustomerAddress sss=itr.next();
			if(sss.getCity().equals(customerAddress.getCity()) ||sss.getPinCode()==customerAddress.getPinCode()
					||sss.getLayout().equals(customerAddress.getLayout()) ||sss.getStreetName().equals(customerAddress.getStreetName())
					||sss.getDoorNo()==customerAddress.getDoorNo()) {
				sss.setCity(customerAddress.getCity());
				sss.setDoorNo(customerAddress.getDoorNo());
				sss.setLayout(customerAddress.getLayout());
				sss.setPinCode(customerAddress.getPinCode());
				sss.setStreetName(customerAddress.getStreetName());
			}
		   ss.add(sss);
		}
          customer.setCustomerBillingAddres(ss);
		Customer customer2 =customerService.updateCustomer(customer);
	     CustomerAddressResponse response1=new CustomerAddressResponse();
				
	     List<CustomerAddressResponse> list = new ArrayList<>();

			for (CustomerAddress add : customer2.getCustomerBillingAddres()) {
				if(add.getCity()==customerAddress.getCity()&& add.getPinCode()==customerAddress.getPinCode()
						&&add.getLayout()==customerAddress.getLayout() &&add.getStreetName()==customerAddress.getStreetName()
								&&add.getDoorNo()==customerAddress.getDoorNo()) {
				response1.setCity(add.getCity());

				response1.setDoorNo(add.getDoorNo());
				response1.setPinCode(add.getPinCode());
				response1.setStreetName(add.getStreetName());
				response1.setLayout(add.getLayout());

			
			}
			}
			
			return new ResponseEntity<>(response1, HttpStatus.OK);
			
	}
	@DeleteMapping("/deleteCustomerbillingAddress/{id}")
	public ResponseEntity<String> deleteCustomerAddress(@PathVariable int id) {
		Customer customer= customerService.searchCustomer(id);
		   
		   customer.setCustomerBillingAddres(null);
	    String s="some issue with deleting Address";
		Customer cus=      customerService.updateCustomer(customer);
		       if(cus.getCustomerBillingAddres()==null) {
		    	   s="Customer Address deleted!";
		       }
		    	
		       return new ResponseEntity<>(s, HttpStatus.OK);
		
	}
	@PostMapping("/addCustomershippingAddress/{id}")
	public ResponseEntity<CustomerAddressResponse> addCustomerAddress1(@PathVariable int id,@RequestBody CustomerAddress customerAddress) {
		// TODO Auto-generated method stub
		Customer customer1 = customerService.searchCustomer(id);
		if (customer1 == null) {
			throw new CustomerNotFoundException("Customer not exists with this Id!");
		}
		else if (customerAddress == null) {
			throw new BadRequestException("Add Customer Address");
		}

		List<CustomerAddress> list1=new ArrayList<>();
		
        if(customer1.getCustomerShipingAddres()!=null) {
		list1=customer1.getCustomerShipingAddres();
        }
        list1.add(customerAddress);
		customer1.setCustomerShipingAddres(list1);
		Customer customer=customerService.updateCustomer(customer1);

		List<CustomerAddress> customerAddress1 =  customer.getCustomerShipingAddres();
       Iterator<CustomerAddress>itr=customerAddress1.iterator();
		CustomerAddressResponse response1 = new CustomerAddressResponse();
		while(itr.hasNext()) {
			CustomerAddress add=itr.next();
			if(add.getCity()==customerAddress.getCity() && add.getDoorNo()==customerAddress.getDoorNo()&& add.getLayout()
					==customerAddress.getLayout() && add.getPinCode()==customerAddress.getPinCode()) {
		
		response1.setCity(add.getCity());
		response1.setCustomerId(id);
		response1.setDoorNo(add.getDoorNo());
		response1.setPinCode(add.getPinCode());
		response1.setStreetName(add.getStreetName());
		response1.setLayout(add.getLayout());
			}
		}
		return new ResponseEntity<>(response1, HttpStatus.CREATED);
			
	}

	@GetMapping("/searchCustomershippingAddress/{id}")
	public ResponseEntity<List<CustomerAddress>> searchCustomerAddress1(@PathVariable int id) {
		// TODO Auto-generated method stub
		List<CustomerAddress>  customerAddress = customerService.searchCustomerAddres1(id);
		if (customerAddress == null) {
			throw new CustomerAddressNotFoundException("CustomerAddress not exists with this Id!");
		}
		CustomerAddress address=new CustomerAddress();
	
			List<CustomerAddressResponse> response1=new ArrayList<>();
			CustomerAddressResponse response =new CustomerAddressResponse();
			Iterator<CustomerAddress> itr=customerAddress.iterator();
			while(itr.hasNext() ) {
				CustomerAddress sss=itr.next();
				
			response.setCity(sss.getCity());
			response.setCustomerId(id);
			response.setDoorNo(sss.getDoorNo());
			response.setPinCode(sss.getPinCode());
			response.setStreetName(sss.getStreetName());
			response.setLayout(sss.getLayout());
			response1.add(response);
			}
		
	

		return new ResponseEntity<>(customerAddress, HttpStatus.OK);

	}

	@PutMapping("/updateCustomershippingAddress/{id}")
	public ResponseEntity<CustomerAddressResponse> updateCustomerAddress1(@PathVariable int id, @RequestBody CustomerAddress customerAddress) {
		// TODO Auto-generated method stub


		Customer customer= customerService.searchCustomer(id);
		if (customer == null) {
			throw new CustomerAddressNotFoundException("CustomerAddress not exists with this Id!");
		}
		List<CustomerAddress>  customerAddress1=customer.getCustomerShipingAddres();

		List<CustomerAddress>ss=new ArrayList<>();
		 
		Iterator<CustomerAddress> itr=customerAddress1.iterator();
		while(itr.hasNext() ) {
			CustomerAddress sss=itr.next();
			if(sss.getCity().equals(customerAddress.getCity()) ||sss.getPinCode()==customerAddress.getPinCode()
					||sss.getLayout().equals(customerAddress.getLayout()) ||sss.getStreetName().equals(customerAddress.getStreetName())
					||sss.getDoorNo()==customerAddress.getDoorNo()){
				sss.setCity(customerAddress.getCity());
				sss.setDoorNo(customerAddress.getDoorNo());
				sss.setLayout(customerAddress.getLayout());
				sss.setPinCode(customerAddress.getPinCode());
				sss.setStreetName(customerAddress.getStreetName());
			}
		   ss.add(sss);
		}

		Customer customer2 =customerService.updateCustomer(customer);
	     CustomerAddressResponse response1=new CustomerAddressResponse();
				
	     List<CustomerAddressResponse> list = new ArrayList<>();

			for (CustomerAddress add : customer2.getCustomerShipingAddres()) {
				if(add.getCity()==customerAddress.getCity()&& add.getPinCode()==customerAddress.getPinCode()
						&&add.getLayout()==customerAddress.getLayout() &&add.getStreetName()==customerAddress.getStreetName()
								&&add.getDoorNo()==customerAddress.getDoorNo()) {
				response1.setCity(add.getCity());

				response1.setDoorNo(add.getDoorNo());
				response1.setPinCode(add.getPinCode());
				response1.setStreetName(add.getStreetName());
				response1.setLayout(add.getLayout());

			
			}
			}
			
			return new ResponseEntity<>(response1, HttpStatus.OK);
			
	}
	@DeleteMapping("/deleteCustomershippingAddress/{id}")
	public ResponseEntity<String> deleteCustomerAddress1(@PathVariable int id) {
		Customer customer= customerService.searchCustomer(id);
		   
		   customer.setCustomerShipingAddres(null);
	    String s="some issue with deleting Address";
		Customer cus=      customerService.updateCustomer(customer);
		       if(cus.getCustomerShipingAddres()==null) {
		    	   s="Customer Address deleted!";
		       }
		    	
		       return new ResponseEntity<>(s, HttpStatus.OK);
		
	}

}
