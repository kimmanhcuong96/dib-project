package com.dib.dib.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dib.dib.Constant.Constant;
import com.dib.dib.dto.LoginForm;
import com.dib.dib.dto.SearchInfoForm;
import com.dib.dib.model.BannerLink;
import com.dib.dib.model.DataRawFormat;
import com.dib.dib.model.ListDistrict;
import com.dib.dib.model.VideoLink;
import com.dib.dib.service.BannerService;
import com.dib.dib.service.LoginFormService;
import com.dib.dib.service.SearchService;
import com.dib.dib.service.UploadService;
import com.dib.dib.service.VideoLinkService;

import dib.dib.Utils.UtilCustom;


@Controller 
public class ServiceController {
	
	@Autowired
	private BannerService bannerService;
	
	@Autowired
	private LoginFormService loginFormService;
	
	@Autowired
	private UploadService upload;
	
	@Value("${temp_document_path}") 
	String tempDocumentPath;

    @Value("${document_path}") 
    String documentPath;
    
    @Value("${top_banner_path}") 
    String topBannerPath;
    
    @Value("${bottom_banner_path}") 
    String bottomBannerPath;
    
    @Autowired
    SearchService searchService;
    
    @Autowired
    private VideoLinkService videoLinkService;
    
	 @RequestMapping(value = { "/registerAccount" }, method = RequestMethod.POST)
	  public String registerAccount(Model model, LoginForm newAccount) {
	     System.out.println("data" + newAccount.toString());
	     if(newAccount != null && !newAccount.getUser().isEmpty() & !newAccount.getPassword().isEmpty()) {
	    	 loginFormService.registerNewAccount(newAccount);
	    	 model.addAttribute("message", Constant.regisSuccess);
	     } else {
	    	 model.addAttribute("message", Constant.regisFailure);
	     }
		 return "registerAccount";
	  }
	 
	 @PostMapping("/upload")
	  public String singleFileUpload(@RequestParam("file") MultipartFile file,
	                                 RedirectAttributes redirectAttributes) {
		 String message = "uploadMessage";
	    if (file.isEmpty()) {
	      redirectAttributes.addFlashAttribute(message, Constant.uploadMessage);
	      return "redirect:/upload";
	    }
	    upload.deleteOldFile(tempDocumentPath);
	    upload.uploadFile(file, redirectAttributes, tempDocumentPath, message);
	    return "redirect:/upload";
	  }
	 
	 //upload banner top
	 @PostMapping("/uploadBannerTop")
	  public String uploadTopBanner(@RequestParam("file") MultipartFile file,
	                                 RedirectAttributes redirectAttributes) {
		String message = "uploadMessageTop";
	    if (file.isEmpty()) {
	      redirectAttributes.addFlashAttribute(message, Constant.uploadMessage);
	      return "redirect:/quanlybanner";
	    }
	    upload.deleteOldFile(topBannerPath);
	    upload.uploadFile(file, redirectAttributes, topBannerPath, message);
	    return "redirect:/quanlybanner";
	  }
	 
	 
	//upload banner bottom
		 @PostMapping("/uploadBannerBottom")
		  public String uploadBottomBanner(@RequestParam("file") MultipartFile file,
		                                 RedirectAttributes redirectAttributes) {
			String message = "uploadMessageBottom";
		    if (file.isEmpty()) {
		      redirectAttributes.addFlashAttribute(message, Constant.uploadMessage);
		      return "redirect:/quanlybanner";
		    }
		    upload.deleteOldFile(bottomBannerPath);
		    upload.uploadFile(file, redirectAttributes, bottomBannerPath, message);
		    return "redirect:/quanlybanner";
		  }
	 
	 @RequestMapping(value = { "/synchronizeRaw" }, method = RequestMethod.GET)
	  public String synchronizeRawDatabase( RedirectAttributes redirectAttributes) {
		 
		 if(upload.saveRawData(tempDocumentPath)) {
			 redirectAttributes.addFlashAttribute("synchronizeMessage", Constant.synchComplete);
		 } else {
			 redirectAttributes.addFlashAttribute("synchronizeMessage", Constant.synchFailure);
		 }
		 return "redirect:/upload";
	  }
	 
	 @RequestMapping(value = { "/getCity" }, method = RequestMethod.GET)
	 @ResponseBody
	 public List<String> getCityList(){
		 return searchService.getListCity();
	 }
	 
	 
	 @RequestMapping(value = { "/getDistrict" }, method = RequestMethod.GET)
	 @ResponseBody
	 public List<ListDistrict> getDistrictList(){
		 return searchService.getListDistrict();
	 }
	 
	 @RequestMapping(value = { "/searchInformation" }, method = RequestMethod.POST)
	  public String searchInformation( RedirectAttributes redirectAttributes, SearchInfoForm searchData) {
	     System.out.println("data" + searchData.toString());
	     List<DataRawFormat> results = searchService.dataSearch(searchData);
	     redirectAttributes.addFlashAttribute("results", results);
		 return "redirect:/results";
	  }
	 
