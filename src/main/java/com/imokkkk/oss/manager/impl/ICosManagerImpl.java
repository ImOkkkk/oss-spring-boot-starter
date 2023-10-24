package com.imokkkk.oss.manager.impl;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectRequest;

import org.springframework.beans.factory.annotation.Value;

import java.io.InputStream;

import javax.annotation.Resource;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;


@Service
@DependsOn("cosConfig")
public class ICosManagerImpl extends BaseObjectStorageManagerImpl {

    @Value(value = "${cos.bucket.name}")
    private String bucketName;

    @Resource private COSClient cosClient;

    @Override
    public void upload(InputStream inputStream, int length, String cosFilePath) {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(length);
        PutObjectRequest putObjectRequest =
                new PutObjectRequest(bucketName, cosFilePath, inputStream, objectMetadata);
        cosClient.putObject(putObjectRequest);
    }

    @Override
    public String getFileUrl(String filePath) {
        return cosClient.getObjectUrl(bucketName, filePath).toString();
    }

    @Override
    public void deleteFile(String filePath) {
        cosClient.deleteObject(bucketName, filePath);
    }
}
