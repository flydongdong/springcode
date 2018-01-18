package com.fly.dd.mycode.common.utils.poi;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;

import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by zhuyd on 2017/3/10.
 */
public class HSSFUtils {

    HSSFWorkbook wb ;
    HSSFSheet sheet;//当前sheet

    /**
     * 新建workBook对象，自动创建第一个sheet(名字：sheet1)
     */
    public HSSFUtils(){
        wb = new HSSFWorkbook();
        this.sheet = wb.createSheet("sheet1");
    }


    /**
     * 传入workBook对象，自动创建第一个sheet(名字：sheet1)
     */
    public HSSFUtils(HSSFWorkbook wb) {
        this.wb = wb;
        this.sheet = wb.createSheet("sheet1");
    }

    /**
     * 创建Excel 工作簿sheet
     * @param sheetName
     * @return
     */
    public HSSFSheet createSheet(String sheetName){
        this.sheet = wb.createSheet(sheetName);
        return this.sheet;
    }

    /**
     * 生成EXCEL数据
     * @param titles
     * @param properties
     * @param list
     */
    public  HSSFWorkbook createExcel(String[] titles, String[] properties, List<?> list){

        if(CollectionUtils.isEmpty(list) || ArrayUtils.isEmpty(properties)){
            return this.wb;
        }

        int row = 0;
        for (Object object:list) {
            int col = 0;
            for (String property : properties) {
                addCell(col,row,getObjectValue(property,object),null);
                col++;
            }
            row++;
        }
        return this.wb;
    }


    /**
     * 设置sheet名字
     * @param index
     * @param sheetName
     */
    public void setSheetName(int index ,String sheetName){
        this.wb.setSheetName(index,sheetName);
    }

    /**
     * 设置当前操作的sheet，如果不存在则无效
     * @param index
     */
    public void setOperationSheet(int index){
        HSSFSheet sheet = wb.getSheetAt(index) ;
        if(sheet!=null){
            this.sheet = sheet;
        }
    }

    /**
     * 添加单元格
     * @param col
     * @param row
     * @param  value
     * @param type
     */
    private void addCell(int col,int row,Object value,HSSFCellStyle type){
        addCell(this.sheet,col,row,value,type);
    }

    /**
     * 添加单元格
     * @param sheet
     * @param col
     * @param row
     * @param value
     * @param type
     */
    private void addCell(HSSFSheet sheet,int col,int row,Object value,HSSFCellStyle type){
        if(sheet==null){
            sheet = this.sheet;
        }
        HSSFRow sheetRow = sheet.getRow(row);
        if(sheetRow == null ){
            sheetRow = sheet.createRow(row);
        }
        HSSFCell cell = sheetRow.createCell(col);
        if(cell == null){
            cell =  sheetRow.createCell(col);
        }
        //设置样式
        if(type == null){
            type = this.wb.createCellStyle();
            type.setAlignment(HorizontalAlignment.CENTER);
        }
        cell.setCellStyle(type);
        cell.setCellType(CellType.STRING);
        cell.setCellValue(String.valueOf(value));
    }

    /**
     * 设置列宽度
     * @param ColumnWidth
     * @param width
     */
    public void  setColumnWidth(int ColumnWidth,int width){
        this.sheet.setColumnWidth(ColumnWidth,width);
    }

    /**
     * 获取单元格 (如不存在自动创建)
     *
     * @param row
     * @param col
     * @return
     */
    public HSSFCell getCell(int row,int col){
        HSSFRow sheetRow = this.sheet.getRow(row);
        if(sheetRow == null ){
            sheetRow = sheet.createRow(row);
        }
        HSSFCell cell = sheetRow.createCell(col);
        if(cell == null){
            cell =  sheetRow.createCell(col);
        }
        return cell;
    }

    /**
     * 根据属性名获取对象的值(get+"属性名(开头大写)")
     * @param valueName 属性名
     * @param obj 对象
     * @return
     */
    private Object getObjectValue(String valueName,Object obj){
        Object value = null;
        String getter = "get" + StringUtils.capitalize(valueName);
        try {
            Method method = obj.getClass().getMethod(getter,new Class[]{});
            value = method.invoke(obj,new Object[]{});
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

//    /**
//     * 获取单元格
//     *
//     * @param sheet
//     * @param row
//     * @param col
//     * @return
//     */
//    private HSSFCell getCell(HSSFSheet sheet, int row, int col) {
//        HSSFRow sheetRow = sheet.getRow(row);
//        if (sheetRow == null) {
//            sheetRow = sheet.createRow(row);
//        }
//        HSSFCell cell = sheetRow.getCell(col);
//        if (cell == null) {
//            cell = sheetRow.createCell(col);
//        }
//        return cell;
//    }

    public static void main(String[] args) {
        String a  = "aBCdE";
        System.out.println(StringUtils.swapCase(a));
        System.out.println(StringUtils.uncapitalize(a));
    }


}
