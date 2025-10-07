package com.xxc.xia.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxc.xia.common.utils.AssertUtils;
import com.xxc.xia.common.wrapper.PageWrapper;
import com.xxc.xia.convert.UserRoleRelConvert;
import com.xxc.xia.dto.userrolerel.UserRoleRelCreateRequest;
import com.xxc.xia.dto.userrolerel.UserRoleRelPageRequest;
import com.xxc.xia.dto.userrolerel.UserRoleRelUpdateRequest;
import com.xxc.xia.entity.UserRoleRel;
import com.xxc.xia.mapper.UserRoleRelMapper;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * 用户角色关系表 serviceImpl
 *
 * @author xxc
 * @create 2025-09-24 01:06:55
 */
@Service
public class UserRoleRelServiceImpl extends ServiceImpl<UserRoleRelMapper, UserRoleRel> {

    @Resource
    private UserRoleRelMapper userRoleRelMapper;

    /**
     * 创建UserRoleRel
     *
     * @param request
     * @return
     */
    public Long createUserRoleRel(UserRoleRelCreateRequest request) {
        // 插入
        UserRoleRel userRoleRel = UserRoleRelConvert.convert(request);
        userRoleRel.setCreateTime(new Date());
        userRoleRel.setUpdateTime(new Date());
        userRoleRelMapper.insert(userRoleRel);
        // 返回
        return userRoleRel.getId();
    }

    /**
     * 更新UserRoleRel
     *
     * @param request
     * @return
     */
    public void updateUserRoleRel(UserRoleRelUpdateRequest request) {
        // 校验存在
        checkUserRoleRelExists(request.getId());
        // 更新
        UserRoleRel updateObj = UserRoleRelConvert.convert(request);
        updateObj.setUpdateTime(new Date());
        userRoleRelMapper.updateById(updateObj);
    }

    /**
     * 删除UserRoleRel
     *
     * @param id
     * @return
     */
    public void deleteUserRoleRel(Long id) {
        // 校验存在
        checkUserRoleRelExists(id);
        // 删除
        userRoleRelMapper.deleteById(id);
    }

    /**
     * 校验UserRoleRel是否存在
     *
     * @param id
     */
    private void checkUserRoleRelExists(Long id) {
        UserRoleRel userRoleRel = userRoleRelMapper.selectById(id);
        AssertUtils.notNull(userRoleRel, String.format("不存在id=%d的记录", id));
    }

    /**
     * 获取UserRoleRel
     *
     * @param id
     * @return
     */
    public UserRoleRel getUserRoleRel(Long id) {
        return userRoleRelMapper.selectById(id);
    }

    /**
     * 获取UserRoleRel列表
     *
     * @param ids
     * @return
     */
    public List<UserRoleRel> getUserRoleRelList(Collection<Long> ids) {
        return userRoleRelMapper.selectBatchIds(ids);
    }

    /**
     * 分页获取UserRoleRel列表
     *
     * @param request
     * @return
     */
    public PageWrapper<UserRoleRel> getUserRoleRelPage(UserRoleRelPageRequest request) {
        LambdaQueryWrapper<UserRoleRel> lqw = new LambdaQueryWrapper<>();
        // 主键ID
        lqw.eq(request.getId() != null, UserRoleRel::getId, request.getId());
        // 用户id
        lqw.eq(StringUtils.isNotBlank(request.getUserId()), UserRoleRel::getUserId,
            request.getUserId());
        // 角色key
        lqw.eq(StringUtils.isNotBlank(request.getRoleKey()), UserRoleRel::getRoleKey,
            request.getRoleKey());
        // 扩展信息
        lqw.eq(StringUtils.isNotBlank(request.getExtendInfo()), UserRoleRel::getExtendInfo,
            request.getExtendInfo());
        // 创建者
        lqw.eq(StringUtils.isNotBlank(request.getCreateBy()), UserRoleRel::getCreateBy,
            request.getCreateBy());
        // 创建时间
        lqw.eq(request.getCreateTime() != null, UserRoleRel::getCreateTime,
            request.getCreateTime());
        // 创建时间 start
        lqw.ge(request.getCreateTimeStart() != null, UserRoleRel::getCreateTime,
            request.getCreateTimeStart());
        // 创建时间 end
        lqw.le(request.getCreateTimeEnd() != null, UserRoleRel::getCreateTime,
            request.getCreateTimeEnd());
        // 更新者
        lqw.eq(StringUtils.isNotBlank(request.getUpdateBy()), UserRoleRel::getUpdateBy,
            request.getUpdateBy());
        // 更新时间
        lqw.eq(request.getUpdateTime() != null, UserRoleRel::getUpdateTime,
            request.getUpdateTime());
        // 更新时间 start
        lqw.ge(request.getUpdateTimeStart() != null, UserRoleRel::getUpdateTime,
            request.getUpdateTimeStart());
        // 更新时间 end
        lqw.le(request.getUpdateTimeEnd() != null, UserRoleRel::getUpdateTime,
            request.getUpdateTimeEnd());
        lqw.orderByDesc(UserRoleRel::getId);
        return userRoleRelMapper.selectPage(request, lqw);
    }

    /**
     * 根据用户ids查询
     * @param userIdSet
     * @return
     */
    public List<UserRoleRel> queryByUserIds(Set<Long> userIdSet) {
        if (userIdSet == null || userIdSet.isEmpty()) {
            return Collections.emptyList();
        }
        List<UserRoleRel> userRoleRelList = new LambdaQueryChainWrapper<>(baseMapper)
            .in(UserRoleRel::getUserId, userIdSet).list();
        return Optional.ofNullable(userRoleRelList).orElse(Collections.emptyList());
    }

    /**
     * 根据用户id删除
     * @param userId
     * @return
     */
    public boolean deleteByUserId(Long userId) {
        return new LambdaUpdateChainWrapper<>(baseMapper)
                .eq(UserRoleRel::getUserId, userId)
                .remove();
    }

}