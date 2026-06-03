package com.seconddeal.backend.service;

//package com.seconddeal.backend.image;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final Cloudinary cloudinary;

    public String uploadImage(MultipartFile file) throws IOException {
        Map result = cloudinary.uploader().upload(
                file.getBytes(),
                ObjectUtils.asMap("folder", "seconddeal") // saves in seconddeal folder
        );
        return result.get("secure_url").toString(); // returns image URL
    }
}