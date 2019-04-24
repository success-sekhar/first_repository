package com.jp.hr.springboot.controller;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jp.hr.springboot.entities.Employee;
import com.jp.hr.springboot.exception.EmployeeException;
import com.jp.hr.springboot.service.EmployeeService;
import com.jp.hr.springboot.service.IEmployeeService;
//@CrossOrigin(origins = "*", allowedHeaders = "*") //prep-work 1
@RestController //prep-work 2
@RequestMapping("/api/employees") //prep-work 3
public class EmployeeController {
	@Autowired
	private IEmployeeService employeeService;

	//http://localhost:8082/api/employees
	@GetMapping(value="",produces="application/json")
	public List<Employee> getAllEmployees() throws EmployeeException{
		return employeeService.getEmployeeList();
	}
	
	//http://localhost:8082/api/employees/empId/1
	@GetMapping(value="/empId/{empId}",produces="application/json")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "empId") int empId ) throws EmployeeException{
		Employee employee = employeeService.getEmployeeById(empId);
		return ResponseEntity.ok().body(employee);
	}
	
	//http://localhost:8082/api/employees/save
	@PostMapping("/save")
	public HttpStatus saveEmployee(@RequestBody Employee employee) throws EmployeeException{
		return employeeService.addEmployee(employee) != null? HttpStatus.CREATED :HttpStatus.BAD_REQUEST;
	}
	//http://localhost:8082/api/employees/updateEmp/1
	@PutMapping("/updateEmp/{employeeId}")
	public HttpStatus updateEmployee(@PathVariable(value = "employeeId") int employeeId, @Valid @RequestBody Employee employeeDetails)throws EmployeeException{
		Employee employee = employeeService.getEmployeeById(employeeId);
		if (employee == null)
		{
			return HttpStatus.BAD_REQUEST;
		}
		else
		{
			employee.setFirstName(employeeDetails.getFirstName());
			employee.setLastName(employeeDetails.getLastName());
			return employeeService.updateEmployee(employee)?HttpStatus.ACCEPTED :HttpStatus.BAD_REQUEST;
			
		}
		
	}
	//http://localhost:8082/api/employees//deleteEmp/1
	@DeleteMapping("/deleteEmp/{employeeId}")
	public Map<String, Boolean> deleteEmp(@PathVariable int employeeId) throws EmployeeException{
		Employee employee = employeeService.getEmployeeById(employeeId);
		Map<String, Boolean> response = new HashMap<>();
		if (employee != null)
		{
			employeeService.deleteEmployeeById(employeeId);
			response.put("deleted", Boolean.TRUE);
			
		}
		
		return response;
	}
}
