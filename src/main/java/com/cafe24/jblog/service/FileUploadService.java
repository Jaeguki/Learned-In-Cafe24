package com.cafe24.jblog.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadService {

	private static final String SAVE_PATH = "/Users/jaeguki/dev/Java/logo";
	private static final String URL = "images/logo";

	public String restore(MultipartFile multipartFile) {

		String url = "";
		String saveFileName;
		try {	
			
			if (multipartFile.isEmpty()) {
				return url;
			}
			String originalFileName = multipartFile.getOriginalFilename();
			String extName = originalFileName.substring(originalFileName.lastIndexOf('.') + 1);
			saveFileName = generateSaveFileName(extName);
			long fileSize = multipartFile.getSize();

			System.out.println("############" + originalFileName);
			System.out.println("############" + extName);
			System.out.println("############" + saveFileName);
			System.out.println("############" + fileSize);
			
			byte[] fileData = multipartFile.getBytes();
			OutputStream os = new FileOutputStream(SAVE_PATH + "/" + saveFileName);
			os.write(fileData);
			os.close();
			

		} catch (IOException e) {
			throw new RuntimeException("Fileupload error:" + e);
		}
		
		return URL+"/"+saveFileName;
	}

	private String generateSaveFileName(String extName) {


		String fileName = "";
		Calendar calendar = Calendar.getInstance();
		
		fileName += calendar.get(Calendar.YEAR);
		fileName += calendar.get(Calendar.MONTH);
		fileName += calendar.get(Calendar.DATE);
		fileName += calendar.get(Calendar.HOUR);
		fileName += calendar.get(Calendar.MINUTE);
		fileName += calendar.get(Calendar.SECOND);
		fileName += calendar.get(Calendar.MILLISECOND);
		fileName += ("." + extName);

		return fileName;
	}
}