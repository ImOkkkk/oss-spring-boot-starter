package com.imokkkk.oss.config;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.region.Region;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@ConditionalOnProperty(name = "storage.type", havingValue = "cos")
public class CosConfig {

    @Value("${cos.appId:}")
    private String bucketAppId;

    @Value("${cos.secretID:}")
    private String bucketSecretId;

    @Value("${cos.secretKey:}")
    private String bucketSecretKey;

    @Value("${cos.region:}")
    private String bucketRegion;

    @Bean
    public COSClient cosClient() {
        Region region = new Region(bucketRegion);
        ClientConfig clientConfig = new ClientConfig(region);
        return new COSClient(this.credentials(), clientConfig);
    }

    @Bean
    public COSCredentials credentials() {
        return new BasicCOSCredentials(bucketAppId, bucketSecretId, bucketSecretKey);
    }
}
