/**
 * 
 */
package com.company.employee.co.uk.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.employee.co.uk.model.Employee;
import com.company.employee.co.uk.repo.EmployeeRepo;

/**
 * @author Damodar
 *
 */
@Service
public class EmployeeDAO {
	@Autowired
	EmployeeRepo empRepository;
	
	//Creating employee records
	public List<Employee> saveEmployeeDetails(List<Employee> empDetails){
		
		return empRepository.saveAll(empDetails);
	}
	
	//Getting all employee details
	public List<Employee> getEmployeeDetails(){
		return empRepository.findAll();
	}
	
	// Get an employee
	public Employee getEmpDetails(Integer id) {
		return empRepository.getOne(id);
	}
	
	//updating an employee with location
	public Employee updateEmployee(Integer empID){
		return empRepository.getOne(empID);
	}
	
	//Deleting an employee
	public void deletEmployee(Employee employee) {
		empRepository.delete(employee);
	}
}
