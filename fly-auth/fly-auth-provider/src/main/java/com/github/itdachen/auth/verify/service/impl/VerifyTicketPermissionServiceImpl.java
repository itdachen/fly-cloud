package com.github.itdachen.auth.verify.service.impl;

import com.github.itdachen.auth.verify.mapper.IVerifyTicketPermissionMapper;
import com.github.itdachen.auth.verify.service.IVerifyTicketPermissionService;
import com.github.itdachen.boot.rbac.IRbacPermissionService;
import com.github.itdachen.framework.context.permission.CheckPermissionInfo;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * Description: 鉴权
 * Created by 王大宸 on 2023/05/05 14:35
 * Created with IntelliJ IDEA.
 */
@Service
public class VerifyTicketPermissionServiceImpl implements IVerifyTicketPermissionService {
    private final IVerifyTicketPermissionMapper verifyTicketPermissionMapper;
    private final IRbacPermissionService rbacPermissionService;

    public VerifyTicketPermissionServiceImpl(IVerifyTicketPermissionMapper verifyTicketPermissionMapper,
                                             IRbacPermissionService rbacPermissionService) {
        this.verifyTicketPermissionMapper = verifyTicketPermissionMapper;
        this.rbacPermissionService = rbacPermissionService;
    }

    @Override
    public CheckPermissionInfo verifyTicketPermission(HttpServletRequest request,
                                                            final String userId,
                                                            final String requestUri,
                                                            final String requestMethod) throws Exception {
        return rbacPermissionService.checkUserPermission(userId, requestUri, requestMethod);
    }

}
