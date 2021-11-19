package com.ldh.fastRpc.netIO.client;

/**
 * @program: FastRPC
 * @description:
 * @author: Donghui Li
 * @create: 2021-11-19 14:25
 */
import com.alibaba.fastjson.JSON;
import com.ldh.fastRpc.netIO.RequestDTO;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.io.UnsupportedEncodingException;

public class NettyClientHandler extends ChannelHandlerAdapter {
    private ByteBuf firstMessage;

    private static final String msg = "";

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        RequestDTO requestDTO = RequestDTO.builder()
                .fullyQualifiedName("com.ldh.fastRpc.test.addService.impl.TestAddImpl")
                .methodName("add")
                .returnTypes(int.class)
                .parameterTypes(new Class[]{int.class,int.class})
                .param(new Object[]{1,2})
                .build();
        byte[] data = JSON.toJSONString(requestDTO).getBytes();
        firstMessage = Unpooled.buffer();
        firstMessage.writeBytes(data);
        ctx.writeAndFlush(firstMessage);
        System.err.println("客户端发送消息:你好，服务器");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        String rev = getMessage(buf);
        System.err.println("客户端收到服务器消息:" + rev);
    }

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
}
