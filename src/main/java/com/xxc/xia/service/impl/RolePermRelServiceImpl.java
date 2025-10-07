package com.xxc.xia.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxc.xia.common.wrapper.PageWrapper;
import com.xxc.xia.common.utils.AssertUtils;
import com.xxc.xia.convert.RolePermRelConvert;
import com.xxc.xia.dto.rolepermrel.*;
import com.xxc.xia.entity.RolePermRel;
import com.xxc.xia.mapper.RolePermRelMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * 角色权限关系表 serviceImpl
 *
 * @author xxc
 * @create 2025-09-24 01:06:55
 */
@Service
public class RolePermRelServiceImpl extends ServiceImpl<RolePermRelMapper, RolePermRel> {

    @Resource
    private RolePermRelMapper rolePermRelMapper;

    /**
     * 创建RolePermRel
     *
     * @param request
     * @return
     */
    public Long createRolePermRel(RolePermRelCreateRequest request) {
        // 插入
        RolePermRel rolePermRel = RolePermRelConvert.convert(request);
        rolePermRel.setCreateTime(new Date());
        rolePermRel.setUpdateTime(new Date());
        rolePermRelMapper.insert(rolePermRel);
        // 返回
        return rolePermRel.getId();
    }

    /**
     * 更新RolePermRel
     *
     * @param request
     * @return
     */
    public void updateRolePermRel(RolePermRelUpdateRequest request) {
        // 校验存在
        checkRolePermRelExists(request.getId());
        // 更新
        RolePermRel updateObj =  RolePermRelConvert.convert(request);
        updateObj.setUpdateTime(new Date());
        rolePermRelMapper.updateById(updateObj);
    }

    /**
     * 删除RolePermRel
     *
     * @param id
     * @return
     */
    public void deleteRolePermRel(Long id) {
        // 校验存在
        checkRolePermRelExists(id);
        // 删除
        rolePermRelMapper.deleteById(id);
    }

    /**
     * 校验RolePermRel是否存在
     *
     * @param id
     */
    private void checkRolePermRelExists(Long id) {
        RolePermRel rolePermRel = rolePermRelMapper.selectById(id);
        AssertUtils.notNull(rolePermRel, String.format("不存在id=%d的记录", id));
    }

    /**
     * 获取RolePermRel
     *
     * @param id
     * @return
     */
    public RolePermRel getRolePermRel(Long id) {
        return rolePermRelMapper.selectById(id);
    }

    /**
     * 获取RolePermRel列表
     *
     * @param ids
     * @return
     */
    public List<RolePermRel> getRolePermRelList(Collection<Long> ids) {
        return rolePermRelMapper.selectBatchIds(ids);
    }

    /**
     * 分页获取RolePermRel列表
     *
     * @param request
     * @return
     */
    public PageWrapper<RolePermRel> getRolePermRelPage(RolePermRelPageRequest request) {
        LambdaQueryWrapper<RolePermRel> lqw = new LambdaQueryWrapper<>();
        // 主键ID
        lqw.eq(request.getId() != null, RolePermRel::getId, request.getId());
        // 角色key
        lqw.eq(StringUtils.isNotBlank(request.getRoleKey()), RolePermRel::getRoleKey, request.getRoleKey());
        // 权限key
        lqw.eq(StringUtils.isNotBlank(request.getPermKey()), RolePermRel::getPermKey, request.getPermKey());
        // 扩展信息
        lqw.eq(StringUtils.isNotBlank(request.getExtendInfo()), RolePermRel::getExtendInfo, request.getExtendInfo());
        // 创建者
        lqw.eq(StringUtils.isNotBlank(request.getCreateBy()), RolePermRel::getCreateBy, request.getCreateBy());
        // 创建时间
        lqw.eq(request.getCreateTime() != null, RolePermRel::getCreateTime, request.getCreateTime());
        // 创建时间 start
        lqw.ge(request.getCreateTimeStart() != null, RolePermRel::getCreateTime, request.getCreateTimeStart());
        // 创建时间 end
        lqw.le(request.getCreateTimeEnd() != null, RolePermRel::getCreateTime, request.getCreateTimeEnd());
        // 更新者
        lqw.eq(StringUtils.isNotBlank(request.getUpdateBy()), RolePermRel::getUpdateBy, request.getUpdateBy());
        // 更新时间
        lqw.eq(request.getUpdateTime() != null, RolePermRel::getUpdateTime, request.getUpdateTime());
        // 更新时间 start
        lqw.ge(request.getUpdateTimeStart() != null, RolePermRel::getUpdateTime, request.getUpdateTimeStart());
        // 更新时间 end
        lqw.le(request.getUpdateTimeEnd() != null, RolePermRel::getUpdateTime, request.getUpdateTimeEnd());
        lqw.orderByDesc(RolePermRel::getId);
        return rolePermRelMapper.selectPage(request, lqw);
    }

}