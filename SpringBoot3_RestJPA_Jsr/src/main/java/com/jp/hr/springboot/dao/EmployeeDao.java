package com.jp.hr.springboot.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.jp.hr.springboot.entities.Employee;
import com.jp.hr.springboot.exception.EmployeeException;

@Repository
public class EmployeeDao implements IEmployeeDao {

	@PersistenceContext
	private EntityManager entityManager; 
	@Override
	public Integer addEmployee(Employee employee) throws EmployeeException {
		
		entityManager.persist(employee);
		return employee.getEmpId();
		
	}

	@Override
	public Employee getEmployeeById(int empId) throws EmployeeException {
		Employee emp = entityManager.find(Employee.class, empId);
		return emp;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> getEmployeeList() throws EmployeeException {
		Query query = entityManager.createQuery("from Employee");
		return query.getResultList();
	}

	@Override
	public boolean updateEmployee(Employee employee) throws EmployeeException {
		
		return entityManager.merge(employee)!=null;
	}

	@Override
	public boolean deleteEmployeeById(int empId) throws EmployeeException {
		Employee employee = getEmployeeById(empId);
		if (employee==null)
		{
			return false;
		}
		else{
			entityManager.remove(employee);
			return true;
		}
		
	}

}
