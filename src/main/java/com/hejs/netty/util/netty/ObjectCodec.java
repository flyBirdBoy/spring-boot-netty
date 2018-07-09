package com.hejs.netty.util.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;

import java.util.List;

/**
 * @Author: hejis
 * @Description:
 * @Date: Create in 15:24 2018/7/9
 * @Modified By:
 */
public class ObjectCodec extends MessageToMessageCodec<ByteBuf, Object> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Object msg, List<Object> out) {
        byte[] data = ObjectSerializerUtils.serilizer(msg);
        ByteBuf buf = Unpooled.buffer();
        buf.writeBytes(data);
        out.add(buf);
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) {
        byte[] bytes = new byte[msg.readableBytes()];
        msg.readBytes(bytes);
        Object deSerilizer = ObjectSerializerUtils.deSerilizer(bytes);
        out.add(deSerilizer);
    }

}
