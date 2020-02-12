/**
 * 
 */
package com.company.employee.co.uk.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Damodar
 *
 */
@Entity
@Table(name = "employee_data")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer empID;
	@NotNull
	private String empName;
	@NotNull
	private Integer empSalary;
	@NotNull
	private String empDepartment;
	@NotNull
	private Date empJoingDate;
	@NotNull
	private String location;
	
}
