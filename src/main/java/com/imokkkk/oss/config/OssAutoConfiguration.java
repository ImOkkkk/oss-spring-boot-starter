package com.imokkkk.oss.config;

import com.imokkkk.oss.manager.impl.ICosManagerImpl;
import com.imokkkk.oss.manager.impl.IOssManagerImpl;
import com.imokkkk.oss.manager.ObjectStorageManager;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;


@Configuration
@Import({CosConfig.class, OssConfig.class})
public class OssAutoConfiguration {
    @Bean
    @Primary
    @ConditionalOnProperty(name = "storage.type", havingValue = "oss")
    public ObjectStorageManager ossConfig() {
        // 在这里配置并返回阿里云 OSS 的具体实现
        return new IOssManagerImpl();
    }

    @Bean
    @ConditionalOnProperty(name = "storage.type", havingValue = "cos")
    public ObjectStorageManager cosConfig() {
        // 在这里配置并返回腾讯云 COS 的具体实现
        return new ICosManagerImpl();
    }
}
