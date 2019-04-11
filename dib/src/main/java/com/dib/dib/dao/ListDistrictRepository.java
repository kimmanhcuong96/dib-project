package com.dib.dib.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.dib.dib.model.ListDistrict;

public interface ListDistrictRepository extends CrudRepository<ListDistrict, Integer>{
	@Query("SELECT u FROM ListDistrict u ORDER BY u.districtName ASC")
	  public List<ListDistrict> findAllDistrict();
	
	@Query("SELECT u FROM ListDistrict u WHERE lower(u.cityName) LIKE lower(:cityName)")
	  public List<ListDistrict> findDistrictByCity( @Param("cityName") String cityName);
}
