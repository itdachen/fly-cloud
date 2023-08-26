package com.github.itdachen.actuator.send.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.itdachen.actuator.send.ITakeNotifySendService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Description: 记录消息
 * Created by 王大宸 on 2023-07-16 1:32
 * Created with IntelliJ IDEA.
 */
@Service
public class TakeNotifySendServiceImpl implements ITakeNotifySendService {
    private static final Logger logger = LoggerFactory.getLogger(TakeNotifySendServiceImpl.class);
    //    private static final String NOTIFY_URI = "http://biz/biz/dashboard/admin/open/wallboard/take/notes/send/notify";
    private static final String NOTIFY_URI = "http://127.0.0.1:7080/admin/dashboard/admin/open/wallboard/take/notes/send/notify";

    @Override
    public void sendNotify(JSONObject body) {
        try {
//            HttpUtil.createPost(NOTIFY_URI)
//                    .header("Content-Type", "application/json")
//                    .body(String.valueOf(body))
//                    .execute()
//                    .body();
        } catch (Exception e) {
            logger.error("消息推送失败, {}", e.getMessage());
        }
    }

}
