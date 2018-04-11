package com.greenfoxacademy.secondproject;

import com.greenfoxacademy.secondproject.model.Todo;
import com.greenfoxacademy.secondproject.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SecondprojectApplication implements CommandLineRunner{

	@Autowired
	Repository repository;

	public static void main(String[] args) {
		SpringApplication.run(SecondprojectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}
}
