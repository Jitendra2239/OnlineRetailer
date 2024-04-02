package com.jitendra.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jitendra.model.Customer;
@Repository
public interface CustomerRepo extends JpaRepository<Customer, Integer>{

}
