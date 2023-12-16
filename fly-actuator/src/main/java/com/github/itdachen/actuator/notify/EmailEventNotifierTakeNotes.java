package com.github.itdachen.actuator.notify;

import de.codecentric.boot.admin.server.domain.entities.Instance;
import de.codecentric.boot.admin.server.domain.entities.InstanceRepository;
import de.codecentric.boot.admin.server.domain.events.InstanceEvent;
import de.codecentric.boot.admin.server.domain.events.InstanceStatusChangedEvent;
import de.codecentric.boot.admin.server.notify.AbstractStatusChangeNotifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import reactor.core.publisher.Mono;

/**
 * EmailEventNotifierTakeNotes
 *
 * @author 王大宸
 * @date 2023-12-16 23:07
 */
public class EmailEventNotifierTakeNotes extends AbstractStatusChangeNotifier {
    private static final Logger logger = LoggerFactory.getLogger(EmailEventNotifierTakeNotes.class);


    //邮件模板内容信息
    private String content = "<p style=\"white-space: normal;\">亲爱的运维人员：</p><p style=\"white-space: normal;\">&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;您好！</p><p style=\"white-space: normal;\">&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;您的运维管理的服务程序已经<strong>${服务状态}</strong>,</p><p style=\"white-space: normal;\">服务名称: <strong>${服务名称}</strong>, 服务器IP: <strong>${服务器ip地址}。</strong></p><p style=\"white-space: normal;\">请查看及时服务运行状态。</p><p style=\"white-space: normal;\">如有打扰，请见谅！</p><p style=\"white-space: normal;\"><br/></p><p style=\"white-space: normal;\"><strong>FLY-CLOUD 服务监控中心！</strong></p><p><br/></p>'";

    public EmailEventNotifierTakeNotes(InstanceRepository repository) {
        super(repository);
    }

    @Override
    protected Mono<Void> doNotify(InstanceEvent event, Instance instance) {
        return Mono.fromRunnable(() -> {
            logger.info("服务状态发生改变, 服务名称: {}, 服务器IP: {}, 状态变更前: {}, 状态变更后: {}",
                    instance.getRegistration().getName(),
                    instance.getRegistration().getServiceUrl(),
                    this.getLastStatus(event.getInstance()),
                    event.getType()
            );


            if (event instanceof InstanceStatusChangedEvent) {
                logger.info("Instance {} ({}) is {}", instance.getRegistration().getName(), event.getInstance(),
                        ((InstanceStatusChangedEvent) event).getStatusInfo().getStatus());
                //获取服务状态 status  UP上线,OFFLINE离职
                String status = ((InstanceStatusChangedEvent) event).getStatusInfo().getStatus();
                //服务名称,
                String clientName = instance.getRegistration().getName().toLowerCase();
                //服务所在的服务器ip地址
                String clientServerUrl = instance.getRegistration().getServiceUrl();
                String statusName = "OFFLINE".equals(status) ? "已经离线了" : "已经重新上线!";
                if ("OFFLINE".equals(status) || "UP".equals(status)) {
                    //  emailUtil.sendEmail(subject + "-" + clientName + "-" + status, repelateMessage(content,statusName,clientName,clientServerUrl));
                }
            } else {
                logger.info("Instance {} ({}) {}", instance.getRegistration().getName(), event.getInstance(),
                        event.getType());
            }

        });
    }

    /**
     * 替换邮件模板内占位符
     */
    private String repelateMessage(String msg, String state, String appName, String serverIp) {
        msg = msg.replace("${服务状态}", state);
        msg = msg.replace("${服务名称}", appName);
        msg = msg.replace("${服务器ip地址}", serverIp);
        return msg;
    }

}
