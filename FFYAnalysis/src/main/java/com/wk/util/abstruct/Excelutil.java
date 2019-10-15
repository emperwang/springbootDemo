package com.wk.util.abstruct;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface Excelutil {

    void readDataFromExcel(String filePath);

    void readDataFromExcel(MultipartFile file);

    Workbook writeDataToExcel(String json);

    Workbook writeDataToExcel(List data);
}
