package com.atguigu.daijia.driver.config;

import com.atguigu.daijia.common.login.GuiguLogin;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/*@Project_name:daijia-parent
@Create 2024/11/5 下午4:58
Description:
*/
@Data
@Component
@ConfigurationProperties(prefix = "tencent.cloud")
public class TencentCloudProperties {
    private String secretId;
    private String secretKey;
    private String region;
    private String bucketPrivate;
    private String persionGroupId;
}