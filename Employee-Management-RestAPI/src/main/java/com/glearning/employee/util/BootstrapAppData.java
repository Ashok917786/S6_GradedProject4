package com.glearning.employee.util;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.glearning.employee.entity.Employee;
import com.glearning.employee.entity.Role;
import com.glearning.employee.entity.User;
import com.glearning.employee.repository.EmployeeRepository;
import com.glearning.employee.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class BootstrapAppData {

	private final EmployeeRepository employeeRepository;
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	@EventListener(ApplicationReadyEvent.class)
	public void insertEmployees(ApplicationReadyEvent event) {
		Employee emp1 = new Employee();
		emp1.setFirstName("ramesh");
		emp1.setLastName("sharma");
		emp1.setEmail("ramesh@gamil.com");
		this.employeeRepository.save(emp1);

		Employee emp2 = new Employee();
		emp2.setFirstName("mamesh");
		emp2.setLastName("Gupta");
		emp2.setEmail("mamesh@gamil.com");
		this.employeeRepository.save(emp2);
		/* ..................................................................... */

		User mahesh = new User();
		mahesh.setUsername("mahesh");
		mahesh.setPassword(this.passwordEncoder.encode("root"));
		mahesh.setEmailAddress("mahesh@gmail.com");

		Role maheshRole = new Role();
		maheshRole.setRoleName("ADMIN");

		maheshRole.setUser(mahesh);
		mahesh.addRole(maheshRole);

		// Ramesh
		User ramesh = new User();
		ramesh.setUsername("ramesh");
		ramesh.setPassword(this.passwordEncoder.encode("root"));
		ramesh.setEmailAddress("ramesh@gmail.com");

		Role rameshRole = new Role();
		rameshRole.setRoleName("USER");

		rameshRole.setUser(ramesh);
		ramesh.addRole(rameshRole);

		this.userRepository.save(mahesh);
		this.userRepository.save(ramesh);
	}
}
