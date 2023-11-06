package com.example.project.excel.repository;





import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.project.excel.model.Admin;
import com.example.project.excel.model.Employee;


public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

	@Query("SELECT emp FROM Employee emp WHERE emp.empEmail=:empMail AND emp.doj=:doj")
	public Employee employeeLogin(@Param("empMail") String empEmail,  @Param("doj") Date doj );
	
	
	@Query("SELECT adm FROM Admin adm WHERE adm.adminMail=:mail AND adm.password=:pwd")
	public Admin adminLogin(@Param("mail") String adminMail, @Param("pwd") String pwd);
	

}
