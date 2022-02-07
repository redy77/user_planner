package ru.victor.user_planner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class UserPlannerApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserPlannerApplication.class, args);
	}//TODO Сделать DTO
}
