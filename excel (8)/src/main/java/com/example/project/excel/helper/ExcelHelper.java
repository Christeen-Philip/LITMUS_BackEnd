package com.example.project.excel.helper;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.example.project.excel.model.Employee;


public class ExcelHelper {
	
	  public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";  
	  public static boolean hasExcelFormat(MultipartFile file) {

		    if (!TYPE.equals(file.getContentType())) {
		      return false;
		    }

		    return true;
		  }

	  public static List<Employee> excelToEmployees(InputStream is) {
		    try {
		
		      Workbook workbook = WorkbookFactory.create(is);
		      Sheet sheet = workbook.getSheetAt(0);
		      Iterator<Row> rows = sheet.iterator();

		      List<Employee> employees = new ArrayList<Employee>();

		      int rowNumber = 0;
		      while (rows.hasNext()) {
		        Row currentRow = rows.next();

		        // skip header
		        if (rowNumber == 0) {
		          rowNumber++;
		          continue;
		        }
		        
		        Iterator<Cell> cellsInRow = currentRow.iterator();

		        Employee employee = new Employee();
		     
		        
		       // DataFormatter formatter = new DataFormatter();
		        int cellIdx = 0;
		        while (cellsInRow.hasNext()) {
		          Cell currentCell = cellsInRow.next();
		          //String cellValue = formatter.formatCellValue(currentCell.getRow().getCell(cellIdx));
                  
		          switch (cellIdx) {
		          case 0:
			        	employee.setEmpId((int)currentCell.getNumericCellValue());
			        	//employee.setEmpId((int)cellValue);
			            break;

		          case 1:
			        	employee.setEmpEmail(currentCell.getStringCellValue());
			        	//employee.setEmpEmail(cellValue);
			            break;

		          case 2:
			        	employee.setName(currentCell.getStringCellValue());
			        	//employee.setName(cellValue);
			            break;
		            
		          case 3:
		        	  	
			        	employee.setDoj(currentCell.getDateCellValue());
			        	//employee.setDoj(Date(cellValue));
			            break;
			            
		          case 4:
			        	employee.setLob(currentCell.getStringCellValue());
			        	//employee.setLob(cellValue);
			            break;
			            
		          case 5:
			        	employee.setStream(currentCell.getStringCellValue());
			        	//employee.setStream(cellValue);
			            break;
			            
			            
		          case 6:
			        	employee.setRole(currentCell.getStringCellValue());
			        	//employee.setRole(cellValue);
			            break;
			            
			            
		          case 7:
			        	employee.setLocation(currentCell.getStringCellValue());
			        	//employee.setLocation(cellValue);
			            break;
			            
		          case 8:
			        	employee.setManagerEmail(currentCell.getStringCellValue());
			        	//employee.setManagerEmail(cellValue);
			            break;
			            
		          default:
		              break;
		            }

		            cellIdx++;
		          }

		          employees.add(employee);
		        }

		        workbook.close();

		        return employees;
		      } catch (IOException e) {
		        throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
		      }
	  }
		    
		    
		    

	  }
			            
		          



