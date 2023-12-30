package com.github.itdachen.auth.verify.service.impl;

import com.github.itdachen.boot.rbac.jdbc.AbstractPermissionUserJdbcService;
import com.github.itdachen.framework.context.permission.PermissionInfo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description:
 * Created by 王大宸 on 2023/05/06 17:04
 * Created with IntelliJ IDEA.
 */
@Service
public class AuthorizedAbstractPermissionUserJdbcService extends AbstractPermissionUserJdbcService {
    @Override
    protected List<PermissionInfo> findUserJobMenuPermission(String userId, String hostIp, String jobId) throws Exception {
        return null;
    }

    @Override
    protected List<PermissionInfo> findUserJobElementPermission(String userId, String hostIp, String jobId) throws Exception {
        return null;
    }

    @Override
    protected List<PermissionInfo> findUserMenuPermission(String userId, String hostIp) throws Exception {
        return null;
    }

    @Override
    protected List<PermissionInfo> findUserElementPermission(String userId, String hostIp) throws Exception {
        return null;
    }

}
