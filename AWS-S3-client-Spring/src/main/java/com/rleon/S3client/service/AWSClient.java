package com.rleon.S3client.service;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.rleon.S3client.config.Properties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AWSClient {

    private AmazonS3 s3Cient;

    @Autowired
    Properties properties;

    private void initAws(){
        AWSCredentials credentials = new BasicAWSCredentials(properties.getAccesskey(), properties.getSecretkey());
    }


}
