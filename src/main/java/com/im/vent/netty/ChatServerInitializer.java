package com.im.vent.netty;

import com.im.vent.controller.MyController;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.group.ChannelGroup;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 代码清单 12-3 初始化 ChannelPipeline
 *
 * @author <a href="mailto:norman.maurer@gmail.com">Norman Maurer</a>
 */
//扩展了 ChannelInitializer
public class ChatServerInitializer extends ChannelInitializer<Channel> {
    private final ChannelGroup group;
    private static Logger logger = LoggerFactory.getLogger(MyController.class);

    public ChatServerInitializer(ChannelGroup group) {
        this.group = group;
    }

    @Override
    //将所有需要的 ChannelHandler 添加到 ChannelPipeline 中
    protected void initChannel(Channel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        // HttpServerCodec 将字节解码为 HttpRequest\HttpContent\LastHttpContent. 并将它们编码为字节
        pipeline.addLast(new HttpServerCodec());
        pipeline.addLast(new ChunkedWriteHandler());
        // HttpObjectAggregator将一个HttpMessage和跟随它的多个HttpContent聚合为单个FullHttpRequest或者FullRespose
        pipeline.addLast(new HttpObjectAggregator(64 * 1024));
        pipeline.addLast(new HttpRequestHandler("/ws"));
        pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));
        pipeline.addLast(new TextWebSocketFrameHandler(group));
    }
}
