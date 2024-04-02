package com.jitendra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jitendra.dto.ProductsResponse;
import com.jitendra.exception.ProductNotFoundException;
import com.jitendra.model.Products;
import com.jitendra.service.ProductsService;
@RestController
@RequestMapping("/api")
public class ProductsController {
    int k=1;
	 @Autowired
	 ProductsService productsService;
   @PostMapping("/product")
	public ResponseEntity< ProductsResponse>  addProducts(@RequestBody Products products) {
		// TODO Auto-generated method stub
	   if(products==null ) {
		   throw new ProductNotFoundException("add Products");
	   }
	   else    if(products.getProductPrice()==0) {
		   throw new ProductNotFoundException("add Price of Product"); 
	   }
	   else    if(products.getProductName()==null) {
		   throw new ProductNotFoundException("add Name of Product"); 
	   }
	   products.setProductId(k++);
	   Products products1=   productsService.addProducts(products);
	   ProductsResponse response =new ProductsResponse();
	   response.setProductDescrition(products1.getProductDescrition());
	   response.setProductId(products1.getProductId());
	   response.setProductName(products1.getProductName());
	   response.setProductPrice(products1.getProductPrice());
		return new ResponseEntity<>(response ,HttpStatus.CREATED) ;
	}

  @DeleteMapping("/product/{id}")
	public ResponseEntity<String>deletProducts(@PathVariable int id) {
		// TODO Auto-generated method stub
	  Products products1=   productsService.searchProducts(id);
	   if(products1==null) {
		   throw new ProductNotFoundException(" Prodcuts not avilable!");
	   }
	  String s1=   productsService.deletProducts(id);
	        k--;
		 	return new ResponseEntity<>(s1,HttpStatus.OK) ;
	}
   
         @GetMapping("/product/{id}")
	public ResponseEntity<ProductsResponse>  searchProducts(@PathVariable int id) {
		// TODO Auto-generated method stub
 	   Products products1=   productsService.searchProducts(id);
 	   if(products1==null) {
 		   throw new ProductNotFoundException(" Prodcuts not avilable!");
 	   }
 	  ProductsResponse response =new ProductsResponse();
	   response.setProductDescrition(products1.getProductDescrition());
	   response.setProductId(products1.getProductId());
	   response.setProductName(products1.getProductName());
	   response.setProductPrice(products1.getProductPrice());
 		return new ResponseEntity<>(response,HttpStatus.OK) ;
	}

     @PutMapping("/product/{id}")
	public  ResponseEntity< ProductsResponse>   updateProducts(@PathVariable int id,@RequestBody Products products) {
		// TODO Auto-generated method stub
    	   Products products1=   productsService.searchProducts(id);
     	   if(products1==null) {
     		   throw new ProductNotFoundException(" Prodcuts not avilable!");
     	   }
     	   products1.setProductDescrition(products.getProductDescrition());
     	   products1.setProductName(products.getProductName());
     	   products1.setProductPrice(products.getProductPrice());
     	 
    	   Products products2=   productsService.updateProducts(products1);
    	
    	   ProductsResponse response =new ProductsResponse();
    	   response.setProductDescrition(products2.getProductDescrition());
    	   response.setProductId(products2.getProductId());
    	   response.setProductName(products2.getProductName());
    	   response.setProductPrice(products2.getProductPrice());
   		return new ResponseEntity<>(response,HttpStatus.OK) ;
	}

}
