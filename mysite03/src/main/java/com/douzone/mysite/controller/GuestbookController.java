package com.douzone.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.douzone.mysite.service.GuestbookService;
import com.douzone.mysite.vo.guestbookVO;

@Controller
@RequestMapping("/guestbook")
public class GuestbookController {

	@Autowired
	private GuestbookService guestbookService;
	
	@RequestMapping("")
	public String list(Model model) {
		List<guestbookVO> list = guestbookService.getlist();
		model.addAttribute("list",list);
		return "guestbook/list";
	}
	
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String write(guestbookVO vo) {
		guestbookService.write(vo);
		return "redirect:/guestbook";
	}

	@RequestMapping(value="/delete/{no}", method=RequestMethod.GET)
	public String delete(@PathVariable("no") Long no, Model model) {
		model.addAttribute("no", no);
		return "guestbook/delete";
	}
	
	
	@RequestMapping(value = "/delete/{no}", method = RequestMethod.POST)
	public String delete(@PathVariable("no") Long no, @RequestParam(value = "password" , required = true, defaultValue = "") String password) {
		
		guestbookService.delete(no, password);
		return "redirect:/guestbook";
	}
	

	
}