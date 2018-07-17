package com.im.vent.server;

import com.im.vent.baiduapi.AuthService;
import com.im.vent.baiduapi.bean.BaiduInfo;
import com.im.vent.bean.BaiduToken;
import com.im.vent.bean.BaiduTokenMapper;
import com.im.vent.controller.MyController;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyServer {
    private static Logger logger = LoggerFactory.getLogger(MyController.class);


    @Autowired
    BaiduInfo baiduInfo;

    @Autowired
    private BaiduTokenMapper baiduTokenMapper;

    public BaiduToken getReplyByBaidu(String msg) {
        BaiduToken msgs = AuthService.getAuth(baiduInfo.getAk(), baiduInfo.getSk());
        baiduTokenMapper.insert(msgs);
        BaiduToken s = baiduTokenMapper.getOne(msgs.getId());
        logger.info("msgs：" + msgs);
        logger.info("msgs：" + s);
        return msgs;
    }


}
