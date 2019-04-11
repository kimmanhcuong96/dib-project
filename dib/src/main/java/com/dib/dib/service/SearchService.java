package com.dib.dib.service;

import java.util.List;

import com.dib.dib.dto.SearchInfoForm;
import com.dib.dib.model.DataRawFormat;
import com.dib.dib.model.ListDistrict;

public interface SearchService {

	public List<String> getListCity();
	
	public List<ListDistrict> getListDistrict();
	
	public List<DataRawFormat> dataSearch(SearchInfoForm searchData);
	
	public List<ListDistrict> getDistrictByCityName(String cityName);
}
