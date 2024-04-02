package com.jitendra.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jitendra.model.CustomerCart;
import com.jitendra.model.CustomerOrder;
@Repository
public interface CustomerOrderRepo extends JpaRepository<CustomerOrder, Integer> {
      public CustomerOrder findByCustomerId(int id);
}
