package com.douzone.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.mysite.security.Auth;
import com.douzone.mysite.service.AdminService;
import com.douzone.mysite.vo.SiteVo;

@Auth(role="ADMIN")
@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private AdminService adminservice; 
	
	@RequestMapping({"","main"})
	public String main(Model model) {
		SiteVo siteVo = adminservice.getinfo();
		
		model.addAttribute(siteVo);
		return "admin/main";
	}
	
	@RequestMapping("/main/update")
	public String mainupdate(SiteVo vo,
			Model model, @RequestParam(value = "file") MultipartFile multipartFile) {
		
		adminservice.mainupdate(multipartFile,vo);
		
		SiteVo siteVo	= adminservice.getinfo();
		model.addAttribute(siteVo);
		
		return "admin/main";
	}
	
	@RequestMapping("/guestbook")
	public String guestbook() {
		return "admin/guestbook";
		
	}
	
	@RequestMapping("/board")
	public String board() {
		return "admin/board";
		
	}
	
	@RequestMapping("/user")
	public String user() {
		return "admin/user";
	}
	
}
