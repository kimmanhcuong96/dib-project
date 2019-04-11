package com.dib.dib.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.dib.dib.model.BannerLink;

public interface BannerLinkRepository extends CrudRepository<BannerLink, Integer>{
	
	@Transactional
	@Modifying
	@Query("delete from BannerLink b where b.position = :position")
	public void deleteByPosition(@Param("position") String position);
	
	@Query("select b from BannerLink b")
	public List<BannerLink> getBannerLinks();

}
