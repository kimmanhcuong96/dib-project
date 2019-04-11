package com.dib.dib.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dib.dib.dao.BannerLinkRepository;
import com.dib.dib.model.BannerLink;

@Component
public class BannerServiceImpl implements BannerService {
	
	@Autowired
	private BannerLinkRepository bannerLinkRepository;
	
	@Override
	public String findBannerName(String bannerFolderPath) {
		File folder = new File(bannerFolderPath);
		if (!folder.exists() || folder == null) {
			folder.mkdir();
		}
		File[] listOfFiles = folder.listFiles();
		int length = listOfFiles.length;
		if (length != 1) {
			try {
				FileUtils.cleanDirectory(folder);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		String bannerpath = listOfFiles[0].getName();
	
		return bannerpath;
	}
	
	@Override
	public String saveAdLink(BannerLink adLink) {
		this.bannerLinkRepository.deleteByPosition(adLink.getPosition());
		this.bannerLinkRepository.save(adLink);
		return "success";
	}
	
	@Override
	public List<BannerLink> getBannerLinks(){
		return this.bannerLinkRepository.getBannerLinks();
	}
	
}
