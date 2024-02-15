package com.stu.manage.service.impl;

import com.stu.manage.entity.ImageResponse;
import com.stu.manage.service.IImageService;
import com.stu.manage.utils.TimeFormatUtil;
import com.stu.manage.utils.UUIDUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;

/**
 * @author: Sparks
 * @Date: 2024/1/21 13:17
 * @Description
 */
@Service
public class ImageService implements IImageService {

    private static Logger logger = LoggerFactory.getLogger(ImageService.class);

    private ServletContext servletContext;

    private Environment environment;

    @Override
    public ImageResponse imageSave(MultipartFile file) {
        if (file != null && !file.isEmpty()){
            String filename = file.getOriginalFilename();
            String suffix = StringUtils.isEmpty(filename)?"jpg":filename.substring(filename.lastIndexOf(".")+1);
            // generate file name
            logger.info("uploading file: {}", filename);
            File dest = generateFile(suffix);
            try {
                if(!dest.exists()){
                    Files.createFile(dest.toPath());
                }
                file.transferTo(dest);
            } catch (IOException e) {
                logger.error("upload failed.", e);
            }

        return new ImageResponse(dest.getName(),getImageUrl(dest.getName()));
        }
        return new ImageResponse();
    }

    @Override
    public boolean deleteImage(String name) {
        Path path = getPath(name);
        boolean deleteResult = false;
        try {
            deleteResult = Files.deleteIfExists(path);
        } catch (IOException e) {
            logger.error("delete file failed.",e);
        }
        return deleteResult;
    }

    public String getImageUrl(String filename){
        return "http://" + "localhost:" + getPort() + getContextPath() + "/images/" + filename;
    }

    private String getPort(){
        return environment.getProperty("local.server.port");
    }

    private String getContextPath(){
        return servletContext.getContextPath();
    }

    private File generateFile(String suffix){
        String timeNow = TimeFormatUtil.getFormat(TimeFormatUtil.format2).format(LocalDateTime.now());
        String uuid = UUIDUtils.getUUID();
        String basePath = UUIDUtils.savePath();
        return new File(basePath, timeNow + "_" + uuid+"."+suffix);
    }

    private Path getPath(String filename){
        String basePath = UUIDUtils.savePath();
        return Paths.get(basePath,filename);
    }


    @Autowired
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }
    @Autowired
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
