package com.dib.dib.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dib.dib.Constant.Constant;
import com.dib.dib.model.BannerLink;
import com.dib.dib.service.BannerService;


@Controller 
public class WebController {
	
	@Autowired
	private BannerService bannerService;
	
	@Value("${top_banner_path}") 
	String topBannerPath;
  
	@Value("${bottom_banner_path}") 
	String bottomBannerPath;
//	
//	@Value("${top_banner_resource_path}") 
//	String topBannerResourcePath;
//	
//	@Value("${bottom_banner_resource_path}") 
//	String bottomBannerResourcePath;
//  
  @RequestMapping(value = { "/", "/index", "/main" }, method = RequestMethod.GET)
  public String homePage(Model model) {
	  List<BannerLink> bannerLinks = this.bannerService.getBannerLinks();
	  for(BannerLink bannerLink : bannerLinks) {
		  if(bannerLink.getPosition().equals(Constant.BANNER_TOP)) {
			  model.addAttribute("topAdLink", bannerLink.getBannerLink());
		  }
		  if(bannerLink.getPosition().equals(Constant.BANNER_BOTTOM)) {
			  model.addAttribute("bottomAdLink", bannerLink.getBannerLink());
		  }
	  }
      return "main";
  }
  
  
  @RequestMapping(value = { "/quanlydib"}, method = RequestMethod.GET)
  public String searchPage(Model model, HttpServletRequest request) {
      return "management";
  }
  
  @RequestMapping(value = { "/quanlyvideo"}, method = RequestMethod.GET)
  public String videoManagementPage() {
      return "video management";
  }
  
  @RequestMapping(value = { "/quanlybanner"}, method = RequestMethod.GET)
  public String bannerManagementPage() {
      return "banner management";
  }
  
  @GetMapping("/registerAccounts")
  public String registerAccountPage() {
      return "registerAccount";
  }
  
  @RequestMapping(value = { "/upload"}, method = RequestMethod.GET)
  public String getUploadSuccess(Model model) {
      return "management";
  }
  
  @RequestMapping(value = { "/results"}, method = RequestMethod.GET)
  public String getResults(Model model, HttpServletRequest request) {
      return "resultSearch";
  }
  
  @GetMapping("/403")
  public String accessDenied() {
      return "403";
  }

  @GetMapping("/login") 
  public String getLogin() {
      return "login";
  }
  
  
  @GetMapping("/error") 
  public String error() {
      return "login";
  }
//  
//  @GetMapping("/logout")
//  public String logout(HttpServletRequest request, HttpServletResponse response) {
//      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//      if (auth != null) {
//          new SecurityContextLogoutHandler().logout(request, response, auth);
//      }
//      return "redirect:/";
//  }

}



