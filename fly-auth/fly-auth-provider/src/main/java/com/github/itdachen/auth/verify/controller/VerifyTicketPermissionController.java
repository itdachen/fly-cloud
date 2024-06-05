package com.github.itdachen.auth.verify.controller;


import com.github.itdachen.auth.verify.service.IVerifyTicketPermissionService;
import com.github.itdachen.framework.context.BizContextHandler;
import com.github.itdachen.framework.context.annotation.IgnoreResponseAdvice;
import com.github.itdachen.framework.context.exception.BizException;
import com.github.itdachen.framework.context.permission.CheckPermissionInfo;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

/***
 * 权限校验
 *
 * @author 王大宸
 * @date 2023/5/5 14:26
 */
@RestController
@RequestMapping("/verify/ticket")
public class VerifyTicketPermissionController {
    private static final Logger logger = LoggerFactory.getLogger(VerifyTicketPermissionController.class);
    private final IVerifyTicketPermissionService verifyTicketPermissionService;

    public VerifyTicketPermissionController(IVerifyTicketPermissionService verifyTicketPermissionService) {
        this.verifyTicketPermissionService = verifyTicketPermissionService;
    }

    @RequestMapping(value = "/{userId}/check/permission", method = RequestMethod.GET)
    @IgnoreResponseAdvice
    public Mono<CheckPermissionInfo> verifyTicketPermission(HttpServletRequest request,
                                                            @PathVariable("userId") String userId,
                                                            @RequestParam String requestUri,
                                                            @RequestParam String requestMethod) throws Exception {
        final String userId1 = BizContextHandler.getUserId();
        return verifyTicketPermissionService.verifyTicketPermission(request,
                userId,
                requestUri,
                requestMethod);
    }

}
