package com.dib.dib.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dib.dib.Constant.Constant;
import com.dib.dib.dao.DataRepository;
import com.dib.dib.dao.ListCityRepository;
import com.dib.dib.dao.ListDistrictRepository;
import com.dib.dib.dto.SearchInfoForm;
import com.dib.dib.model.DataRawFormat;
import com.dib.dib.model.ListDistrict;

import dib.dib.Utils.UtilCustom;

@Component
public class SearchServiceImpl implements SearchService {
	
	@Autowired
	ListCityRepository listCityRepository;
	
	@Autowired
	ListDistrictRepository listDistrictRepository;
	
	@Autowired
	DataRepository dataRepsitory;
	
	@Override
	public List<String> getListCity(){
		return listCityRepository.findAllCity();
	}
	
	@Override
	public List<ListDistrict> getListDistrict(){
		return listDistrictRepository.findAllDistrict();
	}
	
	@Override
	public List<DataRawFormat> dataSearch(SearchInfoForm searchData){
		if(searchData.getAliasName() == null)  searchData.setAliasName(Constant.noInput);
		if(searchData.getBirthDayEnd() == null) searchData.setBirthDayEnd(Constant.VictoryYear);
		if(searchData.getBirthDayStart() == null) searchData.setBirthDayStart(Constant.Zero);
		if(searchData.getCity().equals(Constant.emptyData) || searchData.getCity().equals(Constant.unknown)) searchData.setCity(Constant.noInput);
		if(searchData.getDistrict().equals(Constant.emptyData) || searchData.getDistrict().equals(Constant.unknown)) searchData.setDistrict(Constant.noInput);
		if(searchData.getGoBEnd() == null) searchData.setGoBEnd(Constant.VictoryYear);
		if(searchData.getGoBStart() == null) searchData.setGoBStart(Constant.Zero);
		if(searchData.getName() == null) searchData.setName(Constant.noInput);
		String latinName = UtilCustom.normalizeName(searchData.getName());
		searchData.setName(latinName);
		if(searchData.getVillage() == null) searchData.setVillage(Constant.noInput);
		if(searchData.getWard() == null) searchData.setWard(Constant.noInput);
		return dataRepsitory.findInformation(searchData.getName(), searchData.getWard(), searchData.getAliasName(),
				searchData.getCity(), searchData.getDistrict(), searchData.getBirthDayStart(), searchData.getBirthDayEnd(),
				searchData.getGoBStart(), searchData.getGoBEnd());
	}
	
	@Override
	public List<ListDistrict> getDistrictByCityName(String cityName){
		System.out.println("service: " + cityName);
		return listDistrictRepository.findDistrictByCity(cityName);
	}
}
