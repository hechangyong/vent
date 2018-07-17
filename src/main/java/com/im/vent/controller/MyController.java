package com.im.vent.controller;


import com.im.vent.server.MyServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MyController {
    private static Logger logger = LoggerFactory.getLogger(MyController.class);

    @Autowired
    MyServer server;

    @PostMapping("/sendMessage")
    public String sendMessage(@RequestParam(value = "msg") String msg) {
        Long startTime = System.currentTimeMillis();
        logger.info("msg: " + msg);
        server.getReplyByBaidu(msg);
        Long endTime = System.currentTimeMillis();
        return "耗时： " + (startTime - endTime) + " ";
    }


}
