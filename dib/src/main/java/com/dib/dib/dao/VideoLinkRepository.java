package com.dib.dib.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.dib.dib.model.VideoLink;

public interface VideoLinkRepository extends CrudRepository<VideoLink, Integer>{
	
	@Query("SELECT u FROM VideoLink u ORDER BY u.id DESC")
	  public List<VideoLink> getVideoLinks();
	
	@Transactional
	@Modifying
	@Query("delete from VideoLink v where v.videoLink = :link")
	public void deleteByLink(@Param("link") String link);
	
	
	@Transactional
	@Modifying
	@Query("delete from VideoLink v where v.id = :id")
	public void deleteById(@Param("id") Integer id);
	
	@Transactional
	@Modifying
	@Query("update from VideoLink v set v.videoLink = :videoLink, v.description = :videoDescription where v.id = :id")
	public void updateVideo(@Param("id") Integer id, @Param("videoLink") String videoLink, @Param("videoDescription") String videoDescription);
	

}
