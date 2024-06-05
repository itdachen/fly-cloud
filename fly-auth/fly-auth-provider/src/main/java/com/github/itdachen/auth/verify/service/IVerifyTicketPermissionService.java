package com.github.itdachen.auth.verify.service;

import com.github.itdachen.framework.context.permission.CheckPermissionInfo;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import reactor.core.publisher.Mono;

/**
 * Description:
 * Created by 王大宸 on 2023/05/05 14:35
 * Created with IntelliJ IDEA.
 */
public interface IVerifyTicketPermissionService {


    Mono<CheckPermissionInfo> verifyTicketPermission(HttpServletRequest request,
                                                     String userId,
                                                     String requestUri,
                                                     String requestMethod) throws Exception;

}
