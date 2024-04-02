package com.jitendra.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.jitendra.dao.ProductsDao;
import com.jitendra.model.Products;


@ExtendWith(MockitoExtension.class)
class ProductsServiceImplTest {



	

	@Mock
	private ProductsDao repo;

	@InjectMocks
	private ProductsServiceImpl service;

	private Products products;


		 @BeforeEach                           
		    void setUp() {  
			
			 products=  Products.builder().productId(1).productDescrition("charing").productPrice(200).build();
		    }

	@DisplayName("JUnit test for save method")
	@Test
	void testAddInventry() {
		given(repo.save(products)).willReturn(products);
		Products products1 = service.addProducts(products);
		assertThat(products1).isNotNull();
	}

	@DisplayName("JUnit test for search By Id method")
	@Test
	void testsearchLineItem() {
		given(repo.findById(1)).willReturn(Optional.of(products));
		Products products1= service.searchProducts(products.getProductId());
		assertThat(products1.getProductPrice()).isEqualTo(200);
	}

	@DisplayName("JUnit test for delete By Id method")
	@Test
	void testdeleteLineItem() {
		willDoNothing().given(repo).deleteById(1);
		
		service.deletProducts(1);
		verify(repo, times(1)).deleteById(1);
	}
	
	
	@DisplayName("JUnit test for update method")
	@Test
	void testUpdateLineItem() {
		given(repo.save(products)).willReturn(products);
		products.setProductId(2);    
		Products products1 = service.updateProducts(products);
	    assertThat(products1.getProductId()).isEqualTo(2);
	}

}
