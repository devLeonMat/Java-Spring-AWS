package com.rleon.S3client.config.aws.s3;

import com.amazonaws.services.s3.AmazonS3;

public class AWSS3Service {
    private final AmazonS3 s3Client;

    public AWSS3Service(AmazonS3 amazonS3Client) {
        this.s3Client = amazonS3Client;
    }

    /**
     * validate bucket exist
     **/
    public boolean existBucket(String bucketName) {
        return s3Client.doesBucketExistV2(bucketName);
    }


}
