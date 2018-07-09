package com.hejs.netty.config.nettyconf;

/**
 * @Author: hejis
 * @Description: Netty服务器常量
 * @Date: Create in 15:02 2018/7/9
 * @Modified By:
 */
public class NettyConstanct {

    /**
     * 最大线程量
     */
    private static final int MAX_THREADS = 1024;
    /**
     * 数据包最大长度
     */
    private static final int MAX_FRAME_LENGTH = 65535;

    public static int getMaxFrameLength() {
        return MAX_FRAME_LENGTH;
    }

    public static int getMaxThreads() {
        return MAX_THREADS;
    }


}
