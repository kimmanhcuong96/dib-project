package com.dib.dib.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dib.dib.model.Login;

@Repository
public interface LoginRepository extends CrudRepository<Login, Integer> {
	  public List<Login> findByUser(String user);
	  
}
