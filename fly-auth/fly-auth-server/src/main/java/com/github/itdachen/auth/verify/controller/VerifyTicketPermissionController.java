package com.github.itdachen.auth.verify.controller;


import com.github.itdachen.auth.verify.service.IVerifyTicketPermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/***
 * 权限校验
 *
 * @author 王大宸
 * @date 2023/5/5 14:26
 */
@RestController
@RequestMapping("/verify/permission/ticket")
public class VerifyTicketPermissionController {
private static final Logger logger = LoggerFactory.getLogger(VerifyTicketPermissionController.class);
    private final IVerifyTicketPermissionService verifyTicketPermissionService;

    public VerifyTicketPermissionController(IVerifyTicketPermissionService verifyTicketPermissionService) {
        this.verifyTicketPermissionService = verifyTicketPermissionService;
    }


}
