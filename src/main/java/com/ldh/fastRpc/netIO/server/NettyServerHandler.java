package com.ldh.fastRpc.netIO.server;
import com.alibaba.fastjson.JSON;
import com.ldh.fastRpc.netIO.RequestDTO;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.SocketUtils;
import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @program: FastRPC
 * @description: netty handler
 * @author: Donghui Li
 * @create: 2021-11-19 14:22
 */
@Component
public class NettyServerHandler extends ChannelHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {

        ByteBuf buf = (ByteBuf) msg;

        String recieved = getMessage(buf);
        System.err.println("服务器接收到客户端消息：" + recieved);

        try {
            String ans = getAns(recieved);
            ctx.writeAndFlush(getSendByteBuf(ans));
            System.err.println("服务器回复消息："+ans);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    /**
     * 从ByteBuf中获取信息 使用UTF-8编码返回
     */
    private String getMessage(ByteBuf buf) {

        byte[] con = new byte[buf.readableBytes()];
        buf.readBytes(con);
        try {
            return new String(con, "UTF8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将utf-8编码的消息转换为byte流
     * @param message
     * @return
     * @throws UnsupportedEncodingException
     */
    private ByteBuf getSendByteBuf(String message)
            throws UnsupportedEncodingException {

        byte[] req = message.getBytes("UTF-8");
        ByteBuf pingMessage = Unpooled.buffer();
        pingMessage.writeBytes(req);

        return pingMessage;
    }

    private String getAns(String receiveMsg){
        if(StringUtils.isEmpty(receiveMsg)){
            return null;
        }
        try{
            RequestDTO requestDTO = JSON.parseObject(receiveMsg,RequestDTO.class);
            return invokeMethod(requestDTO);
        }catch (Exception e){
            return "";
        }
    }

    private String invokeMethod(RequestDTO requestDTO){
        try {
            Class<?> zclass = Class.forName(requestDTO.getFullyQualifiedName());
            Method method = zclass.getMethod(requestDTO.getMethodName(), requestDTO.getParameterTypes());
            Object invoke = method.invoke(zclass.newInstance(), requestDTO.getParam());
            System.out.println(invoke);
            return JSON.toJSONString(invoke);
        }catch (Exception e){
            System.out.println(e);
            return "";
        }
    }

    public static void main(String[] args) {
        RequestDTO requestDTO = RequestDTO.builder()
                .fullyQualifiedName("com.ldh.fastRpc.test.addService.impl.TestAddImpl")
                .methodName("add")
                .parameterTypes(new Class[]{int.class,int.class})
                .param(new Object[]{1,2})
                .returnTypes(int.class)
                .build();
        NettyServerHandler nettyServerHandler = new NettyServerHandler();
        System.out.println(nettyServerHandler.invokeMethod(requestDTO).toString());
    }
}