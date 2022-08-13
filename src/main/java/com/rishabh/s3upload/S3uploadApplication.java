package com.rishabh.s3upload;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class S3uploadApplication {

	public static void main(String[] args) {
		SpringApplication.run(S3uploadApplication.class, args);
	}

	@Bean("AmazonS3Client")
    public AmazonS3 amazonS3Init() {
		return AmazonS3ClientBuilder
				.standard()
				.build();
	}

}
