package utility;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadExcelFile {
	
	WorkbookFactory wbf;
	Workbook wb;
	Sheet sh;
	
	public ReadExcelFile(String excelPath){
		
		try {
			File src = new File(excelPath);
			FileInputStream fis = new FileInputStream(src);
			wbf = new WorkbookFactory();
			wb = wbf.create(fis);
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public String getData(int SheetNum,int row, int col) {
		sh = wb.getSheetAt(SheetNum);
		String data=sh.getRow(row).getCell(col).getStringCellValue(); 
		return data;
		
	}
	
	public int getRowCount(int sheetIndex) {
		int row = wb.getSheetAt(sheetIndex).getLastRowNum();
		row = row+1;
		return row;
		
	}

}
