package com.wk.util;

import com.wk.bean.bo.GroupExcelReadbean;
import com.wk.util.abstruct.Excelutil;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
/**
 * 处理Excel
 */
public abstract class ExcelUtilImpl implements Excelutil {
    private static final String suffixXls = "xls";
    private static final String suffixXlsx = "xlsx";
    /**
     *  读取指定的excel文件
     * @param filePath
     * @return
     */
    @Override
    public void readDataFromExcel(String filePath) {
        if (filePath == null && filePath.length() <= 0){
            throw new RuntimeException("readDataFromExcel parameter can't be null");
        }
        File file = new File(filePath);
        try {
            checkFile(file);
        } catch (IOException e) {
            log.error("readDataFromExcel IOException : {}",e.getMessage());
        }
        Workbook workbook = getWorkbook(file);
        if (workbook != null){
            Map<Integer,String> titleFields = new HashMap<>(30);
            int numberOfSheets = workbook.getNumberOfSheets();
            // 遍历sheet
            for (int sheetNum = 0;sheetNum < numberOfSheets;sheetNum++){
                Sheet sheetCur = workbook.getSheetAt(sheetNum);
                if (sheetCur == null){
                    continue;
                }
                // 获取起始 - 结束 列
                int firstRowNum = sheetCur.getFirstRowNum();
                int lastRowNum = sheetCur.getLastRowNum();
                // 遍历第一列 获取第一列标题列
                Row row = sheetCur.getRow(firstRowNum+1);
                if (row != null){
                    int firstCellNum = row.getFirstCellNum();
                    int numberOfCells = row.getPhysicalNumberOfCells();
                    for (int cellNum = firstCellNum;cellNum <= numberOfCells;cellNum++){
                        Cell cell = row.getCell(cellNum);
                        String value = getCellValue(cell);
                        titleFields.put(cellNum,value.replaceAll("\\n",""));
                    }
                }
                // 遍历数据列
                for (int rowNum=firstRowNum+2;rowNum <= lastRowNum;rowNum++){
                    Row rowTmp = sheetCur.getRow(rowNum);
                    if (rowTmp == null){
                        continue;
                    }
                    // 获取起始 以及 结束列
                    int firstCellNum = rowTmp.getFirstCellNum();
                    int numberOfCells = rowTmp.getPhysicalNumberOfCells();
                    List<String> values = new ArrayList<>(numberOfCells);
                    for (int cellNumm = firstCellNum;cellNumm <= numberOfCells;cellNumm++){
                        Cell cell = rowTmp.getCell(cellNumm);
                        String cellValue = getCellValue(cell);
                        values.add(cellValue);
                    }
                    exportValueIntoBean(values,titleFields);
                }
            }
        }
    }

    /**
     *  把读取的值封装到 bean中
     * @param values
     * @return
     */
    protected abstract void exportValueIntoBean(List<String> values,Map<Integer,String> titleFields);

    /**
     *  获取cell中的值
     * @param cell
     * @return
     */
    private String getCellValue(Cell cell) {
        String cellValue = "";
        if (cell == null){
            return cellValue;
        }
        if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC){
            cell.setCellType(Cell.CELL_TYPE_STRING);
        }
        switch (cell.getCellType()){
            case Cell.CELL_TYPE_BLANK:
                cellValue = "";
                break;
            case Cell.CELL_TYPE_BOOLEAN:
                cellValue = String.valueOf(cell.getBooleanCellValue());
                break;
            case Cell.CELL_TYPE_ERROR:
                cellValue = "invalid char";
                break;
            case Cell.CELL_TYPE_FORMULA:
                cellValue = String.valueOf(cell.getCellFormula());
                break;
            case Cell.CELL_TYPE_NUMERIC:
                cellValue = String.valueOf(cell.getNumericCellValue());
                break;
            case Cell.CELL_TYPE_STRING:
                cellValue = String.valueOf(cell.getStringCellValue());
                break;
            default:
                cellValue = "Unknown";
                break;
        }
        return cellValue;
    }

    /**
     *  获取工作文档
     * @param file
     * @return
     */
    private Workbook getWorkbook(File file) {
        String name = file.getName();
        log.info("file name is :{}",name);
        Workbook workbook = null;
        try {
            FileInputStream inputStream = new FileInputStream(file);
            if (name.endsWith(suffixXls)){
                workbook = new HSSFWorkbook(inputStream);
            }else{
                workbook = new XSSFWorkbook(inputStream);
            }
        } catch (FileNotFoundException e) {
            log.error("getWorkbook FileNotFoundException,msg is:{}",e.getMessage());
        } catch (IOException e) {
            log.error("getWorkbook IOException,msg is:{}",e.getMessage());
        }
        return workbook;
    }

    /**
     *  检查文件
     * @param file
     * @throws IOException
     */
    private void checkFile(File file) throws IOException {
        if (file == null){
            log.error("file not exist");
            throw new FileNotFoundException(file.getName()+" file is not exist");
        }
        String name = file.getName();
        log.info("filename is :{}",name);
        if (!name.endsWith(suffixXls) && !name.endsWith(suffixXlsx)){
            log.error(name + "  is not a excel file");
            throw new IOException(name +"  is not a excel file");
        }
    }

    /**
     *  读取上传的excel文件
     * @param file
     * @return
     */
    @Override
    public List<GroupExcelReadbean> readDataFromExcel(MultipartFile file) {
        if (file == null){
            throw new RuntimeException("readDataFromExcel parameter can't be null");
        }
        return null;
    }

    /**
     *  把一个 json 串写入到excel中
     * @param json
     * @return
     */
    @Override
    public Workbook writeDataToExcel(String json) {
        if (json == null && json.length() <= 0){
            throw new RuntimeException("writeDataToExcel parameter can't be null");
        }
        return null;
    }

    /**
     *  把一个list容器中的内容写入到excel中
     * @param data
     * @return
     */
    @Override
    public Workbook writeDataToExcel(List data) {
        if (data == null && data.size() <= 0){
            throw new RuntimeException("writeDataToExcel parameter can't be null");
        }
        return null;
    }

}
