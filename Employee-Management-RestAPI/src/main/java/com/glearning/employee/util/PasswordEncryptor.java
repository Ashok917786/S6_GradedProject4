package com.glearning.employee.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncryptor {

	public static void main(String[] args) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

		String plainText = "root";

		String encodedPassword = "$2a$10$oAODKyTgxj7kGGpzOiUb6.LsMxZADDMhHDZkseLPHV2TtLInVVg8q";

		System.out.println(encodedPassword);

		System.out.println(passwordEncoder.matches(plainText, encodedPassword));
	}

}
