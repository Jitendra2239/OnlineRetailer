package com.jitendra.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpServerErrorException.InternalServerError;
import org.springframework.web.client.RestTemplate;


import com.jitendra.exception.CustomerNotFoundException;
import com.jitendra.exception.InventryNotFoundException;
import com.jitendra.exception.ItemNotFoundException;
import com.jitendra.exception.ProductNotFoundException;
import com.jitendra.exception.ServiceDownException;
import com.jitendra.model.Cart;
import com.jitendra.model.Customer;
import com.jitendra.model.CustomerCart;
import com.jitendra.model.CustomerOrder;
import com.jitendra.model.CustomerOrderResponse;
import com.jitendra.model.Customers;
import com.jitendra.model.Inventry;
import com.jitendra.model.Item;

import com.jitendra.model.LineItem;
import com.jitendra.model.LineItems;
import com.jitendra.model.Order;
import com.jitendra.model.Product;
import com.jitendra.model.Products;
import com.jitendra.service.CustomerCartService;
import com.jitendra.service.CustomerOrderService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
@RequestMapping("/api/shopingservice")
public class ShippingController {
   
	@Autowired
	private RestTemplate temp;
	@Autowired
	private CustomerCartService service;
	@Autowired
	private CustomerOrderService service1;


	@PostMapping("/products")
	@CircuitBreaker(name = "shippingService", fallbackMethod = "fallbackRandomActivity5")
	public  ResponseEntity<Object>  addProductInventry(@RequestBody Product product) {
		try {
		String s="Product not added!";
		Products p = Products.builder().productName(product.getProductName())
				.productDescrition(product.getProductDescrition()).productPrice(product.getProductPrice()).build();
		Products createdproduct = temp.postForObject("http://localhost:8085/api/product", p, Products.class);
	    if(createdproduct.getProductId()>0) {
		Inventry in = Inventry.builder().productId(createdproduct.getProductId()).quantity(product.getQuantity())
				.build();
		Inventry createdInventry = temp.postForObject("http://localhost:8083/api/addinventry", in, Inventry.class);
		if(createdInventry.getInventryId()>0) {
			s="Product added Sucessfully!";
	    }
	    else {
	    	temp.delete("http://localhost:8085/api/product/{id}",createdproduct.getProductId());
	    }
	}
	    else {
	    	s="Product not added!";
	    }
		return new  ResponseEntity<>(s,HttpStatus.OK) ;
	}
	catch(Exception e) {
		String s=e.getMessage();
		 throw new ProductNotFoundException(s); 
	}
	}

	@PostMapping("/customer")
	@CircuitBreaker(name = "shippingService", fallbackMethod = "fallbackActivity4")
	public  ResponseEntity<Object> addCustomerCart(@RequestBody Customers customer) {
		
			try {
		CustomerCart  customerCart1 = null ;
		Customer c = Customer.builder().customerBillingAddres(customer.getCustomerBillingAddres())
				.customerName(customer.getCustomerName()).customerShipingAddres(customer.getCustomerShipingAddres())
				.customerEmail(customer.getCustomerEmail()).build();
		
		Customer createdcustomer = temp.postForObject("http://localhost:8081/api/customer", c, Customer.class);
		
        if(createdcustomer.getCustomerId()>0) {
        	
		Cart cart =Cart.builder().build();
		Cart createdcart = temp.postForObject("http://localhost:8086/api/cart", cart, Cart.class);
        
    
	if(createdcart.getCartId()>0) {
		
		CustomerCart customerCart = CustomerCart.builder().cartId(createdcart.getCartId())
				.customerId(createdcustomer.getCustomerId()).build();
	 customerCart1 = service.save(customerCart);
	
		}
		else {
			temp.delete("http://localhost:8081/api/deletecustomer/{id}",createdcustomer.getCustomerId());
		}
      }
    
		return new  ResponseEntity<>(createdcustomer,HttpStatus.OK) ;
		}
	 catch(Exception e ) {
				throw new CustomerNotFoundException("customer not found"); 
		
				 }
	
		
	}
	
	@PutMapping("/customer/{custemerid}/cart")
	@CircuitBreaker(name = "shippingService", fallbackMethod = "fallbackActivity3")
	public  ResponseEntity<Object>  addProducttooCart(@PathVariable int custemerid, @RequestBody LineItems items) {
		  try {
		String s;
		int k = 0;
		int res = items.getLineItems().size();
		for (LineItem item : items.getLineItems()) {
			CustomerCart customercart = service.find(custemerid);
			 if (customercart == null) {
				throw new CustomerNotFoundException("Customer not found!");
			} else {
				int id1 = item.getProductId();
				Products pp = temp.getForEntity("http://localhost:8085/api/product/{id}", Products.class, id1).getBody();
			    if(pp.getProductId()==id1) {			
				int cartid = customercart.getCartId();
				LineItem it = temp.postForObject("http://localhost:8086/api/additem/{id}", item, LineItem.class,
						cartid);
				if (it != null)
					k++;
			    }
			    else {
			    	throw new ProductNotFoundException("Product not Found!");
			    }
			}
		}
		if (k == res) {
			s = "Product added Sucessfully!";
		} else
			s = "Product not added!";
		return new  ResponseEntity<>(s,HttpStatus.OK) ;
		  }
		  catch(Exception e) {
				String s=e.getMessage();
				 throw new CustomerNotFoundException(s); 
		  }
	}
	
