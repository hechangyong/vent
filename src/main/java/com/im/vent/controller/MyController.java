package com.im.vent.controller;


import com.im.vent.bean.MessageInfo;
import com.im.vent.server.MyServer;
import com.im.vent.task.Op4ImMessage;
import com.im.vent.utils.HttpClientUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.Future;


@RestController
public class MyController {
    private static Logger logger = LoggerFactory.getLogger(MyController.class);

    @Autowired
    MyServer server;

    @Autowired
    Op4ImMessage op4ImMessage;


    @Autowired
    HttpClientUtil httpClientUtil;

    @PostMapping("/sendMessage")
    public String sendMessage(@RequestParam(value = "msg") String msg, HttpServletRequest request) {

        logger.info("msg: " + msg);
        String ip = httpClientUtil.getIp(request);
        logger.info("ip: " + ip);
        MessageInfo dbInfo = new MessageInfo(msg, ip);
        String baiduReply = server.getReturnMsgfromBaidu(msg, ip);
        try {
            Future<MessageInfo> task1 = op4ImMessage.saveImMessage(dbInfo);
            op4ImMessage.updateMessageById(dbInfo.getId(), baiduReply, task1);
        } catch (Exception e) {
            logger.error("异步保存聊天信息出现异常，{}", e.getMessage(), e);
        }
        return baiduReply;

    }

    @PostMapping("/test")
    public String test() {

        server.updateBaiduToken();
        return "";

    }


}
