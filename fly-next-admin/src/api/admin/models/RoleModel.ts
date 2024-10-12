/*
 * Description: 角色
 * Created by 王大宸 on 2022-06-24 15:36:00
 * Created with IntelliJ Webstorm.
 */

import {BizQuery} from "/@/hooks/biz/BizModel";

/**
 * 角色 查询参数
 *
 * @author 王大宸
 * @date 2022-06-24 15:36:00
 */
export interface RoleQuery extends BizQuery {
    /* 角色名称 */
    name?: string,
}


/**
 * 角色 向后端传值对象
 *
 * @author 王大宸
 * @date 2022-06-24 15:36:00
 */
export interface RoleDto {
    /* 主键唯一标识 */
    id?: string,
    /* 角色名称 */
    name?: string,
    /* 描述 */
    remark?: string,
    /* 创建时间 */
    createTime?: string,
    /* 创建人 */
    createUser?: string,
    /* 创建人id */
    createUserId?: string,
    /* 更新人 */
    updateUser?: string,
    /* 更新时间 */
    updateTime?: string,
    /* 更新人id */
    updateUserId?: string,
}

/**
 * 角色 后端返回接收数据对象
 *
 * @author 王大宸
 * @date 2022-06-24 15:36:00
 */
export interface RoleVo {
    /* 主键唯一标识 */
    id?: string,
    /* 角色名称 */
    name?: string,
    /* 描述 */
    remark?: string,
    /* 创建时间 */
    createTime?: string,
    /* 创建人 */
    createUser?: string,
    /* 创建人id */
    createUserId?: string,
    /* 更新人 */
    updateUser?: string,
    /* 更新时间 */
    updateTime?: string,
    /* 更新人id */
    updateUserId?: string,
}

/**
 * 用户角色 向后端传值对象
 *
 * @author 王大宸
 * @date 2022-06-24 15:36:00
 */
export interface RoleUserDto {
    /* 用户id */
    userId?: string,
    /* 角色id, 多个角色之间用逗号连接 */
    roleId?: string,
}

/**
 * 菜单角色 向后端传值对象
 *
 * @author 王大宸
 * @date 2022-06-24 15:36:00
 */
export interface RoleMenuDto {
    /* 角色id */
    roleId?: string,
    /* 菜单id, 多个菜单之间用逗号连接 */
    menuId?: string,
}

/**
 * 权限下发 向后端传值对象
 *
 * @author 王大宸
 * @date 2022-06-27 10:53:08
 */
export interface PermsAuthDto {
    /* 主键唯一标识 */
    id?: string,
    /* 用户id */
    userId?: string,
    /* 菜单id */
    menuId?: string,
}