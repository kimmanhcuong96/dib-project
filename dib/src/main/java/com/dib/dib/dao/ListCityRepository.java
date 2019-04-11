package com.dib.dib.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.dib.dib.model.ListCity;

public interface ListCityRepository extends CrudRepository<ListCity, Integer> {
	@Query("SELECT u.cityName FROM ListCity u ORDER BY u.cityName ASC")
	  public List<String> findAllCity();
}
