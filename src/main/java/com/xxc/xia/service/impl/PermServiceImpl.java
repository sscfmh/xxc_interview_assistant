package com.xxc.xia.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxc.xia.common.wrapper.PageWrapper;
import com.xxc.xia.common.utils.AssertUtils;
import com.xxc.xia.convert.PermConvert;
import com.xxc.xia.dto.perm.*;
import com.xxc.xia.entity.Perm;
import com.xxc.xia.mapper.PermMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * 权限表 serviceImpl
 *
 * @author xxc
 * @create 2025-09-24 01:06:55
 */
@Service
public class PermServiceImpl extends ServiceImpl<PermMapper, Perm> {

    @Resource
    private PermMapper permMapper;

    /**
     * 创建Perm
     *
     * @param request
     * @return
     */
    public Long createPerm(PermCreateRequest request) {
        // 插入
        Perm perm = PermConvert.convert(request);
        perm.setCreateTime(new Date());
        perm.setUpdateTime(new Date());
        permMapper.insert(perm);
        // 返回
        return perm.getId();
    }

    /**
     * 更新Perm
     *
     * @param request
     * @return
     */
    public void updatePerm(PermUpdateRequest request) {
        // 校验存在
        checkPermExists(request.getId());
        // 更新
        Perm updateObj =  PermConvert.convert(request);
        updateObj.setUpdateTime(new Date());
        permMapper.updateById(updateObj);
    }

    /**
     * 删除Perm
     *
     * @param id
     * @return
     */
    public void deletePerm(Long id) {
        // 校验存在
        checkPermExists(id);
        // 删除
        permMapper.deleteById(id);
    }

    /**
     * 校验Perm是否存在
     *
     * @param id
     */
    private void checkPermExists(Long id) {
        Perm perm = permMapper.selectById(id);
        AssertUtils.notNull(perm, String.format("不存在id=%d的记录", id));
    }

    /**
     * 获取Perm
     *
     * @param id
     * @return
     */
    public Perm getPerm(Long id) {
        return permMapper.selectById(id);
    }

    /**
     * 获取Perm列表
     *
     * @param ids
     * @return
     */
    public List<Perm> getPermList(Collection<Long> ids) {
        return permMapper.selectBatchIds(ids);
    }

    /**
     * 分页获取Perm列表
     *
     * @param request
     * @return
     */
    public PageWrapper<Perm> getPermPage(PermPageRequest request) {
        LambdaQueryWrapper<Perm> lqw = new LambdaQueryWrapper<>();
        // 主键ID
        lqw.eq(request.getId() != null, Perm::getId, request.getId());
        // 权限key
        lqw.eq(StringUtils.isNotBlank(request.getPermKey()), Perm::getPermKey, request.getPermKey());
        // 父权限key
        lqw.eq(StringUtils.isNotBlank(request.getParentKey()), Perm::getParentKey, request.getParentKey());
        // 权限名称
        lqw.eq(StringUtils.isNotBlank(request.getPermName()), Perm::getPermName, request.getPermName());
        // 菜单类型（D目录 M菜单 B按钮）
        lqw.eq(StringUtils.isNotBlank(request.getMenuType()), Perm::getMenuType, request.getMenuType());
        // ui信息
        lqw.eq(StringUtils.isNotBlank(request.getUiInfo()), Perm::getUiInfo, request.getUiInfo());
        // 扩展信息
        lqw.eq(StringUtils.isNotBlank(request.getExtendInfo()), Perm::getExtendInfo, request.getExtendInfo());
        // 创建者
        lqw.eq(StringUtils.isNotBlank(request.getCreateBy()), Perm::getCreateBy, request.getCreateBy());
        // 创建时间
        lqw.eq(request.getCreateTime() != null, Perm::getCreateTime, request.getCreateTime());
        // 创建时间 start
        lqw.ge(request.getCreateTimeStart() != null, Perm::getCreateTime, request.getCreateTimeStart());
        // 创建时间 end
        lqw.le(request.getCreateTimeEnd() != null, Perm::getCreateTime, request.getCreateTimeEnd());
        // 更新者
        lqw.eq(StringUtils.isNotBlank(request.getUpdateBy()), Perm::getUpdateBy, request.getUpdateBy());
        // 更新时间
        lqw.eq(request.getUpdateTime() != null, Perm::getUpdateTime, request.getUpdateTime());
        // 更新时间 start
        lqw.ge(request.getUpdateTimeStart() != null, Perm::getUpdateTime, request.getUpdateTimeStart());
        // 更新时间 end
        lqw.le(request.getUpdateTimeEnd() != null, Perm::getUpdateTime, request.getUpdateTimeEnd());
        return permMapper.selectPage(request, lqw);
    }

}