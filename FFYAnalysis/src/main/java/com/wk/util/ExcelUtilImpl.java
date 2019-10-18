package com.wk.util;

import com.wk.util.abstruct.Excelutil;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
/**
 * 处理Excel
 */
public abstract class ExcelUtilImpl implements Excelutil {
    private static final String suffixXls = "xls";
    private static final String suffixXlsx = "xlsx";
    SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
    private boolean isTitle = true;
    private int columnLength;

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
        doReadData(workbook);
    }

    private void doReadData(Workbook workbook){
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
                    isTitle = false;
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
        // 读取时间时,时间列的title格式也是时间格式;所以当读取title时,无论是不是时间格式,都不按照时间读取
        if (!isTitle) {
            String dataFromExcel = getDataFromExcel(cell);
            if (dataFromExcel != null && !"".equals(dataFromExcel)){
                return dataFromExcel;
            }
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
     * 读取excel中的时间
     * @return
     */
    private String getDataFromExcel(Cell cell){
        String cellValue = "";
        if (cell == null){
            return cellValue;
        }
        String dataFormatString = cell.getCellStyle().getDataFormatString();
        // 符合下面任意一个规则，则说明是data模式
        if ("m/d/yy".equals(dataFormatString) ||
                "yyyy/mm;@".equals(dataFormatString)||
                "yy/m/d".equals(dataFormatString) ||
                "mm/dd/yy".equals(dataFormatString) ||
                "dd-mmm-yy".equals(dataFormatString) ||
                "yyyy/m/d".equals(dataFormatString)){
            if (DateUtil.isCellDateFormatted(cell)){
                Date dateCellValue = cell.getDateCellValue();
                cellValue = this.formater.format(dateCellValue);
            }
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

    private Workbook getWorkbook(MultipartFile file) throws IOException {
        if (file == null){
            log.error("getWorkbook but parameter can't be null");
            throw new IOException("getWorkbook but parameter can't be null");
        }
        String name = file.getOriginalFilename();
        log.info("file name is :{}",name);
        Workbook workbook = null;
        try {
            InputStream inputStream = file.getInputStream();
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
    public void readDataFromExcel(MultipartFile file) {
        if (file == null){
            throw new RuntimeException("readDataFromExcel parameter can't be null");
        }
        try {
            Workbook workbook = getWorkbook(file);
            doReadData(workbook);
        } catch (IOException e) {
            log.error("readDataFromExcel IOException,msg is : {}",e.getMessage());
            return;
        }
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
    public Workbook writeDataToExcel(List<Object> data) {
        if (data == null && data.size() <= 0){
            throw new RuntimeException("writeDataToExcel parameter can't be null");
        }
        String sheetName = "personCount";
        Workbook workbook = wirteDataToExcel(sheetName,data);

        return workbook;
    }

    protected Workbook wirteDataToExcel(String sheetName, List data) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet(sheetName);
        // get column length
        int columnLength = getColumnLength();

        // 总行数
        int totalRowNum = data.size() + 2;
        // 设置列宽
        for (int i=0; i < columnLength;i++){
            if (i == 0){
                sheet.setColumnWidth(i,4800);
            }else {
                sheet.setColumnWidth(i,2000);
            }
        }
        // 标题栏
        HSSFRow titleRow = sheet.createRow(0);
        // 行高
        short height = 36*20;
        titleRow.setHeight(height);
        HSSFCell cellTitle = titleRow.createCell(0);
        HSSFCellStyle cellStyle = createStyle(workbook, "黑体", 20,
                HSSFCellStyle.ALIGN_CENTER,false, true);
        // 设置样式
        cellTitle.setCellStyle(cellStyle);
        cellTitle.setCellValue("FFY");
        // 合并单元格  起始行  截止行  起始列  截止列
        sheet.addMergedRegion(new CellRangeAddress(0,0,0,columnLength));

        // 各个列的小标题栏
        HSSFRow columnTitle = sheet.createRow(1);
        height = 49*20;
        columnTitle.setHeight(height);
        // 列标题栏的 样式
        HSSFCellStyle columnTitleStyle = createStyle(workbook, "宋体", 12,
                HSSFCellStyle.ALIGN_CENTER, false, false);
        
        createColumnTitleRow(columnTitle,columnTitleStyle,columnLength);

        HSSFCellStyle contentStyle = createStyle(workbook, "宋体", 12,
                HSSFCellStyle.ALIGN_CENTER, true, false);
        int rowNum = 2;
        writeContentToWorkbook(sheet,rowNum,data,contentStyle);

        // last row
        HSSFRow lastRow = sheet.createRow(totalRowNum);
        height = 35*20;
        lastRow.setHeight(height);
        HSSFCellStyle lastRowCellStyle = createStyle(workbook, "黑体", 8,
                HSSFCellStyle.ALIGN_LEFT, true, true);
        HSSFCell cell0 = lastRow.createCell(columnLength - 2);
        cell0.setCellStyle(lastRowCellStyle);
        cell0.setCellValue("备注:生成时间");
        HSSFCell cell1 = lastRow.createCell(columnLength - 1);
        cell1.setCellStyle(lastRowCellStyle);
        cell1.setCellValue(DateParseUtil.format(new Date(),"yyyy-MM-dd"));
        //sheet.addMergedRegion(new CellRangeAddress(totalRowNum,totalRowNum,0,columnLength));

        return workbook;
    }

    protected abstract void writeContentToWorkbook(HSSFSheet sheet, int rowNum, List<Object> data, HSSFCellStyle contentStyle);

    protected abstract void createColumnTitleRow(HSSFRow columnTitle, HSSFCellStyle columnTitleStyle, int columnLength);

    /**
     *  创建格式
     * @param workbook  workbook
     * @param fontName  字体名字
     * @param fontHeightInPoints 字体高度
     * @param aligment 对齐格式
     * @param border  边界
     * @param boldWeight 加粗
     * @return
     */
    public HSSFCellStyle createStyle(HSSFWorkbook workbook,String fontName,int fontHeightInPoints,short aligment,
                                     boolean border,boolean boldWeight){

        HSSFFont font = workbook.createFont();
        if (boldWeight){
            // 字体加粗
            font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        }
        font.setFontName(fontName);
        font.setFontHeightInPoints((short) fontHeightInPoints);

        // create style
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setFont(font);
        cellStyle.setWrapText(true);
        cellStyle.setAlignment(aligment);
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        // set border
        if (border){
            cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            cellStyle.setBottomBorderColor(HSSFColor.BLACK.index);

            cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            cellStyle.setLeftBorderColor(HSSFColor.BLACK.index);

            cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
            cellStyle.setRightBorderColor(HSSFColor.BLACK.index);

            cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
            cellStyle.setTopBorderColor(HSSFColor.BLACK.index);
        }
        return cellStyle;
    }
    // 获取列总数
    protected abstract int getColumnLength();
}




















