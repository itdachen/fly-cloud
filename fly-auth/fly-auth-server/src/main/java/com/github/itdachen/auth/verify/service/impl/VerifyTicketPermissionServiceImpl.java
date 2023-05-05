package com.github.itdachen.auth.verify.service.impl;

import com.github.itdachen.auth.verify.mapper.IVerifyTicketPermissionMapper;
import com.github.itdachen.auth.verify.service.IVerifyTicketPermissionService;
import org.springframework.stereotype.Service;

/**
 * Description:
 * Created by 王大宸 on 2023/05/05 14:35
 * Created with IntelliJ IDEA.
 */
@Service
public class VerifyTicketPermissionServiceImpl implements IVerifyTicketPermissionService {
    private IVerifyTicketPermissionMapper verifyTicketPermissionMapper;

    public VerifyTicketPermissionServiceImpl(IVerifyTicketPermissionMapper verifyTicketPermissionMapper) {
        this.verifyTicketPermissionMapper = verifyTicketPermissionMapper;
    }

}
