package com.github.itdachen.auth.verify.controller;


import com.github.itdachen.auth.verify.service.IVerifyTicketPermissionService;
import com.github.itdachen.framework.context.annotation.IgnoreResponseAdvice;
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


    /***
     * 根据用户身份校验权限
     *
     * @author 王大宸
     * @date 2024/7/31 9:46
     * @param request 请求信息
     * @param roleId   身份ID
     * @param requestUri 访问地址
     * @param requestMethod 访问方式
     * @return reactor.core.publisher.Mono<com.github.itdachen.framework.context.permission.CheckPermissionInfo>
     */
    @RequestMapping(value = "/{roleId}/check/permission", method = RequestMethod.GET)
    @IgnoreResponseAdvice
    public Mono<CheckPermissionInfo> verifyTicketPermission(HttpServletRequest request,
                                                            @PathVariable("roleId") String roleId,
                                                            @RequestParam String requestUri,
                                                            @RequestParam String requestMethod) throws Exception {
        final CheckPermissionInfo checkPermissionInfo = verifyTicketPermissionService.verifyTicketPermission(request,
                roleId,
                requestUri,
                requestMethod);
        return Mono.just(checkPermissionInfo);
    }

}
