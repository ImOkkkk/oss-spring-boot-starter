package com.imokkkk.oss.manager.impl;

import com.imokkkk.oss.manager.ObjectStorageManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;


public abstract class BaseObjectStorageManagerImpl implements ObjectStorageManager {

    private static final Logger logger =
            LoggerFactory.getLogger(BaseObjectStorageManagerImpl.class);

    @Override
    public String uploadFile(byte[] fileBytes, String filePath) {
        InputStream inputStream = null;
        try {
            inputStream = new ByteArrayInputStream(fileBytes);
            upload(inputStream, fileBytes.length, filePath);
        } catch (Exception e) {
            logger.error("上传文件：【{}】失败！", filePath, e);
            throw new RuntimeException(String.format("上传文件：【%s】失败！", filePath));
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return getFileUrl(filePath);
    }

    public abstract void upload(InputStream inputStream, int length, String cosFilePath);

    public abstract String getFileUrl(String filePath);
}
