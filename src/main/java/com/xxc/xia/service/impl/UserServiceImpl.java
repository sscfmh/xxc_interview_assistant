package com.xxc.xia.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxc.xia.common.enums.RoleEnum;
import com.xxc.xia.common.utils.AssertUtils;
import com.xxc.xia.common.wrapper.PageWrapper;
import com.xxc.xia.convert.UserConvert;
import com.xxc.xia.dto.user.UserCreateRequest;
import com.xxc.xia.dto.user.UserPageRequest;
import com.xxc.xia.dto.user.UserUpdateRequest;
import com.xxc.xia.dto.userrolerel.UserRoleRelCreateRequest;
import com.xxc.xia.entity.Role;
import com.xxc.xia.entity.User;
import com.xxc.xia.mapper.UserMapper;
import jakarta.annotation.Resource;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * 用户信息表 serviceImpl
 *
 * @author xxc
 * @create 2025-09-24 01:06:55
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> {

    @Resource
    private UserMapper             userMapper;

    @Autowired
    private TransactionTemplate    transactionTemplate;

    @Autowired
    private UserRoleRelServiceImpl userRoleRelService;

    @Autowired
    private RoleServiceImpl        roleService;

    /**
     * 创建User
     *
     * @param request
     * @return
     */
    public Long createUser(UserCreateRequest request) {
        // 插入
        User user = UserConvert.convert(request);
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        transactionTemplate.executeWithoutResult(status -> {
            userMapper.insert(user);
            UserRoleRelCreateRequest createRequest = new UserRoleRelCreateRequest();
            createRequest.setUserId(String.valueOf(user.getId()));
            createRequest.setRoleKey(RoleEnum.NORMAL_USER.getCode());
            userRoleRelService.createUserRoleRel(createRequest);
        });
        // 返回
        return user.getId();
    }

    /**
     * 更新User
     *
     * @param request
     * @return
     */
    public void updateUser(UserUpdateRequest request) {
        // 校验存在
        checkUserExists(request.getId());
        if (CollectionUtils.isNotEmpty(request.getRoles())) {
            for (String roleKey : request.getRoles()) {
                Role role = roleService.queryByRoleKey(roleKey);
                AssertUtils.notNull(role, "角色不存在");
            }
        }
        // 更新
        User updateObj = UserConvert.convert(request);
        updateObj.setUpdateTime(new Date());
        transactionTemplate.executeWithoutResult((status) -> {
            userMapper.updateById(updateObj);
            userRoleRelService.deleteByUserId(updateObj.getId());
            List<String> roles = request.getRoles();
            if (CollectionUtils.isNotEmpty(roles)) {
                for (String roleKey : roles) {
                    if (StringUtils.isBlank(roleKey)) {
                        continue;
                    }
                    UserRoleRelCreateRequest createRequest = new UserRoleRelCreateRequest();
                    createRequest.setUserId(String.valueOf(updateObj.getId()));
                    createRequest.setRoleKey(roleKey);
                    userRoleRelService.createUserRoleRel(createRequest);
                }
            }
        });
    }

    /**
     * 删除User
     *
     * @param id
     * @return
     */
    public void deleteUser(Long id) {
        // 校验存在
        checkUserExists(id);
        // 删除
        userMapper.deleteById(id);
    }

    /**
     * 校验User是否存在
     *
     * @param id
     */
    private void checkUserExists(Long id) {
        User user = userMapper.selectById(id);
        AssertUtils.notNull(user, String.format("不存在id=%d的记录", id));
    }

    /**
     * 获取User
     *
     * @param id
     * @return
     */
    public User getUser(Long id) {
        return userMapper.selectById(id);
    }

    /**
     * 获取User列表
     *
     * @param ids
     * @return
     */
    public List<User> getUserList(Collection<Long> ids) {
        return userMapper.selectBatchIds(ids);
    }

    /**
     * 分页获取User列表
     *
     * @param request
     * @return
     */
    public PageWrapper<User> getUserPage(UserPageRequest request) {
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        // 主键ID
        lqw.eq(request.getId() != null, User::getId, request.getId());
        // 用户昵称
        lqw.eq(StringUtils.isNotBlank(request.getNickName()), User::getNickName,
            request.getNickName());
        // 用户邮箱
        lqw.eq(StringUtils.isNotBlank(request.getEmail()), User::getEmail, request.getEmail());
        // 手机号码
        lqw.eq(StringUtils.isNotBlank(request.getPhoneNumber()), User::getPhoneNumber,
            request.getPhoneNumber());
        // 密码
        lqw.eq(StringUtils.isNotBlank(request.getPassword()), User::getPassword,
            request.getPassword());
        // 帐号状态（0停用 1正常）
        lqw.eq(StringUtils.isNotBlank(request.getStatus()), User::getStatus, request.getStatus());
        // 用户性别（1男 2女 其他未知）
        lqw.eq(StringUtils.isNotBlank(request.getSex()), User::getSex, request.getSex());
        // 头像地址
        lqw.eq(StringUtils.isNotBlank(request.getAvatar()), User::getAvatar, request.getAvatar());
        // 扩展信息
        lqw.eq(StringUtils.isNotBlank(request.getExtendInfo()), User::getExtendInfo,
            request.getExtendInfo());
        // 创建者
        lqw.eq(StringUtils.isNotBlank(request.getCreateBy()), User::getCreateBy,
            request.getCreateBy());
        // 创建时间
        lqw.eq(request.getCreateTime() != null, User::getCreateTime, request.getCreateTime());
        // 创建时间 start
        lqw.ge(request.getCreateTimeStart() != null, User::getCreateTime,
            request.getCreateTimeStart());
        // 创建时间 end
        lqw.le(request.getCreateTimeEnd() != null, User::getCreateTime, request.getCreateTimeEnd());
        // 更新者
        lqw.eq(StringUtils.isNotBlank(request.getUpdateBy()), User::getUpdateBy,
            request.getUpdateBy());
        // 更新时间
        lqw.eq(request.getUpdateTime() != null, User::getUpdateTime, request.getUpdateTime());
        // 更新时间 start
        lqw.ge(request.getUpdateTimeStart() != null, User::getUpdateTime,
            request.getUpdateTimeStart());
        // 更新时间 end
        lqw.le(request.getUpdateTimeEnd() != null, User::getUpdateTime, request.getUpdateTimeEnd());
        lqw.orderByDesc(User::getId);
        return userMapper.selectPage(request, lqw);
    }

}