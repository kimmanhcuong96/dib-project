package com.dib.dib.service;

import java.util.List;

import com.dib.dib.model.BannerLink;

public interface BannerService {

	public String findBannerName(String bannerPath);
	
	public String saveAdLink(BannerLink adLink);
	
	public List<BannerLink> getBannerLinks();
}