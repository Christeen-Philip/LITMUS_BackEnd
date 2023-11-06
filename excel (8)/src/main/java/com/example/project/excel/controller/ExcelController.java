package com.example.project.excel.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.project.excel.helper.ExcelHelper;
import com.example.project.excel.message.ResponseMessage;
import com.example.project.excel.model.Admin;
import com.example.project.excel.model.Employee;
import com.example.project.excel.repository.EmployeeRepository;
import com.example.project.excel.service.ExcelService;;

@RestController
@CrossOrigin(origins = "*", allowedHeaders="*")
@RequestMapping("/excel")
public class ExcelController {
	
	@Autowired
	ExcelService fileService;
	@Autowired
	EmployeeRepository repo;
	
	  @PostMapping("/upload")
	  public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
	    String message = "";

	    if (ExcelHelper.hasExcelFormat(file)) {
	        fileService.save(file);

	        message = "Uploaded the file successfully: " + file.getOriginalFilename();
	        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
	    
	    }
	      
	      message = "Please upload an excel file!";
	      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
	    }
	  
	  
	  
	  @GetMapping("/employees")
	  public ResponseEntity<List<Employee>> getAllEmployees() {
	    try {
	      List<Employee> employees = fileService.getAllEmployees1();

	      if (employees.isEmpty()) {
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      }

	      return new ResponseEntity<>(employees, HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
	  
	  
	  
	  
	  @PostMapping("/updateupload")
	  public ResponseEntity<ResponseMessage> uploadFile1(@RequestParam("file") MultipartFile file) {
	    String message = "";

	    if (ExcelHelper.hasExcelFormat(file)) {
	    
	    	List<Employee> empList = fileService.update(file);
	        List<Employee> employees = fileService.getAllEmployees1();
	        List<Employee> updateList = new ArrayList<Employee>();

	        for(Employee emp : empList) {
	        	for(Employee empl : employees){
	        		if(emp.getEmpId()==empl.getEmpId()) {
	        			empl.setLob(emp.getLob());
	        			empl.setStream(emp.getStream());
	        			empl.setRole(emp.getRole());
	        			empl.setLocation(emp.getLocation());
	        			empl.setManagerEmail(emp.getManagerEmail());
	        			
	        			updateList.add(empl);
	        			
	        			repo.saveAll(updateList);  
	        			     			
	        		}
	        		 
	        	}
	        }
	        
	        message = "Updated the file successfully: " + file.getOriginalFilename();
	        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
	    
	    } 
	      message = "Please upload an excel file!";
	      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
	    }
	  
	  
	  
	  @PostMapping(value="/employeeUpdate", consumes=org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
	  
	  public  ResponseEntity<String> updateEmployee(@RequestBody Employee newEmployee) {
		  System.out.println("Request receied.");
		  System.out.println(newEmployee);
			boolean result = fileService.updateEmployee(newEmployee);
			System.out.println(result);
			String resp =  "{'status' :" + result +"}";
			HttpHeaders headers = new HttpHeaders();
			
			ResponseEntity<String> respEntity = new ResponseEntity<String>(resp,headers,HttpStatus.OK);
			return respEntity;
		}
	  
	  
	  @PostMapping(value="/employee",consumes=org.springframework.http.MediaType.APPLICATION_JSON_VALUE )
		public ResponseEntity<String> employeeLogin(@RequestBody Employee employee){ 
		  	System.out.println("employee login.............");
		  	boolean result= fileService.loginEmployee(employee);
		  	System.out.println(result);
			String resp =  "{'status' :" + result +"}";
			HttpHeaders headers = new HttpHeaders();
			
			ResponseEntity<String> respEntity = new ResponseEntity<String>(resp,headers,HttpStatus.OK);
			return respEntity;
		}	
	  
	  
	  @PostMapping(value="/admin", consumes=org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<String> adminLogin(@RequestBody Admin admin) {
		  	
		  	System.out.println(admin.getAdminMail());
		  	boolean result = fileService.loginAdmin(admin);
		  	System.out.println(result);
			String resp =  "{'status' :" + result +"}";
			HttpHeaders headers = new HttpHeaders();
			
			ResponseEntity<String> respEntity = new ResponseEntity<String>(resp,headers,HttpStatus.OK);
			return respEntity;
		}	
	  
	 
}
