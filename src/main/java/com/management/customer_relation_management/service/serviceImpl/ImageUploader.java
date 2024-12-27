package com.management.customer_relation_management.service.serviceImpl;

import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;

@Service
public class ImageUploader {

	@Autowired
    private Cloudinary cloudinary;

    public String iamgeUploader(MultipartFile file){
        try {
			String url = this.cloudinary.uploader().upload(file.getBytes(),Map.of("public_id",UUID.randomUUID().toString())).get("url").toString();
			return url;
		}
		catch(Exception e){
			throw new RuntimeException("server erro");
		}
    }
    
}
