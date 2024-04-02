package com.jitendra.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.jitendra.dao.CartRepo;
import com.jitendra.dao.LineItemrepo;
import com.jitendra.modal.CartEntity;
import com.jitendra.modal.LineItem;

@ExtendWith(MockitoExtension.class)
class CartServiceImplTest {

	@Mock
	private CartRepo repo;
	@Mock
	private LineItemrepo repo1;
	@InjectMocks
	private CartServiceImpl cartservice;

	private LineItem item;
	private CartEntity entity;

	@BeforeEach
	public void setup() {
		// employeeRepository = Mockito.mock(EmployeeRepository.class);
		// employeeService = new EmployeeServiceImpl(employeeRepository);
		item = LineItem.builder().itemId(1).productName("product1").productId(2).price(100).quantity(200).build();
		List<LineItem> list = new ArrayList<>();
		list.add(item);
		entity = CartEntity.builder().cartId(2).item(list).build();
	}

	@DisplayName("JUnit test for save method")
	@Test
	void testAddLineItem() {
		given(repo.save(entity)).willReturn(entity);
		CartEntity entity1 = cartservice.addcart(entity);
		assertThat(entity1).isNotNull();
	}

	@DisplayName("JUnit test for search By Id method")
	@Test
	void testsearchLineItem() {
		given(repo1.findById(1)).willReturn(Optional.of(item));
		LineItem item1 = cartservice.getItem(item.getItemId());
		assertThat(item1.getPrice()).isEqualTo(100);
	}

	@DisplayName("JUnit test for delete By Id method")
	@Test
	void testdeleteLineItem() {
		willDoNothing().given(repo1).deleteById(1);
		
		cartservice.deleteCart(1);
		verify(repo1, times(1)).deleteById(1);
	}
	
	
	@DisplayName("JUnit test for update method")
	@Test
	void testUpdateLineItem() {
		given(repo.save(entity)).willReturn(entity);
		 entity.setCartId(2);    
		CartEntity entity1 = cartservice.updateCart(entity);
	    assertThat(entity1.getCartId()).isEqualTo(2);
	}

}
