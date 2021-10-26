package com.douzone.mysite.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.mysite.repository.GalleryRepository;
import com.douzone.mysite.service.GalleryService;
import com.douzone.mysite.vo.GalleryVo;

@Controller
@RequestMapping("/gallery")
public class GalleryController {
	
	@Autowired
	private GalleryService galleryService;
	
	@RequestMapping("")
	public String index(Model model){
		
		List<GalleryVo> list = galleryService.getimage();
		
		model.addAttribute("list", list);
	
		return "gallery/index";
	}

	@RequestMapping(value = "/upload" , method = RequestMethod.POST)
	public String upload(@RequestParam(value = "comments", required = true, defaultValue = "") String comments,
			@RequestParam(value = "file") MultipartFile multipartFile) {
	
		galleryService.upload(multipartFile, comments);
		
		return "redirect:/gallery";
	}
	
	
	@RequestMapping(value = "/delete")
	public String delete(@RequestParam (value = "no", required = true) int no) {
		
		galleryService.delete(no);
		
		return "redirect:/gallery";
	}

}
