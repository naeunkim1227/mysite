package com.douzone.mysite.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.mysite.repository.SiteRepository;
import com.douzone.mysite.vo.SiteVo;

@Service
public class AdminService {
	private static String SAVE_PATH = "/upload-images";
	private static String URL_BASE = "/gallery/images";
	
	
	@Autowired
	private SiteRepository siteRepository;
	
	public SiteVo getinfo() {
		return siteRepository.select();
	}

	public int mainupdate(MultipartFile multipartFile,SiteVo vo) {
		
		String OriginName = multipartFile.getOriginalFilename();
		String extName = OriginName.substring(OriginName.lastIndexOf(".") + 1);
		String saveName = generateSaveName(extName);
		
		if(multipartFile != null) {
			
		}
		return siteRepository.updatemain(vo);
	}

	private String generateSaveName(String extName) {
		String name = "";
		
		Calendar calendar = Calendar.getInstance();
		
		name += calendar.get(Calendar.YEAR);
		name += calendar.get(Calendar.MONTH);
		name += calendar.get(Calendar.DATE);
		name += calendar.get(Calendar.HOUR);
		name += calendar.get(Calendar.MINUTE);
		name += calendar.get(Calendar.MILLISECOND);
		name += "." + extName;
		
		return name;
	}
}
