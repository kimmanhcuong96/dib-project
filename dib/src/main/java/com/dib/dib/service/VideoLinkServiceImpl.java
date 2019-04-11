package com.dib.dib.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dib.dib.dao.VideoLinkRepository;
import com.dib.dib.model.VideoLink;

@Service
public class VideoLinkServiceImpl implements VideoLinkService {
	
	@Autowired
	private VideoLinkRepository videoLinkRepository;
	
	@Override
	public void saveVideoLink(VideoLink video) {
		this.videoLinkRepository.save(video);
	}
	
	@Override
	public List<VideoLink> getVideo() {
		List<VideoLink> allVideo = this.videoLinkRepository.getVideoLinks();
		return allVideo;
	}
	
	@Override
	public String deleteVideoByLink(String videoLink) {
		this.videoLinkRepository.deleteByLink(videoLink);
		return "success";
	}
	
	@Override
	public String deleteAllVideoLink() {
		this.videoLinkRepository.deleteAll();
		return "success";
	}
	
	@Override
	public String updateVideo(VideoLink videoLink) {
		this.videoLinkRepository.updateVideo(videoLink.getId(), videoLink.getVideoLink(), videoLink.getDescription());
		return "success";
	}
	
	@Override
	public String deleteVideoById(Integer id) {
		this.videoLinkRepository.deleteById(id);
		return "success";
	}
}
