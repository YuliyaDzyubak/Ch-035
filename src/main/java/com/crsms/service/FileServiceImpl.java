package com.crsms.service;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service("multipartFileService")
public class FileServiceImpl implements FileService {
	
	private final String storagePath = "storage" + File.separator + "resources";
	
	@Override
	public void uploadFile(MultipartFile multipartFile) throws IOException {	
		String originalName = multipartFile.getOriginalFilename();
        // Creating if not exist the directory to store file
        String rootPath = System.getProperty("catalina.home");
        File dir = new File(rootPath + File.separator + storagePath);
        if (!dir.exists())
            dir.mkdirs();
        String filePath = dir.getAbsoluteFile() + File.separator + originalName;
        multipartFile.transferTo(new File(filePath));
	}

	@Override
	public File getFileFromStorage(String name) {
		return null;
	}

	public String getStoragePath() {
		return storagePath;
	}
	
}