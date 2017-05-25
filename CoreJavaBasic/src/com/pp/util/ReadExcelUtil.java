package com.pp.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Created by anirudh on 20/10/14.
 */
public class ReadExcelUtil {

    //private static final String FILE_PATH = "/Users/anirudh/Projects/JCGExamples/JavaWriteReadExcelFileExample/testReadStudents.xlsx";
    private static final String FILE_PATH = "TestData.xlsx";

    public static void main(String args[]) {

        Map userData = getExcelAllData();
        System.out.println(userData);
    }

    private static Map<String,String> getExcelAllData() {
        Map <String,String> userData = new HashMap<String,String>();
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("TestData.xlsx");
            // Using XSSF for xlsx format, for xls use HSSF
            Workbook workbook = new XSSFWorkbook(fis);

            int numberOfSheets = workbook.getNumberOfSheets();

            //looping over each workbook sheet
            for (int i = 0; i < numberOfSheets; i++) {
                Sheet sheet = workbook.getSheetAt(i);
                Iterator rowIterator = sheet.iterator();

                //iterating over each row
                while (rowIterator.hasNext()) {
                	
                    //Student student = new Student();
                    Row row = (Row)rowIterator.next();
                    
                    //This is to Skip First Header Row of Sheet
                	if(row.getRowNum() == 0){
                		continue;
                	}
                    Iterator cellIterator = row.cellIterator();
                    //Iterating over each cell (column wise)  in a particular row.
                    String keyUserName = null;
                	String valUserPswd = null;
                    while (cellIterator.hasNext()) {
                        Cell cell = (Cell)cellIterator.next();
                        //The Cell Containing String will is name.
                        if(cell.getColumnIndex() == 1){
                        	keyUserName = cell.getStringCellValue();
                        }else if(cell.getColumnIndex() == 2){
                        	valUserPswd = cell.getStringCellValue();
                        	 userData.put(keyUserName, valUserPswd);
                        	 keyUserName = null;
                        	 valUserPswd = null;
                        }
                    }
                }
            }
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }catch(Exception e){
        	e.printStackTrace();
        }
        return userData;
    }
}