	 @RequestMapping(value = "/downloadFile", method = RequestMethod.GET)
	  public StreamingResponseBody getSteamingFile(HttpServletResponse response,
	                                               @RequestParam String document) throws IOException {
	    String standardedName = document.toLowerCase();
	    standardedName = standardedName.trim();
	    while (standardedName.indexOf(" ") != -1) standardedName = standardedName.replaceAll(" ","");
	    while (standardedName.indexOf(".") != -1) standardedName = standardedName.replaceAll(".","");
	    while (standardedName.indexOf("-") != -1) standardedName = standardedName.replaceAll("-","");
	    int length = standardedName.length();
	    UtilCustom.checkFolderExist(documentPath);
	    String fileName = standardedName;
	    if(length > 5) {
	    	fileName = standardedName.substring(0, Constant.fileNameLength);
	    }
	    if(length < 5) {
	    	int compCharacters = Constant.fileNameLength - length;
	    	for(int i = 0; i < compCharacters; i++) {
	    		fileName = Constant.defaultCharacter + fileName;
	    	}
	    }
	    fileName = fileName + Constant.fileFormat;
	    String filePath = documentPath + fileName;
	    File downloadedFile = new File(filePath);
	    response.setContentType("application/pdf");
	    response.setHeader("Content-Disposition: inline", "attachment; filename=" + fileName );
	    InputStream inputStream = new FileInputStream(new File(filePath));
	    if(downloadedFile.exists()) {
	      return outputStream -> {
	        int nRead;
	        byte[] data = new byte[1024];
	        while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
	          outputStream.write(data, 0, nRead);
	        }
	        inputStream.close();
	      };     
	    }
	    
	    return outputStream -> {
	      outputStream.write(0);
	    };
	  }
	 
	 
	 @RequestMapping(value = { "/getSpecifiedDistricts" }, method = RequestMethod.GET)
	 @ResponseBody
	  public List<ListDistrict> getDistrictFromCityName( @RequestParam String city) {
		 System.out.println("param: " + city);
		 return searchService.getDistrictByCityName(city);
	  }
	 
	 
	 //get banner top controller
	 @RequestMapping(value = { "/getTopBanner" }, method = RequestMethod.GET)
	  public StreamingResponseBody getBannerTop(HttpServletResponse response) throws Exception {
		 String fileName = bannerService.findBannerName(topBannerPath);
		 String filePath = topBannerPath + fileName;
		 File downloadedFile = new File(filePath);
		 response.setContentType("image/jpg");
		 response.setHeader("Content-Disposition: inline", "attachment; filename=" + fileName );
		 InputStream inputStream = new FileInputStream(new File(filePath));
		 if(downloadedFile.exists()) {
		      return outputStream -> {
		        int nRead;
		        byte[] data = new byte[1024];
		        while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
		          outputStream.write(data, 0, nRead);
		        }
		        inputStream.close();
		      };     
		    }
		    
		    return outputStream -> {
		      outputStream.write(0);
		    };
	  }
	 
	 
	 //get banner bottom controller
	 @RequestMapping(value = { "/getBottomBanner" }, method = RequestMethod.GET)
	  public StreamingResponseBody getBannerBottom(HttpServletResponse response) throws Exception {
		 String fileName = bannerService.findBannerName(bottomBannerPath);
		 String filePath = bottomBannerPath + fileName;
		 File downloadedFile = new File(filePath);
		 response.setContentType("image/jpg");
		 response.setHeader("Content-Disposition: inline", "attachment; filename=" + fileName );
		 InputStream inputStream = new FileInputStream(new File(filePath));
		 if(downloadedFile.exists()) {
		      return outputStream -> {
		        int nRead;
		        byte[] data = new byte[1024];
		        while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
		          outputStream.write(data, 0, nRead);
		        }
		        inputStream.close();
		      };     
		    }
		    
		    return outputStream -> {
		      outputStream.write(0);
		    };
	  }
	 
	 
	 /*
	  * save video links
	  */
	 @ResponseBody
	 @RequestMapping(value = { "/saveVideo" }, method = RequestMethod.POST)
	 public String saveVideoLink(@RequestBody VideoLink videoLink){
		 this.videoLinkService.saveVideoLink(videoLink);
		 return "success";
	 }
	 
	 
	 /*
	  * get video links
	  */
	 @ResponseBody
	 @RequestMapping(value = { "/getVideoList" }, method = RequestMethod.GET)
	 public List<VideoLink> getVideoLink(){
		 return this.videoLinkService.getVideo();
	 }
	 
	 
	 /*
	  * save banner ad-link
	  */
	 @ResponseBody
	 @RequestMapping(value = { "/saveBannerAds" }, method = RequestMethod.POST)
	 public String saveBannerAds(@RequestBody BannerLink adLink){
		 return this.bannerService.saveAdLink(adLink); 
	 }
	 
	 /*
	  * delete video link by link
	  */
	 @ResponseBody
	 @RequestMapping(value = { "/deleteByVideoLink" }, method = RequestMethod.POST)
	 public String deleteByVideoLink(@RequestBody String videoLink){
		 return this.videoLinkService.deleteVideoByLink(videoLink);
	 }
	 
	 
	 /*
	  * delete video link by link
	  */
	 @ResponseBody
	 @RequestMapping(value = { "/deleteByVideoId" }, method = RequestMethod.POST)
	 public String deleteByVideoId(@RequestBody VideoLink videoLink){
		 return this.videoLinkService.deleteVideoById(videoLink.getId());
	 }
	 
	 /*
	  * delete all video link
	  */
	 @ResponseBody
	 @RequestMapping(value = { "/deleteAllVideoLink" }, method = RequestMethod.GET)
	 public String deleteAllVideoLink(){
		 return this.videoLinkService.deleteAllVideoLink();
	 }
	 
	 /*
	  * update video link
	  */
	 @ResponseBody
	 @RequestMapping(value = { "/updateVideo" }, method = RequestMethod.POST)
	 public String updateVideo(@RequestBody VideoLink videoLink){
		 return this.videoLinkService.updateVideo(videoLink); 
	 }
}
