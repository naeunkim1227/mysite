package com.douzone.mysite.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.mysite.repository.GalleryRepository;
import com.douzone.mysite.vo.GalleryVo;

@Service
public class GalleryService {

	@Autowired
	private GalleryRepository galleryRepository;

	private static String SAVE_PATH = "/upload-images";
	private static String URL_BASE = "/gallery/images";

	public void upload(MultipartFile multipartFile, String comments) {
		File uploaddir = new File(SAVE_PATH);
		
		try {
			if (!uploaddir.exists()) {
				uploaddir.mkdir();
			}
			
			/*
			if(multipartFile.isEmpty()) {
				return null;
			}
			
			*/
			String originName = multipartFile.getOriginalFilename();
			System.out.println(originName);
			String extName = originName.substring(originName.lastIndexOf(".") + 1);
			String saveName = generateSaveFileName(extName);
			
			System.out.println(extName);
			System.out.println(saveName);
			
			byte[] data = multipartFile.getBytes();
			OutputStream os = new FileOutputStream(SAVE_PATH + "/" + saveName);
			os.write(data);
			os.close();
			
			GalleryVo vo = new GalleryVo();
			vo.setUrl(URL_BASE + "/" + saveName);
			vo.setComments(comments);
			
			galleryRepository.insert(vo);
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private String generateSaveFileName(String extName) {
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

	public List<GalleryVo> getimage() {
		
		return	galleryRepository.getimages();
	}

	public int delete(int no) {
		
		return galleryRepository.delete(no);
	}

}
