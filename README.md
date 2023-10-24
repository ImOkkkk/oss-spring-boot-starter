## 使用

1. 克隆项目到本地

   ```shell
   git clone git@github.com:ImOkkkk/oss-spring-boot-starter.git
   ```

2. 构建项目

   ```shell
   mvn install
   ```

3. 引入坐标

   ```xml
       <dependency>
         <groupId>com.imokkkk</groupId>
         <artifactId>oss-spring-boot-starter</artifactId>
         <version>1.0.0</version>
       </dependency>
   ```

4. 配置文件填写配置项

   ```properties
   storage.type=oss
   
   #腾讯云对象存储
   cos.appId=147258369
   cos.secretID=
   cos.secretKey=
   cos.region=ap-shanghai
   cos.bucket.name=
   
   #阿里云对象存储
   oss.endpoint=oss-cn-chengdu.aliyuncs.com
   oss.accessKeyId=
   oss.accessKeySecret=
   oss.bucket.name=
   ```

5. 代码中直接使用

   ```java
   @Service
   public class FileServiceImpl implements FileService {
   
       @Autowired private ObjectStorageManager objectStorageManager;
   
       private Logger logger = LoggerFactory.getLogger(IFileManagerImpl.class);
   
       @Override
       public String upload(MultipartFile file) {
           String filename = file.getOriginalFilename();
           String path = "";
           try {
               path = objectStorageManager.uploadFile(file.getBytes(), "static/" + filename);
           } catch (IOException e) {
               logger.error("文件{}上传失败！", filename, e);
           }
           return path;
       }
   
       @Override
       public void deleteFile(String fileUrl) {
           objectStorageManager.deleteFile(URLUtil.getPath(fileUrl));
       }
   }
   ```

   