package com.github.itdachen.actuator.notify;

import com.alibaba.fastjson.JSONObject;
import com.github.itdachen.actuator.send.ITakeNotifySendService;
import de.codecentric.boot.admin.server.domain.entities.Instance;
import de.codecentric.boot.admin.server.domain.entities.InstanceRepository;
import de.codecentric.boot.admin.server.domain.events.InstanceEvent;
import de.codecentric.boot.admin.server.domain.events.InstanceStatusChangedEvent;
import de.codecentric.boot.admin.server.notify.AbstractStatusChangeNotifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Description: 服务状态变更通知
 * Created by 王大宸 on 2023-07-16 1:29
 * Created with IntelliJ IDEA.
 */
@Component
public class AdminActuatorEventNotifierTakeNotes extends AbstractStatusChangeNotifier {
    private static final Logger logger = LoggerFactory.getLogger(AdminActuatorEventNotifierTakeNotes.class);

    /**
     * 消息模板
     */
    private static final String TEMPLATE_INFO = "\n <<<%s>>> \n 【时间】: %s \n 【服务名】: %s \n 【状态】: %s(%s) \n 【服务IP】: %s \n 【详情】: %s";

    /**
     * 标题：系统告警
     */
    private final String TITLE_ALARM = "系统告警";

    /**
     * 标题：系统通知
     */
    private final String TITLE_NOTICE = "系统通知";

    /**
     * 状态转换过滤
     */
    private final String[] IGNORE_CHANGES = new String[]{"UNKNOWN:UP", "REGISTERED:UP", "DOWN:UP", "OFFLINE:UP"};


    private List<String> IGNORE_CHANGES_ARR = new ArrayList<>();

    private final ITakeNotifySendService takeNotesSendNotify;

    public AdminActuatorEventNotifierTakeNotes(InstanceRepository repository,
                                               ITakeNotifySendService takeNotesSendNotify) {
        super(repository);
        this.takeNotesSendNotify = takeNotesSendNotify;

        IGNORE_CHANGES_ARR.add("UNKNOWN");
        IGNORE_CHANGES_ARR.add("REGISTERED");
        IGNORE_CHANGES_ARR.add("DOWN");
        IGNORE_CHANGES_ARR.add("UP");
        IGNORE_CHANGES_ARR.add("OFFLINE");
        IGNORE_CHANGES_ARR.add("DEREGISTERED");
    }

    /**
     * 判断是否需要告警
     *
     * @param event
     * @param instance
     * @return 判断结果
     */
    @Override
    protected boolean shouldNotify(InstanceEvent event, Instance instance) {
        final String status = instance.getStatusInfo().getStatus();

        final String type = event.getType();



//        if (IGNORE_CHANGES_ARR.contains(status)){
//            return true;
//        }

        if (IGNORE_CHANGES_ARR.contains(type)) {
            logger.info("服务状态发生改变, 服务名称: {}, 服务器IP: {}, 状态变更前: {}, 状态变更后: {}",
                    instance.getRegistration().getName(),
                    instance.getRegistration().getServiceUrl(),
                    this.getLastStatus(event.getInstance()),
                    type
            );
            return true;
        }
        logger.info("服务状态发生改变, 服务名称: {}, 服务器IP: {}, 状态变更前: {}, 状态变更后: {}",
                instance.getRegistration().getName(),
                instance.getRegistration().getServiceUrl(),
                this.getLastStatus(event.getInstance()),
                status
        );
        if (IGNORE_CHANGES_ARR.contains(status)) {
            return true;
        }

        //  return IGNORE_CHANGES_ARR.contains(status);

        if (!(event instanceof InstanceStatusChangedEvent)) {
            return false;
        } else {
            InstanceStatusChangedEvent statusChange = (InstanceStatusChangedEvent) event;
            String from = this.getLastStatus(event.getInstance());
            String to = statusChange.getStatusInfo().getStatus();

            //    return IGNORE_CHANGES_ARR.contains(to);

            return Arrays.binarySearch(this.IGNORE_CHANGES, from + ":" + to) < 0
                    && Arrays.binarySearch(this.IGNORE_CHANGES, "*:" + to) < 0
                    && Arrays.binarySearch(this.IGNORE_CHANGES, from + ":*") < 0;
        }
    }

