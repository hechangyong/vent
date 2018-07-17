package com.im.vent.baiduapi.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:baidu.properties")
@ConfigurationProperties(prefix = "baidu.info")
public class BaiduInfo {
    private String sk;
    private String ak;
    private String appId;

    public String getSk() {
        return sk;
    }

    public void setSk(String sk) {
        this.sk = sk;
    }

    public String getAk() {
        return ak;
    }

    public void setAk(String ak) {
        this.ak = ak;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }
}
