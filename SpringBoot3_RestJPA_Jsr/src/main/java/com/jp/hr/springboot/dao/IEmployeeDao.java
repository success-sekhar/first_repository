package com.jp.hr.springboot.dao;
import java.util.List;

import com.jp.hr.springboot.entities.Employee;
import com.jp.hr.springboot.exception.EmployeeException;
/** @author Smita **/
public interface IEmployeeDao {
	public Integer addEmployee(Employee employee)throws EmployeeException;//C-create
	public List<Employee> getEmployeeList()throws EmployeeException;//R All Employee -retrieve
	public boolean updateEmployee(Employee employee)throws EmployeeException;//U-update
	public boolean deleteEmployeeById(int empId)throws EmployeeException;//D-delete
	public Employee getEmployeeById(int empId)throws EmployeeException;//S-search
}
