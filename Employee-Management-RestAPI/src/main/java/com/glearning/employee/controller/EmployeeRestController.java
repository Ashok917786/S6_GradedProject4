package com.glearning.employee.controller;

import java.util.List;

import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.glearning.employee.entity.Employee;
import com.glearning.employee.service.EmployeeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeRestController {

	private final EmployeeService employeeService;

	@PostMapping
	public Employee saveEmployee(@RequestBody Employee employee) {
		return this.employeeService.saveEmployee(employee);
	}

	@GetMapping
	public List<Employee> fetchAllEmployees() {
		return this.employeeService.fetchAllEmployee();
	}

	@GetMapping("/sort")
	public List<Employee> fetchAllEmployees(Direction direction) {
		return this.employeeService.fetchAllEmployee(direction);
	}

	@GetMapping("/{id}")
	public Employee fetchEmployeeById(@PathVariable("id") long empId) {
		return this.employeeService.fetchEmployeeById(empId);
	}

	@GetMapping("/search")
	public List<Employee> fetchEmployeeByFirstName(String firstName) {
		return this.employeeService.fetchEmployeeByFirstName(firstName);
	}

	@DeleteMapping("/{id}")
	public void deleteEmployeeById(@PathVariable("id") long empId) {
		this.employeeService.deleteEmployeeById(empId);
	}
}
