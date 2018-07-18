package com.im.vent.baiduapi;

import com.google.gson.JsonArray;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

/**
 * Created by hecy on 2018/7/18.
 */
/*
 * unit对话服务
 */
@Component
public class UnitService {
    /**
     * 重要提示代码中所需工具类
     * FileUtil,Base64Util,HttpUtil,GsonUtils请从
     * https://ai.baidu.com/file/658A35ABAB2D404FBF903F64D47C1F72
     * https://ai.baidu.com/file/C8D81F3301E24D2892968F09AE1AD6E2
     * https://ai.baidu.com/file/544D677F5D4E4F17B4122FBD60DB82B3
     * https://ai.baidu.com/file/470B3ACCA3FE43788B5A963BF0B625F3
     * 下载
     */
    public String utterance(String msg, String user_id, String token) {
        // 请求URL
        String talkUrl = "https://aip.baidubce.com/rpc/2.0/unit/bot/chat";
        try {
            // 请求参数
            String params = "{" +
                    "\"bot_session\":\"\"," +
                    "\"log_id\":\"7758521\"," +
                    "\"request\":{" +
                    "   \"bernard_level\":1," +
                    "   \"client_session\":\"{" +
                    "       \\\"client_results\\\":\\\"\\\", " +
                    "       \\\"candidate_options\\\":[]" +
                    "   }\"," +
                    "   \"query\":\""+msg+"\"," +
                    "   \"query_info\":{" +
                    "       \"source\":\"KEYBOARD\"," +
                    "       \"type\":\"TEXT\"" +
                    "   }," +
                    "   \"updates\":\"\"," +
                    "   \"user_id\":\""+user_id+"\"" +
                    "}," +
                    "\"bot_id\":7835," +
                    "\"version\":\"2.0\"" +
                    "}";
//            String accessToken =  "24.04caed631a99a706b68d747e1cb38370.2592000.1534493845.282335-11547509";
            String result = HttpUtil.post(talkUrl, token, "application/json", params);
            JSONObject jsonObject = new JSONObject(result);

            JSONObject jsonObject1 = jsonObject.getJSONObject("response");
            JSONArray jsonArray =  jsonObject1.getJSONArray("action_list");
            JSONObject jsonArray0 =  jsonArray.getJSONObject(0);
            return (String) jsonArray0.get("say");


         } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void main(String[] args) {


//           utterance("烦","001","");
    }
}
