package com.hejs.netty.config.nettyconf;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author: hejis
 * @Description: 读取yml配置文件中的信息
 * @Date: Create in 15:01 2018/7/9
 * @Modified By:
 */
@Component
@ConfigurationProperties(prefix = "netty")
public class NettyConfig {

    private int port;

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}