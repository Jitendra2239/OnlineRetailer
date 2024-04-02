package com.jitendra.dao;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;
import org.springframework.stereotype.Repository;

import com.jitendra.model.Inventry;
@Repository
public interface InventryRepo extends CassandraRepository <Inventry, Integer>{
	 @Query(allowFiltering = true)
	 public Inventry findByProductId(int id);
}
