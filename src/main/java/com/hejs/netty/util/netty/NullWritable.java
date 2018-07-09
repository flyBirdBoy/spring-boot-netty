package com.hejs.netty.util.netty;

import java.io.Serializable;

/**
 * @Author: hejis
 * @Description: 服务器可能返回空的处理
 * @Date: Create in 15:17 2018/7/9
 * @Modified By:
 */
public class NullWritable implements Serializable {

    private static final long serialVersionUID = -8191640400484155111L;
    private static NullWritable instance = new NullWritable();

    private NullWritable() {
    }

    public static NullWritable nullWritable() {
        return instance;
    }

}
