package com.example.project.excel.service;

import java.io.IOException;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.project.excel.helper.ExcelHelper;
import com.example.project.excel.model.Admin;
import com.example.project.excel.model.Employee;
import com.example.project.excel.repository.EmployeeRepository;


@Service
public class ExcelService {
	
	
	@Autowired
	EmployeeRepository repo;
	
	
	public void save(MultipartFile file) {
	    try {
	      List<Employee> employees = ExcelHelper.excelToEmployees(file.getInputStream());
	      repo.saveAll(employees);
	    } 
	    catch (IOException e) {
	      throw new RuntimeException("fail to store excel data: " + e.getMessage());
	    }
	  }
	
	
	public List<Employee> update(MultipartFile file) {
	    try {
	      List<Employee> employees = ExcelHelper.excelToEmployees(file.getInputStream());
	      return employees;
	    } 
	    catch (IOException e) {
	      throw new RuntimeException("fail to store excel data: " + e.getMessage());
	    }
	    
	  }
	

	public List<Employee> getAllEmployees1() {
	    return repo.findAll();
	  }
	
	
	public boolean updateEmployee(Employee newEmployee) {
		System.out.println(newEmployee.getEmpId());
		System.out.println(newEmployee.getLob());
		
	  boolean status = false;
	  Optional<Employee> empOpt = repo.findById(newEmployee.getEmpId());
	  
	  System.out.println(empOpt);
	  if(empOpt.isPresent()) {
		  	Employee emp = empOpt.get();
		  	emp.setLob(newEmployee.getLob());
  			emp.setStream(newEmployee.getStream());
  			emp.setRole(newEmployee.getRole());
  			emp.setLocation(newEmployee.getLocation());
  			emp.setManagerEmail(newEmployee.getManagerEmail());
  			repo.save(emp);
 			status =true;
 		    
	  }
	  		  
     
      return status;
        
	}
	
	 
	
	public boolean loginEmployee(Employee employee) {
		
		Employee emp = repo.employeeLogin(employee.getEmpEmail(),employee.getDoj());
		System.out.println("service:" + employee.getDoj());
		System.out.println("###############" + emp);
		if(emp !=null)
			return true;
		else
			return false;
	}
	
	public boolean loginAdmin(Admin admin) {
			
			Admin adm = repo.adminLogin(admin.getAdminMail(),admin.getPassword());
			System.out.println(admin.getAdminMail());
			System.out.println("###############" + adm);
			if(adm !=null)
				return true;
			else
				return false;
		}
	

}
