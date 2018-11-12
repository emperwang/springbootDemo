package com.wk.upload.demo.controller;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Controller
public class UploadAndDownload {

    @GetMapping("index.do")
    public String index(){
        return "Upload";
    }

    @PostMapping(value = "uploadfile.do")
    public ModelAndView uploadFile(@RequestParam("uploadFile")MultipartFile uploadFile,@RequestParam("id") String id){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("Upload");
        InputStream fis = null;
        OutputStream outputStream = null;

        try {
            fis = uploadFile.getInputStream();
            File dest = new File("D:\\image\\"+uploadFile.getOriginalFilename());
            if(!dest.exists()){
                dest.createNewFile();
            }
            outputStream = new FileOutputStream(dest);
            IOUtils.copy(fis,outputStream);
            modelAndView.addObject("success","上传成功");
            return modelAndView;
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(fis != null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(outputStream!=null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        modelAndView.addObject("success","上传失败");
        return modelAndView;
    }

    @PostMapping("download.do")
    public void downLoadFileAction(HttpServletRequest request, HttpServletResponse response){
        response.setCharacterEncoding(request.getCharacterEncoding());
        response.setContentType("application/octet-stream");
        FileInputStream fis = null;

        File file = new File("D:\\test.xls");
        try {
            fis  = new FileInputStream(file);
            response.setHeader("Content-Disposition","attachment;filename="+file.getName());
            IOUtils.copy(fis,response.getOutputStream());
            response.flushBuffer();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
