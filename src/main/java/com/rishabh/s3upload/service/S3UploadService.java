package com.rishabh.s3upload.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rishabh.s3upload.model.MovieEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;

@Service
public class S3UploadService {

    private final AmazonS3 amazonS3;
    private final String bucketName;
    private ObjectMapper objectMapper;

    @Autowired
    public S3UploadService(@Qualifier("AmazonS3Client") final AmazonS3 amazonS3,
                           @Value("${s3Bucket}") String bucketName,
                           ObjectMapper objectMapper) {
        this.amazonS3 = amazonS3;
        this.bucketName = bucketName;
        this.objectMapper = objectMapper;
    }

    public void uploadToS3(final MovieEvent movieEvent) throws JsonProcessingException {

        final String s3Key = "/movieEvent/MovieEvent-" + movieEvent.getId() + ".json";
        final byte[] bytesToWrite = objectMapper.writeValueAsBytes(movieEvent);

        final ObjectMetadata omd = new ObjectMetadata();
        omd.setContentLength(bytesToWrite.length);

        final PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, s3Key, new ByteArrayInputStream(bytesToWrite), omd);
        amazonS3.putObject(putObjectRequest);
    }

}
