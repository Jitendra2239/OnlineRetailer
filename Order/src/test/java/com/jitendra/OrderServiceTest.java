package com.jitendra;

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

import com.jitendra.dao.OrderRepo;
import com.jitendra.model.LineItem;
import com.jitendra.model.OrderEntity;
import com.jitendra.service.OrderServiceImpl;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {





		@Mock
		private OrderRepo repo;
	
		@InjectMocks
		private OrderServiceImpl service;

		private LineItem item;
		private OrderEntity entity;

		@BeforeEach
		public void setup() {
			// employeeRepository = Mockito.mock(EmployeeRepository.class);
			// employeeService = new EmployeeServiceImpl(employeeRepository);
			item = LineItem.builder().itemId(1).productName("product1").productId(2).price(100).quantity(200).build();
			List<LineItem> list = new ArrayList<>();
			list.add(item);
			entity = OrderEntity.builder().orderId(2).item(list).build();
		}

		@DisplayName("JUnit test for save method")
		@Test
		void testAddOrder() {
			given(repo.save(entity)).willReturn(entity);
			OrderEntity entity1 = service.addOrder(entity);
			assertThat(entity1).isNotNull();
		}

		@DisplayName("JUnit test for search By Id method")
		@Test
		void testsearchLineItem() {
			given(repo.findById(1)).willReturn(Optional.of(entity));
			List<LineItem> item1 = service.getOder(entity.getOrderId()).getItem();
			assertThat(item1.get(0).getPrice()).isEqualTo(100);
		}

		@DisplayName("JUnit test for delete By Id method")
		@Test
		void testdeleteLineItem() {
			willDoNothing().given(repo).deleteById(1);
			
			service.deleteOrder(1);
			verify(repo, times(1)).deleteById(1);
		}
		
		
		@DisplayName("JUnit test for update method")
		@Test
		void testUpdateLineItem() {
			given(repo.save(entity)).willReturn(entity);
			 entity.setOrderId(2);    
			OrderEntity entity1 = service.updateOrder(entity);
		    assertThat(entity1.getOrderId()).isEqualTo(2);
		}

	}

