package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility {
	
	
	public static void writeDataIntoCell(String filePath, String sheetName,int serialNo, int cellNo,String dataToSet) {
	        
	        try{
	                   File file=new File(filePath);
	                   FileInputStream ioFile=new FileInputStream(file);
	                   Workbook wb = WorkbookFactory.create(ioFile);
	                      Sheet sheet=wb.getSheet(sheetName);
	                      Row row=sheet.getRow(serialNo);
	                      Cell cell=row.createCell(cellNo);
	                      cell.setCellType(cell.CELL_TYPE_STRING);
	                      cell.setCellValue(dataToSet);    
	                      CellStyle headerStyle = wb.createCellStyle();
	                      Font headerFont = wb.createFont();
	                      if (dataToSet.equalsIgnoreCase("Passed")) {
	                            headerStyle.setFillForegroundColor(IndexedColors.GREEN.getIndex());
	                            headerFont.setColor(IndexedColors.WHITE.getIndex());
	                            headerStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
	                            headerStyle.setFont(headerFont);
	                            cell.setCellStyle(headerStyle);
	                      } else if(dataToSet.equalsIgnoreCase("Failed")){
	                            headerStyle.setFillForegroundColor(IndexedColors.RED.getIndex());
	                            headerFont.setColor(IndexedColors.WHITE.getIndex());
	                            headerStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
	                            headerStyle.setFont(headerFont);
	                            cell.setCellStyle(headerStyle);
	                      }                          
	                      FileOutputStream outFile=new FileOutputStream(filePath);
	                      wb.write(outFile);
	                      outFile.close();
	        }catch(Exception e){
	              e.getMessage();
	      }
	  }

}
