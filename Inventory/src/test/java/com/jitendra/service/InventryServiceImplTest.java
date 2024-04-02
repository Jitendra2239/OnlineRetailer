package com.jitendra.service;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;

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

import com.jitendra.dao.InventryRepo;
import com.jitendra.model.Inventry;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
@ExtendWith(MockitoExtension.class)
public class InventryServiceImplTest {

		@Mock
		private InventryRepo repo;
	
		@InjectMocks
		private InventryServiceImpl service;

		private Inventry inventry;


		@BeforeEach
		public void setup() {
			// employeeRepository = Mockito.mock(EmployeeRepository.class);
			// employeeService = new EmployeeServiceImpl(employeeRepository);
			inventry= Inventry.builder().inventryId(1).productId(2).quantity(200).build();
			
		}

		@DisplayName("JUnit test for save method")
		@Test
		void testAddInventry() {
			given(repo.save(inventry)).willReturn(inventry);
			Inventry entity1 = service.addLiInventry(inventry);
			assertThat(entity1).isNotNull();
		}

		@DisplayName("JUnit test for search By Id method")
		@Test
		void testsearchLineItem() {
			given(repo.findById(1)).willReturn(Optional.of(inventry));
			Inventry inventry1= service.searchLiInventry(inventry.getInventryId());
			assertThat(inventry1.getQuantity()).isEqualTo(200);
		}

		@DisplayName("JUnit test for delete By Id method")
		@Test
		void testdeleteLineItem() {
			willDoNothing().given(repo).deleteById(1);
			
			service.deleteLiInventry(1);
			verify(repo, times(1)).deleteById(1);
		}
		
		
		@DisplayName("JUnit test for update method")
		@Test
		void testUpdateLineItem() {
			given(repo.save(inventry)).willReturn(inventry);
			inventry.setInventryId(2);    
			Inventry entity1 = service.updateLiInventry(inventry);
		    assertThat(entity1.getInventryId()).isEqualTo(2);
		}

	}
