package com.dib.dib.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dib.dib.Constant.Constant;
import com.dib.dib.dao.BirthDayRepository;
import com.dib.dib.dao.DataRepository;
import com.dib.dib.dao.ListCityRepository;
import com.dib.dib.dao.ListDistrictRepository;
import com.dib.dib.dao.ListVillageRepository;
import com.dib.dib.dao.ListWardRepository;
import com.dib.dib.dao.YearGOBRepository;
import com.dib.dib.model.BirthDay;
import com.dib.dib.model.DataRawFormat;
import com.dib.dib.model.ListCity;
import com.dib.dib.model.ListDistrict;
import com.dib.dib.model.ListVillage;
import com.dib.dib.model.ListWard;
import com.dib.dib.model.YearGOB;

import dib.dib.Utils.UtilCustom;

@Component
public class UploadServiceImpl implements UploadService {

	// Save the uploaded file to this folder
	@Autowired
	private DataRepository dataRepository;

	@Autowired
	private BirthDayRepository birthDayRepository;

	@Autowired
	private ListCityRepository listCityRepository;

	@Autowired
	private ListDistrictRepository listDistrictRepository;

	@Autowired
	private ListVillageRepository listVillageRepository;

	@Autowired
	private ListWardRepository listWardRepository;

	@Autowired
	private YearGOBRepository yearGoBRepository;

