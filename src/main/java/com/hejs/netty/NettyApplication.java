package com.hejs.netty;

import com.hejs.netty.config.nettyconf.NettyServerListener;
import io.netty.channel.ChannelFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.InetSocketAddress;

@SpringBootApplication
public class NettyApplication implements CommandLineRunner {

    @Autowired
    private NettyServerListener nettyServerListener;

    public static void main(String[] args) {
        SpringApplication.run(NettyApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        nettyServerListener.start();
    }


}
