package com.im.vent.netty;

import com.im.vent.controller.MyController;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 代码清单 12-2 处理文本帧
 *
 * @author <a href="mailto:norman.maurer@gmail.com">Norman Maurer</a>
 */
//扩展 SimpleChannelInboundHandler，并处理 TextWebSocketFrame 消息
public class TextWebSocketFrameHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    private final ChannelGroup group;
    private static Logger logger = LoggerFactory.getLogger(TextWebSocketFrameHandler.class);

    public TextWebSocketFrameHandler(ChannelGroup group) {
        this.group = group;
    }

    //重写 userEventTriggered()方法以处理自定义事件
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        logger.info(" userEventTriggered ");

        //如果该事件表示握手成功，则从该 ChannelPipeline 中移除HttpRequest-Handler，因为将不会接收到任何HTTP消息了
        if (evt == WebSocketServerProtocolHandler.ServerHandshakeStateEvent.HANDSHAKE_COMPLETE) {
            ctx.pipeline().remove(HttpRequestHandler.class);
            //(2) 将新的 WebSocket Channel 添加到 ChannelGroup 中，以便它可以接收到所有的消息
            group.add(ctx.channel());
            //通知所有已经连接的 WebSocket 客户端新的客户端已经连接上了
            group.writeAndFlush(new TextWebSocketFrame("[system]: 当前在线人数 【" + group.size() + "】"));
        } else {
            super.userEventTriggered(ctx, evt);
        }
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        //(3) 增加消息的引用计数，并将它写到 ChannelGroup 中所有已经连接的客户端

        Channel currentChannel = ctx.channel();
        logger.info(" channelRead0 ");
        group.forEach(channel -> {

            if(channel == currentChannel) {
                channel.writeAndFlush(new TextWebSocketFrame("[self]: " + msg.text()));
            } else {
                channel.writeAndFlush(new TextWebSocketFrame("[other]: "+ msg.text()));
            }
        });
    }
    /**
     * Do nothing by default, sub-classes may override this method.
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel currentChannel = ctx.channel();
        logger.info(" handlerRemoved ");
        group.writeAndFlush(new TextWebSocketFrame("[system] : 当前在线人数 【" + group.size() + "】"));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)	throws Exception {
        Channel incoming = ctx.channel();
        logger.info(" exceptionCaught ");

        System.out.println("Client:"+incoming.remoteAddress()+"异常");
        // 当出现异常就关闭连接
        cause.printStackTrace();
        ctx.close();
    }

}
