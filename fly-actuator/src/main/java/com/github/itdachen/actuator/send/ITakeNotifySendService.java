package com.github.itdachen.actuator.send;

import com.alibaba.fastjson.JSONObject;

/**
 * Description:
 * Created by 王大宸 on 2023-07-16 1:31
 * Created with IntelliJ IDEA.
 */
public interface ITakeNotifySendService {

    void sendNotify(JSONObject body);

}