    /***
     * 实现对事件的通知
     * 状态:
     *   DOWN: 健康检查没通过;
     *   OFFLINE: 服务离线;
     *   UP: 服务上线
     *   UNKNOWN: 服务未知异常
     *   REGISTERED: 注册
     *   ENDPOINTS_DETECTED: 检测到端点
     *
     * @author 王大宸
     * @date 2022/10/12 10:08
     * @param event event
     * @param instance instance
     * @return reactor.core.publisher.Mono<java.lang.Void>
     */
    @Override
    protected Mono<Void> doNotify(InstanceEvent event, Instance instance) {
        return Mono.fromRunnable(() -> {
            String messageText = "";
          //  final String status = instance.getStatusInfo().getStatus();

            String status = event.getType();

            JSONObject body = new JSONObject();
            body.put("instanceId", event.getInstance().getValue());
            body.put("appName", instance.getRegistration().getName());
            body.put("appStatus", status);
            body.put("notifyEvent", status);
            body.put("notifyDetails", JSONObject.toJSONString(instance.getStatusInfo().getDetails()));
            body.put("serverUri", instance.getRegistration().getServiceUrl());

            if ("OFFLINE".equals(status)) {
                messageText = String.format(TEMPLATE_INFO,
                        TITLE_ALARM,
                        getNowTime(),
                        instance.getRegistration().getName(),
                        status,
                        "服务离线告警",
                        instance.getRegistration().getServiceUrl(),
                        JSONObject.toJSONString(instance.getStatusInfo().getDetails()));
                logger.info("【服务离线告警】：{}", messageText);
                body.put("notifyEvent", TITLE_ALARM);
                body.put("appStatusDes", "服务离线告警");
            } else if ("DEREGISTERED".equals(status)) {
                messageText = String.format(TEMPLATE_INFO,
                        TITLE_ALARM,
                        getNowTime(),
                        instance.getRegistration().getName(),
                        status,
                        "服务注销告警",
                        instance.getRegistration().getServiceUrl(),
                        JSONObject.toJSONString(instance.getStatusInfo().getDetails()));
                logger.info("【服务注销告警】：{}", messageText);
                body.put("notifyEvent", TITLE_NOTICE);
                body.put("appStatusDes", "服务注销告警");
            } else if ("UP".equals(status)) {
                messageText = String.format(TEMPLATE_INFO,
                        TITLE_NOTICE,
                        getNowTime(),
                        instance.getRegistration().getName(),
                        status,
                        "服务上线通知",
                        instance.getRegistration().getServiceUrl(),
                        JSONObject.toJSONString(instance.getStatusInfo().getDetails()));
                logger.info("【服务上线通知】：{}", messageText);
                body.put("notifyEvent", TITLE_NOTICE);
                body.put("appStatusDes", "服务上线通知");
            } else {
                messageText = String.format(TEMPLATE_INFO,
                        TITLE_NOTICE,
                        getNowTime(),
                        instance.getRegistration().getName(),
                        status,
                        "服务其他通知",
                        instance.getRegistration().getServiceUrl(),
                        JSONObject.toJSONString(instance.getStatusInfo().getDetails()));
                body.put("appStatusDes", "服务其他通知");
                body.put("notifyEvent", TITLE_NOTICE);
                logger.info("【服务其他通知】：{}", messageText);
            }


            body.put("notifyMsg", messageText);
            body.put("notifyEventTitle", status + "【" + body.get("appStatusDes") + "】");
            takeNotesSendNotify.sendNotify(body);


//            if (event instanceof InstanceStatusChangedEvent) {
//                logger.info("Instance Status Change: [{}], [{}], [{}], [{}]",
//                        instance.getRegistration().getName(),
//                        event.getInstance().getValue(),
//                        ((InstanceStatusChangedEvent) event).getStatusInfo().getStatus(),
//                        instance.getRegistration().getServiceUrl());
//
//                String serverStatus = ((InstanceStatusChangedEvent) event).getStatusInfo().getStatus();
//                System.err.println("serverStatus: " + serverStatus);
//
//                JSONObject body = new JSONObject();
//                body.put("instanceId", event.getInstance().getValue());
//                body.put("appName", instance.getRegistration().getName());
//                body.put("appStatus", serverStatus);
//                body.put("notifyEvent", serverStatus);
//                body.put("notifyDetails", JSONObject.toJSONString(instance.getStatusInfo().getDetails()));
//                body.put("serverUri", instance.getRegistration().getServiceUrl());
//
//                switch (serverStatus) {
//                    // 健康检查没通过
//                    case "DOWN" -> {
//                        messageText = String.format(TEMPLATE_INFO,
//                                TITLE_ALARM,
//                                instance.getRegistration().getName(),
//                                event.getInstance(),
//                                ((InstanceStatusChangedEvent) event).getStatusInfo().getStatus(),
//                                "健康检查没通过告警",
//                                instance.getRegistration().getServiceUrl(),
//                                JSONObject.toJSONString(instance.getStatusInfo().getDetails()));
//                        logger.info("【健康检查没通过告警】：{}", messageText);
//                        body.put("notifyEvent", TITLE_ALARM);
//                        body.put("appStatusDes", "健康检查没通过告警");
//
//                    }
//                    // 服务离线
//                    case "OFFLINE" -> {
//                        messageText = String.format(TEMPLATE_INFO,
//                                TITLE_ALARM,
//                                instance.getRegistration().getName(),
//                                event.getInstance(),
//                                ((InstanceStatusChangedEvent) event).getStatusInfo().getStatus(),
//                                "服务离线告警",
//                                instance.getRegistration().getServiceUrl(),
//                                JSONObject.toJSONString(instance.getStatusInfo().getDetails()));
//                        logger.info("【服务离线告警】：{}", messageText);
//                        body.put("notifyEvent", TITLE_ALARM);
//                        body.put("appStatusDes", "服务离线告警");
//                    }
//                    //服务上线
//                    case "UP" -> {
//                        messageText = String.format(TEMPLATE_INFO,
//                                TITLE_NOTICE,
//                                instance.getRegistration().getName(),
//                                event.getInstance(),
//                                ((InstanceStatusChangedEvent) event).getStatusInfo().getStatus(),
//                                "服务上线通知", instance.getRegistration().getServiceUrl(),
//                                JSONObject.toJSONString(instance.getStatusInfo().getDetails()));
//                        logger.info("【服务上线通知】：{}", messageText);
//                        body.put("notifyEvent", TITLE_NOTICE);
//                        body.put("appStatusDes", "服务上线通知");
//                    }
//                    // 服务未知异常
//                    case "UNKNOWN" -> {
//                        messageText = String.format(TEMPLATE_INFO,
//                                TITLE_ALARM,
//                                instance.getRegistration().getName(),
//                                event.getInstance(),
//                                ((InstanceStatusChangedEvent) event).getStatusInfo().getStatus(),
//                                "服务未知异常告警", instance.getRegistration().getServiceUrl(),
//                                JSONObject.toJSONString(instance.getStatusInfo().getDetails()));
//                        body.put("appStatusDes", "服务未知异常告警");
//                        body.put("notifyEvent", TITLE_ALARM);
//                        logger.info("【服务未知异常告警】：{}", messageText);
//                    }
//                    case "REGISTERED" -> {
//                        messageText = String.format(TEMPLATE_INFO,
//                                TITLE_NOTICE,
//                                instance.getRegistration().getName(),
//                                event.getInstance(),
//                                ((InstanceStatusChangedEvent) event).getStatusInfo().getStatus(),
//                                "服务注册通知", instance.getRegistration().getServiceUrl(),
//                                JSONObject.toJSONString(instance.getStatusInfo().getDetails()));
//                        body.put("appStatusDes", "服务注册通知");
//                        body.put("notifyEvent", TITLE_NOTICE);
//                        logger.info("【服务注册通知】：{}", messageText);
//                    }
//                    default -> {
//                        messageText = String.format(TEMPLATE_INFO,
//                                TITLE_NOTICE,
//                                instance.getRegistration().getName(),
//                                event.getInstance(),
//                                ((InstanceStatusChangedEvent) event).getStatusInfo().getStatus(),
//                                "服务其他通知",
//                                instance.getRegistration().getServiceUrl(),
//                                JSONObject.toJSONString(instance.getStatusInfo().getDetails()));
//                        body.put("appStatusDes", "服务其他通知");
//                        body.put("notifyEvent", TITLE_NOTICE);
//                        logger.info("【服务其他通知】：{}", messageText);
//                    }
//                }
//                body.put("notifyMsg", messageText);
//                body.put("notifyEventTitle", serverStatus + "【" + body.get("appStatusDes") + "】");
//                takeNotesSendNotify.sendNotify(body);
//            } else {
//                logger.info("Instance Info: [{}], [{}], [{}], [{}]",
//                        instance.getRegistration().getName(),
//                        event.getInstance().getValue(),
//                        event.getType(),
//                        instance.getRegistration().getServiceUrl());
//            }

        });
    }


    private String getNowTime() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }


}
