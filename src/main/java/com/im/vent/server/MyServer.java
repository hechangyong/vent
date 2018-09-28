package com.im.vent.server;

import com.im.vent.baiduapi.AuthService;
import com.im.vent.baiduapi.UnitService;
import com.im.vent.baiduapi.bean.BaiduInfo;
import com.im.vent.bean.BaiduToken;
import com.im.vent.bean.BaiduTokenMapper;
import com.im.vent.bean.MessageInfo;
import com.im.vent.bean.MessageInfoMapper;
import com.im.vent.controller.MyController;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MyServer {
    private static Logger logger = LoggerFactory.getLogger(MyController.class);


    @Autowired
    BaiduInfo baiduInfo;

    @Autowired
    UnitService unitService;

    @Autowired
    BaiduTokenMapper baiduTokenMapper;

    @Autowired
    MessageInfoMapper messageInfoMapper;

    public Map<String, String> baiduTokenMap = new HashMap<>();

    /**
     * 更新百度 token
     */
    public void updateBaiduToken() {
        try {
            BaiduToken msgs = AuthService.getAuth(baiduInfo.getAk(), baiduInfo.getSk());
            baiduTokenMapper.insert(msgs);
            baiduTokenMap.put("baidutoken", msgs.getToken());
        } catch (Exception e) {
            logger.error("更新百度token出现异常：{}", e.getMessage(), e);
        }
    }


    public String getReturnMsgfromBaidu(String msg, String userid) {
        String accessToken = getToken();
        return unitService.utterance(msg, userid, accessToken);
    }

    public String getToken() {
        if (baiduTokenMap.get("baidutoken") != null) {
            return baiduTokenMap.get("baidutoken");
        } else {
            BaiduToken msgs = baiduTokenMapper.getOneByDate();
            logger.info("缓存中没有token, 查询数据库【{}】", msgs);
            baiduTokenMap.put("baidutoken", msgs.getToken());
            return msgs.getToken();
        }
    }


    public int saveMessage(MessageInfo messageInfo) {
        return messageInfoMapper.insertMessageinfo(messageInfo);
    }

    public void updateMessageById(Long id, String baiduReply) {
        logger.info("更新数据【{}】【{}】", id, baiduReply);
        MessageInfo messageInfo = new MessageInfo();
        messageInfo.setId(id);
        messageInfo.setReplymsg(baiduReply);
        messageInfoMapper.update(messageInfo);
    }
}
