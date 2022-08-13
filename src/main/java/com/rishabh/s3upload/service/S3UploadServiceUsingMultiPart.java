package com.rishabh.s3upload.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.TransferManagerBuilder;
import com.amazonaws.services.s3.transfer.Upload;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class S3UploadServiceUsingMultiPart {

    private final AmazonS3 amazonS3;
    private TransferManager transferManager;
    private final String bucketName;

    public S3UploadServiceUsingMultiPart(@Qualifier("AmazonS3Client") final AmazonS3 amazonS3,
                                         @Value("${s3Bucket}") String bucketName) {
        this.amazonS3 = amazonS3;
        this.bucketName = bucketName;
    }

    public void uploadFile(final String s3Key, final String filePath) {

        try {
            transferManager = TransferManagerBuilder.standard()
                    .withS3Client(amazonS3)
                    .withMultipartUploadThreshold(104857600L)
                    .withMinimumUploadPartSize(104857600L)
                    .build();
            Upload upload = transferManager.upload(bucketName, s3Key, new File(filePath));

            //perform other task

            //make call blocking to get result
            upload.waitForCompletion();

            System.out.println("Upload completed");
        } catch (Exception ex) {
            final String errMssg = String.format("Exception while sending data to S3 bucket: %s", bucketName);
            throw new RuntimeException(errMssg, ex);
        } finally {
            transferManager.shutdownNow();
        }
    }


}
