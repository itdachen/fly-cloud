package com.github.itdachen.gateway.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Description: 返回消息封装
 * Created by 王大宸 on 2023-09-03 22:07
 * Created with IntelliJ IDEA.
 */
public class MsgBodyUtils {


    public static String msg(int status, String msg) {
        return "{\"success\":\"false\",\"status\":\"" + status + "\", \"msg\":\"" + msg + "\",\"data\": null}";
    }

    public static Map<String, Object> resMsg(int status, String msg) {
        Map<String, Object> map = new HashMap<>();
        map.put("success", false);
        map.put("status", status);
        map.put("msg", msg);
        map.put("data", null);
        return map;
    }

}
