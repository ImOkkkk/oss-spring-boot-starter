package com.imokkkk.oss.manager;


public interface ObjectStorageManager {

    String uploadFile(byte[] fileBytes, String filePath);

    void deleteFile(String filePath);
}
