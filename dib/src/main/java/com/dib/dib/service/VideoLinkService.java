package com.dib.dib.service;

import java.util.List;

import com.dib.dib.model.VideoLink;

public interface VideoLinkService {
	
	public void saveVideoLink(VideoLink video);
	public List<VideoLink> getVideo();
	public String deleteVideoByLink(String videoLink);
	public String deleteVideoById(Integer id);
	public String deleteAllVideoLink();
	public String updateVideo(VideoLink videoLink);
}
