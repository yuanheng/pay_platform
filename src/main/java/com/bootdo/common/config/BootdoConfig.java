package com.bootdo.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "bootdo")
public class BootdoConfig {
    //上传路径
    private String uploadPath;

    private String username;

    private String password;

    private Integer veriCodeNeeded;

    public Boolean isVeriCodeNeeded() {
        return veriCodeNeeded == Constant.YES;
    }

    public void setVeriCodeNeeded(Integer veriCodeNeeded) {
        this.veriCodeNeeded = veriCodeNeeded;
    }

    public String getUploadPath() {
        return uploadPath;
    }

    public void setUploadPath(String uploadPath) {
        this.uploadPath = uploadPath;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
