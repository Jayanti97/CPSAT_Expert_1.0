package org.example;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.*;
import java.util.*;


public class ExcelMaster {
    public static Map<String, String> mapRowData;


    public List<String> readExcelData(int rowNum) throws IOException {

        FileInputStream file = new FileInputStream(new File("src/test/resources/TestData/F0-05.xlsx"));
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        Sheet sheet = workbook.getSheet("Sheet1");
        int activeRows = sheet.getLastRowNum() - sheet.getFirstRowNum();
        List<String> rowInfo = new ArrayList<>();
        DataFormatter dataFormatter = new DataFormatter();

        for (int i = 0; i < activeRows + 1; i++) {
            if (i == rowNum) {
                Row row = sheet.getRow(i);
                for (int j = 0; j < row.getLastCellNum(); j++)
                    rowInfo.add(dataFormatter.formatCellValue(row.getCell(j)));
            }


        }
        return rowInfo;
    }


    public void writeDataInExistingExcelSheet() throws IOException {

        FileInputStream file = new FileInputStream(new File("src/test/resources/TestData/F0-05.xlsx"));
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        Sheet sheet = workbook.getSheet("Sheet1");
        int activeRows = sheet.getLastRowNum() - sheet.getFirstRowNum();

        Row row = sheet.getRow(0);
        Row newRow = sheet.createRow(activeRows + 1);
        for (int j = 0; j < row.getLastCellNum(); j++) {


            Cell cell = newRow.createCell(j);
            cell.setCellValue("check");
            j++;

        }


        file.close();
        FileOutputStream outputStream = new FileOutputStream("src/test/resources/TestData/F0-05.xlsx");
        workbook.write(outputStream);
        outputStream.close();


    }


    public Map getTestDataByRow(int rowNum) throws IOException {
        List<String> values = readExcelData(rowNum);
        List<String> keys = Arrays.asList("WU + Account Country", "WU + Account Currency", "Destination Country", "Destination Currency",
                "Delivery Method", "Transfer Amount");
        mapRowData = new HashMap<>();
        for (int i = 0; i < keys.size(); i++)
            mapRowData.put(keys.get(i), values.get(i));
        return mapRowData;
    }


    public void writeDataInNewExcelSheet(Map<String, String> data) throws IOException {

        FileInputStream file = new FileInputStream(new File("src/test/resources/TestData/F0-05.xlsx"));
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        Sheet sheet = workbook.getSheet("TestOutput2");

        int activeRows = sheet.getLastRowNum() - sheet.getFirstRowNum();
        Row newRow = sheet.createRow(activeRows + 1);
        int j = 0;
        for (String value : data.values()) {
            Cell cell = newRow.createCell(j);
            cell.setCellValue(value);
            j++;
        }
        file.close();
        FileOutputStream outputStream = new FileOutputStream("src/test/resources/TestData/F0-05.xlsx");
        workbook.write(outputStream);
        outputStream.close();


    }


    public List<String> readPayeeData(String sheetName) throws IOException {

        FileInputStream file = new FileInputStream(new File("src/test/resources/TestData/F0-05.xlsx"));
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        Sheet sheet = workbook.getSheet(sheetName);
        int activeRows = sheet.getLastRowNum() - sheet.getFirstRowNum();
        List<String> rowInfo = new ArrayList<>();
        DataFormatter dataFormatter = new DataFormatter();

        for (int i = 0; i < activeRows + 1; i++) {

            Row row = sheet.getRow(i);
            for (int j = 0; j < row.getLastCellNum(); j++)
                rowInfo.add(dataFormatter.formatCellValue(row.getCell(j)));


        }
        return rowInfo;
    }


    @Test
    public void checkNow() throws IOException {

        // System.out.println(readPayeeData("Sheet3"));
    /*
    ExcelMaster excelMaster = new ExcelMaster();
    String sEmail = excelMaster.getCellValue("/Users/78102787/Documents/Project- X - UI Automation/src/test/resources/TestData/EmailAndPassword.xlsx","EMAILANDPASSWORD","Validating if the User able to change password","Email");
    String sPassWord = excelMaster.getCellValue("/Users/78102787/Documents/Project- X - UI Automation/src/test/resources/TestData/EmailAndPassword.xlsx","EMAILANDPASSWORD","Validating if the User able to change password","Password");
    excelMaster.setCellValue("/Users/78102787/Documents/Project- X - UI Automation/src/test/resources/TestData/EmailAndPassword.xlsx","EMAILANDPASSWORD","Validating if the User able to change password","Password","Test123$");
    sPassWord = excelMaster.getCellValue("/Users/78102787/Documents/Project- X - UI Automation/src/test/resources/TestData/EmailAndPassword.xlsx","EMAILANDPASSWORD","Validating if the User able to change password","Password");

    System.out.println(sEmail);
    System.out.println(sPassWord);

     */
        Random rnd = new Random();
        int number = rnd.nextInt(9999);
        String sNewPassword = "Hello." + String.format("%04d", number);
        System.out.println(sNewPassword);

    }

    public String getCellValue(String fileName, String sheetName, String scenarioName, String columnName) {
        System.out.println(fileName);
        FileInputStream fis;
        int k = 0, r = 0;
        XSSFCell cell;
        String stringCellValue = null;
        cell = null;
        try {
            fis = new FileInputStream(fileName);
            XSSFWorkbook wb = new XSSFWorkbook(fis);
            XSSFSheet ws = wb.getSheet(sheetName);
            FormulaEvaluator evaluator = wb.getCreationHelper().createFormulaEvaluator();
            int rows = ws.getPhysicalNumberOfRows();
            int cols = ws.getRow(0).getPhysicalNumberOfCells();
            for (int i = 0; i < rows; i++) {
                if (ws.getRow(i).getCell(0).toString().equalsIgnoreCase(scenarioName)) {
                    r = i;
                }
            }
            for (int j = 0; j < cols; j++) {
                if (ws.getRow(0).getCell(j).toString().replace(".0", "").
                        equalsIgnoreCase(columnName)) {
                    k = j;
                }
            }
            cell = ws.getRow(r).getCell(k);
            if (cell == null) {
                return null;
            }
            stringCellValue = evaluator.evaluateInCell(cell).getStringCellValue();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringCellValue;
    }

    public void setCellValue(String fileName, String sheetName, String scenarioName, String columnName, String value) {
        FileInputStream fis;
        int k = 0, r = 0;
        XSSFCell cell;
        cell = null;
        try {
            fis = new FileInputStream(fileName);
            XSSFWorkbook wb = new XSSFWorkbook(fis);
            XSSFSheet ws = wb.getSheet(sheetName);
            FormulaEvaluator evaluator = wb.getCreationHelper().createFormulaEvaluator();
            int rows = ws.getPhysicalNumberOfRows();
            int cols = ws.getRow(0).getPhysicalNumberOfCells();
            for (int i = 0; i < rows; i++) {
                if (ws.getRow(i).getCell(0).toString().replace(".0", "").
                        equalsIgnoreCase(scenarioName)) {
                    r = i;
                }
            }
            for (int j = 0; j < cols; j++) {
                if (ws.getRow(0).getCell(j).toString().replace(".0", "").
                        equalsIgnoreCase(columnName)) {
                    k = j;
                }
            }
            cell = ws.getRow(r).getCell(k);
            if (cell == null) {
            } else {
                evaluator.evaluateInCell(cell).setCellValue(value);
            }

            FileOutputStream outputStream = new FileOutputStream(fileName);
            wb.write(outputStream);
            wb.close();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

