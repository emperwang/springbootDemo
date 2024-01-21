package com.stu.manage.service;

import com.stu.manage.entiry.ImageResponse;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author: Sparks
 * @Date: 2024/1/21 13:17
 * @Description
 */
public interface IImageService {

    ImageResponse imageSave(MultipartFile file);

    boolean deleteImage(String name);
}
