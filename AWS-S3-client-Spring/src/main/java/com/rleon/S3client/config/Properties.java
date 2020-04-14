package com.rleon.S3client.config;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@ConfigurationProperties(prefix = "amazonproperties")
@Getter
@Component
public class Properties {

    private String endpointurl;
    private String bucketname;
    private String accesskey;
    private String secretkey;
}
