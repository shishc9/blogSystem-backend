package icu.shishc.util;

import com.alibaba.fastjson.JSONObject;

/**
 * @date:2021-4-11, 19:00
 * @author:ShiShc
 */

public class JsonUtil {


    public static JSONObject successJson() { return successJson(new JSONObject());}


    public static JSONObject successJson(Object info) {
        JSONObject resultJson = new JSONObject();
        resultJson.put("code", Constants.SUCCESS_CODE);
        resultJson.put("msg", Constants.SUCCESS_MSG);
        resultJson.put("info", info);
        return resultJson;
    }


}
