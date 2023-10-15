package com.example.tutorrequirementservice;

//import the SpringBootApplication annotation
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//annotate the class with the SpringBootApplication annotation
@SpringBootApplication
public class TutorRequirementServiceApplication {

	//main method to run the application
	public static void main(String[] args) {
		//run the SpringApplication with the TutorRequirementServiceApplication class and the args
		SpringApplication.run(TutorRequirementServiceApplication.class, args);
	}

}