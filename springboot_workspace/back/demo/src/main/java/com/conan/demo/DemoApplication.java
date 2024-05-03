package com.conan.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.conan.domain.Sample;

import lombok.extern.log4j.Log4j2;

@Log4j2
@SpringBootApplication
public class DemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		Sample sample = new Sample();
		sample.setName("aaa");
		log.info("sample " + sample.getName());
	}
}
