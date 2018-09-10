package com.im.vent.task;

import com.im.vent.bean.MessageInfo;
import com.im.vent.server.MyServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

/**
 * 处理Im信息
 */
@Component
public class Op4ImMessage {
    @Autowired
    MyServer server;

    @Async("saveImMessage") // @Async所修饰的函数不要定义为static类型，这样异步调用不会生效
    public Future<MessageInfo> saveImMessage(MessageInfo messageInfo) {
        server.saveMessage(messageInfo);
        return new AsyncResult<>(messageInfo);
    }

    @Async("updateMessageById")
    public void updateMessageById(Long messageInfoId, String baiduReply, Future<MessageInfo> task1) {
        try {
            boolean flag = true;
            while (flag) {
                if (task1.isDone()) {
                    server.updateMessageById(messageInfoId, baiduReply);
                    flag = false;
                } else if (task1.isCancelled()) {
                    flag = false;
                    Thread.sleep(1000);
                } else {
                    Thread.sleep(1000);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}

