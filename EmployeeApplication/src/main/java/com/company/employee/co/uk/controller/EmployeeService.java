/**
 * 
 */
package com.company.employee.co.uk.controller;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.employee.co.uk.dao.EmployeeDAO;
import com.company.employee.co.uk.model.Employee;

/**
 * @author damodar
 *
 */
@RestController
@RequestMapping(value = "/company")
public class EmployeeService {
	@Autowired
	EmployeeDAO employeeDAO;
	
	//End point to save employee details
	@PostMapping(value = "/employee")
	public Employee crateEmployee(@Valid @RequestBody Employee employee) {
		return employeeDAO.addEmployee(employee);
	}
	
	//End point to return all available emp details
	@GetMapping("/employee")
	public List<Employee> getAllEmployeeDetails(){
		return employeeDAO.getEmployeeDetails();
	}
	
	//End point to return specific emp details
	@GetMapping("/employee/{id}")
	public ResponseEntity<Employee> findEmpDetailsByID(@PathVariable(value = "id") Integer empID) {
		Employee emp = employeeDAO.findEmpDetails(empID);
		if (null == emp ) {
			ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(emp);
	}
	
	//End point to update an selected emp details
	@PutMapping("/employee/{id}/{location}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable(value="id") Integer empID, @PathVariable(value="location") String location) {
		Employee employee = employeeDAO.findEmpDetails(empID);
		if (null == employee ) {
			ResponseEntity.notFound().build();
		}
		employee.setLocation(location);
		return ResponseEntity.ok().body(employeeDAO.addEmployee(employee));
	}
	
	
	//End point to delete given emp details
	@DeleteMapping("/employee/{id}")
	public ResponseEntity<Employee>  deleteEmployee(@PathVariable(value="id") Integer empID) {
		Employee employee = employeeDAO.findEmpDetails(empID);
		if (null == employee ) {
			ResponseEntity.notFound().build();
		}
		employeeDAO.deletEmployee(employee);
		return ResponseEntity.ok().build();
	}
	
	//End point to delete given emp details
	@GetMapping("/employee/find/{date}/{salary}")
	public List<Employee> getEmployeeDetails(@PathVariable(value="salary") Integer empSalary, @PathVariable(value="date") Date empDate){
		List<Employee> empList = employeeDAO.getEmployeeDetails();
		System.out.println("empSalaryempSalaryempSalaryempSalary"+empSalary);
		empList = empList.stream().filter(i -> i.getEmpSalary() > empSalary && empDate.before(i.getEmpJoingDate())).collect(Collectors.toList());
		return empList;
	}
	
	@PutMapping("/employee/update/{department}/{location}")
	public List<Employee> updateLocation(@PathVariable(value="department") String department, @PathVariable(value="location") String location) {
		List<Employee> empList = employeeDAO.getEmployeeDetails();
		empList.stream().filter(i -> department.equalsIgnoreCase(i.getEmpDepartment())).forEach(i -> i.setLocation(location));
		employeeDAO.saveEmployeeDetails(empList);
		return empList;
	}
}
