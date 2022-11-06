package com.glearning.employee.service;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.glearning.employee.entity.Employee;
import com.glearning.employee.repository.EmployeeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeService {

	private final EmployeeRepository employeeRepository;

	public Employee saveEmployee(Employee employee) {
		return this.employeeRepository.save(employee);
	}

	public List<Employee> fetchAllEmployee() {
		return employeeRepository.findAll();
	}

	public List<Employee> fetchAllEmployee(Direction direction) {
		return employeeRepository.findAll(Sort.by(direction, "firstName"));
	}

	public Employee fetchEmployeeById(long empId) {
		return this.employeeRepository.findById(empId).orElseThrow();
	}

	public List<Employee> fetchEmployeeByFirstName(String firstName) {

		Employee employee = new Employee();
		employee.setFirstName(firstName);
		ExampleMatcher exampleMatcher = ExampleMatcher.matchingAny()
				.withMatcher("firstName", ExampleMatcher.GenericPropertyMatchers.exact())
				.withIgnorePaths("emp_id", "lastName", "emailId");
		Example<Employee> firstNameExample = Example.of(employee, exampleMatcher);

		return this.employeeRepository.findAll(firstNameExample);
	}

	public void deleteEmployeeById(long empId) {
		this.employeeRepository.deleteById(empId);
	}
}