	@Override
	public void uploadFile(MultipartFile file, RedirectAttributes redirectAttributes, String uploadedFolder, String message) {
		try {

			// Get the file and save to server directory
			File folder = new File(uploadedFolder);
			if (!folder.exists() || folder == null) {
				folder.mkdir();
			}
			byte[] bytes = file.getBytes();
			Path path = Paths.get(uploadedFolder + file.getOriginalFilename());
			Files.write(path, bytes);

			redirectAttributes.addFlashAttribute(message,
					 Constant.uploadSuccess + "'" + file.getOriginalFilename() + "'");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean saveRawData(String path) {
		File documentFolder = new File(path);
		if (!documentFolder.exists() || documentFolder == null) {
			return false;
		}
		File[] listOfFiles = documentFolder.listFiles();
		int length = listOfFiles.length;
		if (length != 1) {
			return false;
		}
		File dataFile = new File(documentFolder.toString() + "/" + listOfFiles[0].getName());
		if (!dataFile.exists()) {
			return false;
		} else {
			try {
				Workbook workbook = WorkbookFactory.create(dataFile);
				Sheet sheet = workbook.getSheetAt(0);
				Iterator<Row> rowIterator = sheet.rowIterator();
				List<DataRawFormat> dataRawFormats = new ArrayList<>();
				Set<BirthDay> birthDays = new HashSet<>();
				Set<ListCity> listCities = new HashSet<>();
				Set<ListDistrict> listDistricts = new HashSet<>();
				Set<ListVillage> listVillages = new HashSet<>();
				Set<ListWard> listWards = new HashSet<>();
				Set<YearGOB> yearGoBs = new HashSet<>();
				try {
					while (rowIterator.hasNext()) {
						DataRawFormat dataRawFormat = new DataRawFormat();
						Row row = rowIterator.next();
						if (row.getRowNum() != 0) {
							if (row.getCell(0) != null) {
								dataRawFormat.setHoSoSo(row.getCell(0).toString());
							} else {
								dataRawFormat.setHoSoSo(Constant.noInput);
							}
							if (row.getCell(1) != null) {
								dataRawFormat.setHoSoGoc(row.getCell(1).toString());
							} else {
								dataRawFormat.setHoSoGoc(Constant.noInput);
							}
							if (row.getCell(2) != null) {
								dataRawFormat.setHoVaTen(row.getCell(2).toString());
							} else {
								dataRawFormat.setHoVaTen(Constant.noInput);
							}
							if (row.getCell(3) != null) {
								dataRawFormat.setTen(row.getCell(3).toString());
							} else {
								dataRawFormat.setTen(Constant.noInput);
							}
							if (row.getCell(4) != null) {
								dataRawFormat.setHoTenDayDu(row.getCell(4).toString());
								dataRawFormat.setTenTimKiem(UtilCustom.normalizeName(dataRawFormat.getHoTenDayDu()));
							} else {
								dataRawFormat.setHoTenDayDu(Constant.noInput);
								dataRawFormat.setTenTimKiem(Constant.noInput);
							}
							if (row.getCell(5) != null) {
								dataRawFormat.setBiDanh(row.getCell(5).toString());
							} else {
								dataRawFormat.setBiDanh(Constant.noInput);
							}
							if (row.getCell(6) != null) {
								dataRawFormat.setNgayThangNamSinh(row.getCell(6).toString());
							} else {
								dataRawFormat.setNgayThangNamSinh(Constant.noInput);
							}
							if (row.getCell(7) != null) {
								dataRawFormat.setNgaySinh(row.getCell(7).toString());
							} else {
								dataRawFormat.setNgaySinh(Constant.noInput);
							}
							if (row.getCell(8) != null) {
								dataRawFormat.setThangSinh(row.getCell(8).toString());
							} else {
								dataRawFormat.setThangSinh(Constant.noInput);
							}
							if (row.getCell(9) != null) {
								dataRawFormat.setNamSinh(makeInterger(Double.parseDouble(row.getCell(9).toString())));
								if (checkUnitBirthDay(dataRawFormat.getNamSinh(), birthDays)) {
									BirthDay birthDay = new BirthDay();
									birthDay.setBirthDay(dataRawFormat.getNamSinh());
									birthDays.add(birthDay);
								}
							} else {
								dataRawFormat.setNamSinh(Constant.Zero);
							}
							if (row.getCell(10) != null) {
								dataRawFormat.setQueQuanDD(row.getCell(10).toString());
							} else {
								dataRawFormat.setQueQuanDD(Constant.noInput);
							}
							if (row.getCell(11) != null) {
								dataRawFormat.setQueQuanXH(row.getCell(11).toString());
							} else {
								dataRawFormat.setQueQuanXH(Constant.noInput);
							}
							if (row.getCell(12) != null) {
								dataRawFormat.setQueQuanTinh(row.getCell(12).toString());
								if (checkUnitListCity(dataRawFormat.getQueQuanTinh(), listCities)) {
									if(!dataRawFormat.getQueQuanTinh().isEmpty()) {
										ListCity listCity = new ListCity();
										listCity.setCityName(dataRawFormat.getQueQuanTinh());
										listCities.add(listCity);
									} else {
										ListCity listCity = new ListCity();
										listCity.setCityName(Constant.unknown);
										listCities.add(listCity);
									}
								}
							} else {
								dataRawFormat.setQueQuanTinh(Constant.noInput);
							}
							if (row.getCell(13) != null) {
								dataRawFormat.setQueQuanHuyen(row.getCell(13).toString());
								if (checkUnitDistrict(dataRawFormat.getQueQuanHuyen(), listDistricts)) {
									if(!dataRawFormat.getQueQuanHuyen().isEmpty()) {
										ListDistrict listDistrict = new ListDistrict();
										listDistrict.setDistrictName(dataRawFormat.getQueQuanHuyen());
										if (row.getCell(12) != null) {
											listDistrict.setCityName(row.getCell(12).toString());
										} else {
											listDistrict.setCityName(Constant.unknown);
										}
										listDistricts.add(listDistrict);
									} else {
										ListDistrict listDistrict = new ListDistrict();
										listDistrict.setDistrictName(Constant.unknown);
										if (row.getCell(12) != null) {
											listDistrict.setCityName(row.getCell(12).toString());
										} else {
											listDistrict.setCityName(Constant.unknown);
										}
										listDistricts.add(listDistrict);
									}
								}
							} else {
								dataRawFormat.setQueQuanHuyen(Constant.noInput);
							}
							if (row.getCell(14) != null) {
								dataRawFormat.setQueQuanXa(row.getCell(14).toString());
								if (checkUnitListWard(dataRawFormat.getQueQuanXa(), listWards)) {
									ListWard listWard = new ListWard();
									listWard.setWardName(dataRawFormat.getQueQuanXa());
									listWards.add(listWard);
								}
							} else {
								dataRawFormat.setQueQuanXa(Constant.noInput);
							}
							if (row.getCell(15) != null) {
								dataRawFormat.setCoQuan(row.getCell(15).toString());
							} else {
								dataRawFormat.setCoQuan(Constant.noInput);
							}
							if (row.getCell(16) != null) {
								dataRawFormat.setNgayDiB(row.getCell(16).toString());
							} else {
								dataRawFormat.setNgayDiB(Constant.noInput);
							}
							if (row.getCell(17) != null) {
								dataRawFormat.setThangDiB(row.getCell(17).toString());
							} else {
								dataRawFormat.setThangDiB(Constant.noInput);
							}
							if (row.getCell(18) != null) {
								dataRawFormat.setNamDiB(makeInterger(Double.parseDouble(row.getCell(18).toString())));
								if (checkUnitYearGoB(dataRawFormat.getNamDiB(), yearGoBs)) {
									YearGOB yearGoB = new YearGOB();
									yearGoB.setYearGoB(dataRawFormat.getNamDiB());
									yearGoBs.add(yearGoB);
								}
							} else {
								dataRawFormat.setNamDiB(Constant.Zero);
							}
							if (row.getCell(19) != null) {
								dataRawFormat.setNgayThangNamDiB(row.getCell(19).toString());
							} else {
								dataRawFormat.setNgayThangNamDiB(Constant.noInput);
							}
							dataRawFormats.add(dataRawFormat);
						}
					}
				} catch (Exception e2) {
					try {
						workbook.close();
					} catch (Exception e) {
						workbook.close();
						e.printStackTrace();
					}
					e2.printStackTrace();
				}

				dataRepository.deleteAll();
				birthDayRepository.deleteAll();
				listCityRepository.deleteAll();
				listDistrictRepository.deleteAll();
				listVillageRepository.deleteAll();
				listWardRepository.deleteAll();
				yearGoBRepository.deleteAll();

				dataRepository.saveAll(dataRawFormats);
				birthDayRepository.saveAll(birthDays);
				listCityRepository.saveAll(listCities);
				listDistrictRepository.saveAll(listDistricts);
				listVillageRepository.saveAll(listVillages);
				listWardRepository.saveAll(listWards);
				yearGoBRepository.saveAll(yearGoBs);
				try {
					workbook.close();
				} catch (Exception e) {
					e.printStackTrace();
					try {
						workbook.close();
					} catch (Exception e1) {
						e1.printStackTrace();
					} finally {
						workbook.close();
					}
				}
			} catch (EncryptedDocumentException e) {
				e.printStackTrace();
			} catch (InvalidFormatException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return true;
	}

	@Override
	public void deleteOldFile(String path) {
		File documentFolder = new File(path);
		if (!documentFolder.exists() || documentFolder == null) {
			documentFolder.mkdir();
		}
		try {
			FileUtils.cleanDirectory(documentFolder);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Integer makeInterger(Double double1) {
		int val = double1.intValue();
		return val;
	}

	@Override
	public boolean checkUnitBirthDay(Integer year, Set<BirthDay> birthDays) {
		for (BirthDay item : birthDays) {
			if (item.getBirthDay() == year) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean checkUnitListCity(String city, Set<ListCity> cities) {
		for (ListCity item : cities) {
			if (item.getCityName().equals(city)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean checkUnitDistrict(String district, Set<ListDistrict> districts) {
		for (ListDistrict item : districts) {
			if (item.getDistrictName().equals(district)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean checkUnitListVillage(String village, Set<ListVillage> villages) {
		for (ListVillage item : villages) {
			if (item.getVillageName().equals(village)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean checkUnitListWard(String ward, Set<ListWard> wards) {
		for (ListWard item : wards) {
			if (item.getWardName().equals(ward)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean checkUnitYearGoB(Integer year, Set<YearGOB> years) {
		for (YearGOB item : years) {
			if (item.getYearGoB() == year) {
				return false;
			}
		}
		return true;
	}
	
}
