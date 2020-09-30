package com.test.socket;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

import javax.naming.ldap.HasControls;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * socket
 * Netty(NIO)方式
 */
public class MySocketNettyServer {
    HashMap<String, Object> handlerMap = new HashMap<>();

    public void publisher(Object server, int port){
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup wokerGroup = new NioEventLoopGroup();

        try{
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            //在服务器端的handler()方法表示对bossGroup起作用，而childHandler表示对wokerGroup起作用
            serverBootstrap.group(bossGroup,wokerGroup).channel(NioServerSocketChannel.class)
                    .childHandler(new MyServerInitializer());//进行一些设置

            ChannelFuture channelFuture = serverBootstrap.bind(port).sync();
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            wokerGroup.shutdownGracefully();
        }

    }

    class MyServerInitializer extends ChannelInitializer<SocketChannel> {
        @Override
        protected void initChannel(SocketChannel ch) throws Exception {
            ChannelPipeline pipeline = ch.pipeline();

            pipeline.addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
            pipeline.addLast(new LengthFieldPrepender(4));
            //字符串解码
            pipeline.addLast(new StringDecoder(CharsetUtil.UTF_8));
            //字符串编码
            pipeline.addLast(new StringEncoder(CharsetUtil.UTF_8));
            //自己定义的处理器
            pipeline.addLast(new NettySocketHandler());
        }
    }

    class NettySocketHandler extends ChannelInboundHandlerAdapter {
        HashMap<String, Object> handlerMap = new HashMap<>();

        public NettySocketHandler(){
        }
        public NettySocketHandler(HashMap<String, Object> handlerMap){
            this.handlerMap = handlerMap;
        }

        /**
         * 功能：读取服务器发送过来的信息
         */
        public void channelRead(ChannelHandlerContext ctx, Object msg){
            String s = (String) msg;
            String result = invoke(s);
            ctx.write(result);
            ctx.flush();
            ctx.close();
        }
    }

    public String invoke(String str){
        return "你好："+str;
    }

    public static void main(String[] args) {
        new MySocketNettyServer().publisher(null,9999);
    }
}
