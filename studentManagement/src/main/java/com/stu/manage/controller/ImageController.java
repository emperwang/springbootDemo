package com.stu.manage.controller;

import com.stu.manage.entity.ImageResponse;
import com.stu.manage.entity.StatusResult;
import com.stu.manage.service.IImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author: Sparks
 * @Date: 2024/1/21 11:58
 * @Description
 */
@RestController
@RequestMapping("image")
public class ImageController {

    private IImageService imageService;

    @PostMapping("upload")
    public ResponseEntity<ImageResponse> uploadImage(MultipartFile file){
        ImageResponse response = imageService.imageSave(file);
        return !StringUtils.isEmpty(response.getName())?ResponseEntity.status(HttpStatus.CREATED).body(response):ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @DeleteMapping("delete/{name}")
    public ResponseEntity<Object> deleteImage(@PathVariable("name") String name){
        boolean delRes = imageService.deleteImage(name);
        return delRes?ResponseEntity.status(HttpStatus.NO_CONTENT).build():ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new StatusResult(1,"file not fond"));
    }


    @Autowired
    public void setImageService(IImageService imageService) {
        this.imageService = imageService;
    }
}
