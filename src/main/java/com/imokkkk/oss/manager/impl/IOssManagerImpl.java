package com.imokkkk.oss.manager.impl;


import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectRequest;

import org.springframework.beans.factory.annotation.Value;

import java.io.InputStream;

import javax.annotation.Resource;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;


@Service
@DependsOn("ossConfig")
public class IOssManagerImpl extends BaseObjectStorageManagerImpl {

    @Value("${oss.endpoint}")
    private String endpoint;

    @Value(value = "${oss.bucket.name}")
    protected String bucketName;

    @Resource private OSSClient ossClient;

    @Override
    public void deleteFile(String filePath) {
        if (filePath.startsWith("/")) {
            filePath = filePath.substring(1);
        }
        ossClient.deleteObject(bucketName, filePath);
    }

    @Override
    public void upload(InputStream inputStream, int length, String cosFilePath) {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(length);
        PutObjectRequest putObjectRequest =
                new PutObjectRequest(bucketName, cosFilePath, inputStream, objectMetadata);
        ossClient.putObject(putObjectRequest);
    }

    @Override
    public String getFileUrl(String filePath) {
        return "https://" + bucketName + "." + endpoint + "/" + filePath;
    }
}
