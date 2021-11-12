package com.douzone.mysite.controller.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.douzone.mysite.dto.JsonResult;
import com.douzone.mysite.service.GuestbookService;
import com.douzone.mysite.vo.guestbookVO;

@Controller("guestbookApiController")
@RequestMapping("/api/guestbook")
public class GuestbookController {
	
	@Autowired
	private GuestbookService guestbookService;
	 
	@ResponseBody
	@RequestMapping("/list")
	public JsonResult list(@RequestParam(value = "sn", required = true, defaultValue = "-1") Long no) {
		List<guestbookVO> list = guestbookService.getlist(no);
		System.out.println("1111");
		System.out.println(no);
		System.out.println(list);
		return JsonResult.success(list);
	}
	
}
