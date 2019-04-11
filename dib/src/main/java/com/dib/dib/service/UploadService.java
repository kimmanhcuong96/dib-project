package com.dib.dib.service;

import java.util.Set;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dib.dib.model.BirthDay;
import com.dib.dib.model.ListCity;
import com.dib.dib.model.ListDistrict;
import com.dib.dib.model.ListVillage;
import com.dib.dib.model.ListWard;
import com.dib.dib.model.YearGOB;

public interface UploadService {
	public void uploadFile(MultipartFile file, RedirectAttributes redirectAttributes, String uploadedFolder, String message);
	
	public boolean saveRawData(String path);
	
	public void deleteOldFile(String path);
	  
	public Integer makeInterger(Double double1);
	
	public boolean checkUnitListCity(String city, Set<ListCity> cities);
	
	public boolean checkUnitDistrict(String district, Set<ListDistrict> districts);
	
	public boolean checkUnitListVillage(String village, Set<ListVillage> villages);
	
	public boolean checkUnitListWard(String ward, Set<ListWard> wards);
	
	public boolean checkUnitYearGoB(Integer year, Set<YearGOB> years);
	
	public boolean checkUnitBirthDay(Integer year, Set<BirthDay> birthDays);
	
}
