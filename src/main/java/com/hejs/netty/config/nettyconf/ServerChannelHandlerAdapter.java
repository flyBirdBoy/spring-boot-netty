package com.hejs.netty.config.nettyconf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.net.InetAddress;

/**
 * @Author: hejis
 * @Description: 多线程共享
 * @Date: Create in 14:55 2018/7/9
 * @Modified By:
 */
@Component
@ChannelHandler.Sharable
public class ServerChannelHandlerAdapter extends ChannelHandlerAdapter {

    /**
     * 日志处理
     */
    private Logger logger = LoggerFactory.getLogger(ServerChannelHandlerAdapter.class);
    private String result="";
    /**
     * 注入请求分排器
     */
    @Resource
    private RequestDispatcher dispatcher;

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        if(! (msg instanceof FullHttpRequest)){
            result = "未知请求!";
            send(ctx,result,HttpResponseStatus.BAD_REQUEST);
            return;
        }
        FullHttpRequest httpRequest = (FullHttpRequest)msg;
        try{
            //获取路径
            String path = httpRequest.uri();
            //获取参数
            String body = getBody(httpRequest);
            //获取请求方法
            HttpMethod method = httpRequest.method();
            //如果不是这个路径，就直接返回错误
            if(!"/test".equalsIgnoreCase(path)){
                result = "非法请求!";
                send(ctx,result,HttpResponseStatus.BAD_REQUEST);
                return;
            }
            System.out.println("接收到:"+method+" 请求");
            //如果是GET请求
            if(HttpMethod.GET.equals(method)){
                //接受到的消息，做业务逻辑处理...
                System.out.println("body:"+body);
                result="GET请求";
                send(ctx,result,HttpResponseStatus.OK);
                return;
            }
            //如果是POST请求
            if(HttpMethod.POST.equals(method)){
                //接受到的消息，做业务逻辑处理...
                System.out.println("body:"+body);
                result="POST请求";
                send(ctx,result,HttpResponseStatus.OK);
                return;
            }

            //如果是PUT请求
            if(HttpMethod.PUT.equals(method)){
                //接受到的消息，做业务逻辑处理...
                System.out.println("body:"+body);
                result="PUT请求";
                send(ctx,result,HttpResponseStatus.OK);
                return;
            }
            //如果是DELETE请求
            if(HttpMethod.DELETE.equals(method)){
                //接受到的消息，做业务逻辑处理...
                System.out.println("body:"+body);
                result="DELETE请求";
                send(ctx,result,HttpResponseStatus.OK);
                return;
            }
        }catch(Exception e){
            System.out.println("处理请求失败!");
            e.printStackTrace();
        }finally{
            //释放请求
            httpRequest.release();
        }
//        MethodInvokeMeta invokeMeta = (MethodInvokeMeta)msg;
//        // 屏蔽toString()方法
//        if (invokeMeta.getMethodName().endsWith("toString()")
//                && !"class java.lang.String".equals(invokeMeta.getReturnType().toString())){
//            logger.info("客户端传入参数 :{},返回值：{}",
//                    invokeMeta.getArgs(), invokeMeta.getReturnType());
//            dispatcher.dispatcher(ctx, invokeMeta);
//        }
    }

    /**
     * 获取body参数
     * @param request
     * @return
     */
    private String getBody(FullHttpRequest request){
        ByteBuf buf = request.content();
        return buf.toString(CharsetUtil.UTF_8);
    }

    /**
     * 发送的返回值
     * @param ctx     返回
     * @param context 消息
     * @param status 状态
     */
    private void send(ChannelHandlerContext ctx, String context,HttpResponseStatus status) {
        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, status, Unpooled.copiedBuffer(context, CharsetUtil.UTF_8));
        response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain; charset=UTF-8");
        ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
    }

    /*
     * 建立连接时，返回消息
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("连接的客户端地址:" + ctx.channel().remoteAddress());
        ctx.writeAndFlush("客户端"+ InetAddress.getLocalHost().getHostName() + "成功与服务端建立连接！ ");
        super.channelActive(ctx);
    }

}
