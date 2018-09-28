package com.im.vent.netty;

import com.im.vent.controller.MyController;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.ImmediateEventExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;

/**
 * 代码清单 12-4 引导服务器
 */
public class ChatServer {
    private static Logger logger = LoggerFactory.getLogger(MyController.class);

    //创建 DefaultChannelGroup，其将保存所有已经连接的 WebSocket Channel
    private final ChannelGroup channelGroup = new DefaultChannelGroup(ImmediateEventExecutor.INSTANCE);
    private final EventLoopGroup group = new NioEventLoopGroup();
    private final EventLoopGroup groupworker = new NioEventLoopGroup();
    private Channel channel;

    public ChannelFuture start(InetSocketAddress address) {
        logger.info("ChatServer start ");
        //引导服务器
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(group, groupworker)
                .channel(NioServerSocketChannel.class)
                .childHandler(createInitializer(channelGroup))
                .option(ChannelOption.SO_BACKLOG, 1024)
                .childOption(ChannelOption.SO_KEEPALIVE, true);

        ChannelFuture future = bootstrap.bind(address);
        future.syncUninterruptibly();
        channel = future.channel();
        return future;
    }

    //创建 ChatServerInitializer
    protected ChannelInitializer<Channel> createInitializer(ChannelGroup group) {
        logger.info("ChatServer createInitializer ");
        return new ChatServerInitializer(group);
    }

    //处理服务器关闭，并释放所有的资源
    public void destroy() {
        if (channel != null) {
            channel.close();
        }
        channelGroup.close();
        group.shutdownGracefully();
    }

    public static void imEntrance() throws Exception {

        int port = 9000;
        final ChatServer endpoint = new ChatServer();
        ChannelFuture future = endpoint.start(
                new InetSocketAddress(port));
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                endpoint.destroy();
            }
        });
        future.channel().closeFuture().syncUninterruptibly();
    }
}
