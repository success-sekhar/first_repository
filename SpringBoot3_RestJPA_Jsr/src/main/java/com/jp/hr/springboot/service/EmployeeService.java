package com.jp.hr.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jp.hr.springboot.dao.IEmployeeDao;
import com.jp.hr.springboot.entities.Employee;
import com.jp.hr.springboot.exception.EmployeeException;



@Service
public class EmployeeService implements IEmployeeService {
	@Autowired
	private IEmployeeDao employeeDao;
	@Override
	@Transactional
	public Integer addEmployee(Employee employee) throws EmployeeException {
		return employeeDao.addEmployee(employee);
	}

	@Override
	public List<Employee> getEmployeeList() throws EmployeeException {// TODO Auto-generated method stub
		return employeeDao.getEmployeeList();
	}

	@Override
	@Transactional
	public boolean updateEmployee(Employee employee) throws EmployeeException {
		return employeeDao.updateEmployee(employee);
	}

	@Override
	@Transactional
	public boolean deleteEmployeeById(int empId) throws EmployeeException {
		return employeeDao.deleteEmployeeById(empId);
	}

	@Override
	public Employee getEmployeeById(int empId) throws EmployeeException {
		return employeeDao.getEmployeeById(empId);
	}

}
