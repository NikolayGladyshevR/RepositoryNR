package ru.gladyshev.springcourse.MyProjectPlusJwt;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MyProjectPlusJwtApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyProjectPlusJwtApplication.class, args);
	}

	@Bean
 public ModelMapper modelMapper(){
		return new ModelMapper();
	}
}
