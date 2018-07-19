package com.im.vent.controller;


import com.im.vent.bean.MessageInfo;
import com.im.vent.server.MyServer;
import com.im.vent.utils.HttpClientUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


@RestController
public class MyController {
    private static Logger logger = LoggerFactory.getLogger(MyController.class);

    @Autowired
    MyServer server;


    @Autowired
    HttpClientUtil httpClientUtil;

    @PostMapping("/sendMessage")
    public String sendMessage(@RequestParam(value = "msg") String msg, HttpServletRequest request) {

        logger.info("msg: " + msg);
        String ip = httpClientUtil.getIp(request);
        logger.info("ip: " + ip);
        MessageInfo messageInfo = new MessageInfo(msg, ip);
        server.saveMessage(messageInfo);
        String baiduReply = server.getReturnMsgfromBaidu(msg);
        server.updateMessageById(messageInfo.getId(), baiduReply);
        return baiduReply;

    }


}