	@PostMapping("/customer/{customerid}/order")
	@CircuitBreaker(name = "shippingService", fallbackMethod = "fallbackActivity2")
	public  ResponseEntity<Object> addCustomerOrder(@PathVariable int customerid) {
		try {
		String s="";
	 	CustomerCart customercart=service.find(customerid);

   
      Cart cart=   temp.getForEntity("http://localhost:8086/api/getcart/{id}",Cart.class,customercart.getCartId()).getBody();
      if(cart.getItem().size()==0)
    	  throw new ItemNotFoundException("Item not Added in Cart!");
    	
  
       Order order=Order.builder().item(cart.getItem()).build();
       int count=0;
       for(LineItem item:order.getItem()) {
    	   
       Inventry inventry1=   temp.getForEntity("http://localhost:8083/api/inventry/{productid}",Inventry.class,item.getProductId()).getBody();
      if(inventry1!=null &&inventry1.getQuantity()>=item.getQuantity())
    	  count++;
       }
       if(count==order.getItem().size()) {
       Order order1=	temp.postForObject("http://localhost:8084/api/order", order,  Order.class);
       
       if(order1.getOrderId()==0) {
    	     s="Order not placed";
	}
       
       
       else {  
       CustomerOrder cuor=CustomerOrder.builder().customerId(customerid).orderId(order1.getOrderId()).build();
     
       for(LineItem item:cart.getItem()) {
    	   temp.delete("http://localhost:8086/api/deleteitem/{id}",item.getItemId() );
       Inventry inventry=   temp.getForEntity("http://localhost:8083/api/inventry/{productid}",Inventry.class,item.getProductId()).getBody();
         int res=inventry.getQuantity()-item.getQuantity();
         if(res==0) {
        	 temp.delete("http://localhost:8083/api/deleteinventry/{id}",inventry.getInventryId());
          }else {
        	
        	  inventry.setQuantity(res);
             temp.put("http://localhost:8083/api/updateinventry/{id}", inventry ,inventry.getInventryId());
         }
       }
       
       CustomerOrder cuor1=     service1.save(cuor);
       s="Order placed!";
       }
       }
       else {
    	   s="Order not placed because Item not avilable in Inventry!"; 
       }
   	return new  ResponseEntity<>(s,HttpStatus.OK) ;
		}
		catch(Exception e) {
			String s=e.getMessage();
			 throw new CustomerNotFoundException(s); 
		}
	}
	
	@GetMapping("/customer/{customerid}/order")
	@CircuitBreaker(name = "shippingService", fallbackMethod = "fallbackActivity")
	public ResponseEntity<Object>  getCustomerOrder(@PathVariable int customerid ) {
	 try {
		CustomerOrder customerOrder=	service1.findCustomerOrder(customerid);
	Customer createdcustomer = temp.getForEntity("http://localhost:8081/api/customer/{customerid}", Customer.class,customerid).getBody();
		Order order = temp.getForEntity("http://localhost:8081/api/customer/{id}", Order.class,customerOrder.getOrderId()).getBody();
		CustomerOrderResponse  obj=CustomerOrderResponse .builder().customerBillingAddres(createdcustomer.getCustomerBillingAddres())
				.customerShipingAddres(createdcustomer.getCustomerShipingAddres()).customerEmail(createdcustomer.getCustomerEmail())
				.customerId(createdcustomer.getCustomerId()).customerName(createdcustomer.getCustomerName()).item(order.getItem())
				.orderId(order.getOrderId()).build();
	
		return new  ResponseEntity<>(obj,HttpStatus.OK) ;
	 }
	 catch(Exception e) {
		 throw new CustomerNotFoundException(" "); 
		
 }
		}


		@GetMapping("/hello/{id}")
		@CircuitBreaker(name = "shippingService", fallbackMethod = "fallbackRandomActivity1")
		public Customer  hello(@PathVariable int id) {
			//String ssss = temp.getForEntity("http://localhost:8081/api/hello/{id}", String.class,1).getBody();
		 try {
				Customer createdcustomer = temp.getForEntity("http://localhost:8081/api/hello/{id}", Customer.class,id).getBody();
				//return new ResponseEntity<>( "hello", HttpStatus.NOT_FOUND);
			
				return createdcustomer;
		 }
		 catch(CustomerNotFoundException e) {
			throw new CustomerNotFoundException(" "); 
		 }
	
		}
			
		  public ResponseEntity<Object> fallbackActivity(Exception e) {
			  
			    return new ResponseEntity<>("CustomerService is down! ",HttpStatus.OK) ;
		    }
		  
		  public  ResponseEntity<Object>  fallbackRandomActivity1(Exception e) {
		
			    return new ResponseEntity<>("CustomerService Or OrderService  is down! ",HttpStatus.OK) ;
		    }
		  
		  public  ResponseEntity<Object>  fallbackRandomActivity2(Exception e) {
			
			    return new ResponseEntity<>("Some issue with CartService! ",HttpStatus.OK) ;
		    }
		  public  ResponseEntity<Object> fallbackActivity3(Exception e) {
			
			    return new ResponseEntity<>("Some issue with ProductService!  ",HttpStatus.OK) ;
		    }
		  public  ResponseEntity<Object> fallbackActivity4(Exception e) {

			  return new ResponseEntity<>("CustomerService Or CartService  is down! ",HttpStatus.OK) ;
		    
}
		  public  ResponseEntity<Object> fallbackRandomActivity5(Exception e) {
			
			  return new ResponseEntity<>("ProductService Or InventryService  is down!",HttpStatus.OK) ;
		    
           }
}



