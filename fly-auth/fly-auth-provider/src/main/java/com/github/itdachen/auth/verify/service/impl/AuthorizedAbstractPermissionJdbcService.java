package com.github.itdachen.auth.verify.service.impl;

import com.github.itdachen.boot.rbac.jdbc.AbstractPermissionJdbcService;
import com.github.itdachen.framework.context.permission.PermissionInfo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description:
 * Created by 王大宸 on 2023/05/06 17:02
 * Created with IntelliJ IDEA.
 */
@Service
public class AuthorizedAbstractPermissionJdbcService extends AbstractPermissionJdbcService {

    @Override
    protected List<PermissionInfo> findMenuPermission() {
        return null;
    }

    @Override
    protected List<PermissionInfo> findElementPermission() {
        return null;
    }

}
