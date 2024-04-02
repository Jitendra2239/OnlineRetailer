package com.jitendra.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jitendra.model.CustomerCart;
@Repository
public interface CustomerCartRepo extends JpaRepository<CustomerCart, Integer> {

}
