package com.xxc.xia.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxc.xia.common.wrapper.PageWrapper;
import com.xxc.xia.common.utils.AssertUtils;
import com.xxc.xia.convert.RoleConvert;
import com.xxc.xia.dto.role.*;
import com.xxc.xia.entity.Role;
import com.xxc.xia.mapper.RoleMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * 角色表 serviceImpl
 *
 * @author xxc
 * @create 2025-09-24 01:06:55
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> {

    @Resource
    private RoleMapper roleMapper;

    /**
     * 创建Role
     *
     * @param request
     * @return
     */
    public Long createRole(RoleCreateRequest request) {
        // 插入
        Role role = RoleConvert.convert(request);
        role.setCreateTime(new Date());
        role.setUpdateTime(new Date());
        roleMapper.insert(role);
        // 返回
        return role.getId();
    }

    /**
     * 更新Role
     *
     * @param request
     * @return
     */
    public void updateRole(RoleUpdateRequest request) {
        // 校验存在
        checkRoleExists(request.getId());
        // 更新
        Role updateObj =  RoleConvert.convert(request);
        updateObj.setUpdateTime(new Date());
        roleMapper.updateById(updateObj);
    }

    /**
     * 删除Role
     *
     * @param id
     * @return
     */
    public void deleteRole(Long id) {
        // 校验存在
        checkRoleExists(id);
        // 删除
        roleMapper.deleteById(id);
    }

    /**
     * 校验Role是否存在
     *
     * @param id
     */
    private void checkRoleExists(Long id) {
        Role role = roleMapper.selectById(id);
        AssertUtils.notNull(role, String.format("不存在id=%d的记录", id));
    }

    /**
     * 获取Role
     *
     * @param id
     * @return
     */
    public Role getRole(Long id) {
        return roleMapper.selectById(id);
    }

    /**
     * 获取Role列表
     *
     * @param ids
     * @return
     */
    public List<Role> getRoleList(Collection<Long> ids) {
        return roleMapper.selectBatchIds(ids);
    }

    /**
     * 分页获取Role列表
     *
     * @param request
     * @return
     */
    public PageWrapper<Role> getRolePage(RolePageRequest request) {
        LambdaQueryWrapper<Role> lqw = new LambdaQueryWrapper<>();
        // 主键ID
        lqw.eq(request.getId() != null, Role::getId, request.getId());
        // 角色key
        lqw.eq(StringUtils.isNotBlank(request.getRoleKey()), Role::getRoleKey, request.getRoleKey());
        // 角色名称
        lqw.eq(StringUtils.isNotBlank(request.getRoleName()), Role::getRoleName, request.getRoleName());
        // 扩展信息
        lqw.eq(StringUtils.isNotBlank(request.getExtendInfo()), Role::getExtendInfo, request.getExtendInfo());
        // 创建者
        lqw.eq(StringUtils.isNotBlank(request.getCreateBy()), Role::getCreateBy, request.getCreateBy());
        // 创建时间
        lqw.eq(request.getCreateTime() != null, Role::getCreateTime, request.getCreateTime());
        // 创建时间 start
        lqw.ge(request.getCreateTimeStart() != null, Role::getCreateTime, request.getCreateTimeStart());
        // 创建时间 end
        lqw.le(request.getCreateTimeEnd() != null, Role::getCreateTime, request.getCreateTimeEnd());
        // 更新者
        lqw.eq(StringUtils.isNotBlank(request.getUpdateBy()), Role::getUpdateBy, request.getUpdateBy());
        // 更新时间
        lqw.eq(request.getUpdateTime() != null, Role::getUpdateTime, request.getUpdateTime());
        // 更新时间 start
        lqw.ge(request.getUpdateTimeStart() != null, Role::getUpdateTime, request.getUpdateTimeStart());
        // 更新时间 end
        lqw.le(request.getUpdateTimeEnd() != null, Role::getUpdateTime, request.getUpdateTimeEnd());
        return roleMapper.selectPage(request, lqw);
    }

}