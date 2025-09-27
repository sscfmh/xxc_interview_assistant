package com.xxc.xia.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxc.xia.common.wrapper.PageWrapper;
import com.xxc.xia.common.utils.AssertUtils;
import com.xxc.xia.convert.UserFavConvert;
import com.xxc.xia.dto.userfav.*;
import com.xxc.xia.entity.UserFav;
import com.xxc.xia.mapper.UserFavMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * 用户收藏 serviceImpl
 *
 * @author xxc
 * @create 2025-09-27 12:09:35
 */
@Service
public class UserFavServiceImpl extends ServiceImpl<UserFavMapper, UserFav> {

    @Resource
    private UserFavMapper userFavMapper;

    /**
     * 创建UserFav
     *
     * @param request
     * @return
     */
    public Long createUserFav(UserFavCreateRequest request) {
        // 插入
        UserFav userFav = UserFavConvert.convert(request);
        userFav.setCreateTime(new Date());
        userFav.setUpdateTime(new Date());
        userFavMapper.insert(userFav);
        // 返回
        return userFav.getId();
    }

    /**
     * 更新UserFav
     *
     * @param request
     * @return
     */
    public void updateUserFav(UserFavUpdateRequest request) {
        // 校验存在
        checkUserFavExists(request.getId());
        // 更新
        UserFav updateObj =  UserFavConvert.convert(request);
        updateObj.setUpdateTime(new Date());
        userFavMapper.updateById(updateObj);
    }

    /**
     * 删除UserFav
     *
     * @param id
     * @return
     */
    public void deleteUserFav(Long id) {
        // 校验存在
        checkUserFavExists(id);
        // 删除
        userFavMapper.deleteById(id);
    }

    /**
     * 校验UserFav是否存在
     *
     * @param id
     */
    private void checkUserFavExists(Long id) {
        UserFav userFav = userFavMapper.selectById(id);
        AssertUtils.notNull(userFav, String.format("不存在id=%d的记录", id));
    }

    /**
     * 获取UserFav
     *
     * @param id
     * @return
     */
    public UserFav getUserFav(Long id) {
        return userFavMapper.selectById(id);
    }

    /**
     * 获取UserFav列表
     *
     * @param ids
     * @return
     */
    public List<UserFav> getUserFavList(Collection<Long> ids) {
        return userFavMapper.selectBatchIds(ids);
    }

    /**
     * 分页获取UserFav列表
     *
     * @param request
     * @return
     */
    public PageWrapper<UserFav> getUserFavPage(UserFavPageRequest request) {
        LambdaQueryWrapper<UserFav> lqw = new LambdaQueryWrapper<>();
        // 主键ID
        lqw.eq(request.getId() != null, UserFav::getId, request.getId());
        // 业务类型
        lqw.eq(StringUtils.isNotBlank(request.getBizType()), UserFav::getBizType, request.getBizType());
        // 业务ID
        lqw.eq(StringUtils.isNotBlank(request.getBizId()), UserFav::getBizId, request.getBizId());
        // 用户ID
        lqw.eq(StringUtils.isNotBlank(request.getUserId()), UserFav::getUserId, request.getUserId());
        // 扩展信息
        lqw.eq(StringUtils.isNotBlank(request.getExtendInfo()), UserFav::getExtendInfo, request.getExtendInfo());
        // 创建人
        lqw.eq(StringUtils.isNotBlank(request.getCreateBy()), UserFav::getCreateBy, request.getCreateBy());
        // 创建时间
        lqw.eq(request.getCreateTime() != null, UserFav::getCreateTime, request.getCreateTime());
        // 创建时间 start
        lqw.ge(request.getCreateTimeStart() != null, UserFav::getCreateTime, request.getCreateTimeStart());
        // 创建时间 end
        lqw.le(request.getCreateTimeEnd() != null, UserFav::getCreateTime, request.getCreateTimeEnd());
        // 修改人
        lqw.eq(StringUtils.isNotBlank(request.getUpdateBy()), UserFav::getUpdateBy, request.getUpdateBy());
        // 修改时间
        lqw.eq(request.getUpdateTime() != null, UserFav::getUpdateTime, request.getUpdateTime());
        // 修改时间 start
        lqw.ge(request.getUpdateTimeStart() != null, UserFav::getUpdateTime, request.getUpdateTimeStart());
        // 修改时间 end
        lqw.le(request.getUpdateTimeEnd() != null, UserFav::getUpdateTime, request.getUpdateTimeEnd());
        return userFavMapper.selectPage(request, lqw);
    }

}