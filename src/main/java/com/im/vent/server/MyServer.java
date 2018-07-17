package com.im.vent.server;

import com.im.vent.baiduapi.AuthService;
import com.im.vent.baiduapi.bean.BaiduInfo;
import com.im.vent.controller.MyController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyServer {
    private static Logger logger = LoggerFactory.getLogger(MyController.class);


    @Autowired
    BaiduInfo baiduInfo;


    public String getReplyByBaidu(String msg) {
        String msgs = AuthService.getAuth(baiduInfo.getAk(), baiduInfo.getSk());
        return msgs;
    }


}
