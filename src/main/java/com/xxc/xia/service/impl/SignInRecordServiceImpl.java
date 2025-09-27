package com.xxc.xia.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxc.xia.common.wrapper.PageWrapper;
import com.xxc.xia.common.utils.AssertUtils;
import com.xxc.xia.convert.SignInRecordConvert;
import com.xxc.xia.dto.signinrecord.*;
import com.xxc.xia.entity.SignInRecord;
import com.xxc.xia.mapper.SignInRecordMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * 签到记录 serviceImpl
 *
 * @author xxc
 * @create 2025-09-27 12:09:35
 */
@Service
public class SignInRecordServiceImpl extends ServiceImpl<SignInRecordMapper, SignInRecord> {

    @Resource
    private SignInRecordMapper signInRecordMapper;

    /**
     * 创建SignInRecord
     *
     * @param request
     * @return
     */
    public Long createSignInRecord(SignInRecordCreateRequest request) {
        // 插入
        SignInRecord signInRecord = SignInRecordConvert.convert(request);
        signInRecord.setCreateTime(new Date());
        signInRecord.setUpdateTime(new Date());
        signInRecordMapper.insert(signInRecord);
        // 返回
        return signInRecord.getId();
    }

    /**
     * 更新SignInRecord
     *
     * @param request
     * @return
     */
    public void updateSignInRecord(SignInRecordUpdateRequest request) {
        // 校验存在
        checkSignInRecordExists(request.getId());
        // 更新
        SignInRecord updateObj =  SignInRecordConvert.convert(request);
        updateObj.setUpdateTime(new Date());
        signInRecordMapper.updateById(updateObj);
    }

    /**
     * 删除SignInRecord
     *
     * @param id
     * @return
     */
    public void deleteSignInRecord(Long id) {
        // 校验存在
        checkSignInRecordExists(id);
        // 删除
        signInRecordMapper.deleteById(id);
    }

    /**
     * 校验SignInRecord是否存在
     *
     * @param id
     */
    private void checkSignInRecordExists(Long id) {
        SignInRecord signInRecord = signInRecordMapper.selectById(id);
        AssertUtils.notNull(signInRecord, String.format("不存在id=%d的记录", id));
    }

    /**
     * 获取SignInRecord
     *
     * @param id
     * @return
     */
    public SignInRecord getSignInRecord(Long id) {
        return signInRecordMapper.selectById(id);
    }

    /**
     * 获取SignInRecord列表
     *
     * @param ids
     * @return
     */
    public List<SignInRecord> getSignInRecordList(Collection<Long> ids) {
        return signInRecordMapper.selectBatchIds(ids);
    }

    /**
     * 分页获取SignInRecord列表
     *
     * @param request
     * @return
     */
    public PageWrapper<SignInRecord> getSignInRecordPage(SignInRecordPageRequest request) {
        LambdaQueryWrapper<SignInRecord> lqw = new LambdaQueryWrapper<>();
        // 主键ID
        lqw.eq(request.getId() != null, SignInRecord::getId, request.getId());
        // 业务类型
        lqw.eq(StringUtils.isNotBlank(request.getBizType()), SignInRecord::getBizType, request.getBizType());
        // 业务ID
        lqw.eq(StringUtils.isNotBlank(request.getBizId()), SignInRecord::getBizId, request.getBizId());
        // 签到标识
        lqw.eq(request.getYearMonth() != null, SignInRecord::getYearMonth, request.getYearMonth());
        // 扩展信息
        lqw.eq(StringUtils.isNotBlank(request.getExtendInfo()), SignInRecord::getExtendInfo, request.getExtendInfo());
        // 创建者
        lqw.eq(StringUtils.isNotBlank(request.getCreateBy()), SignInRecord::getCreateBy, request.getCreateBy());
        // 创建时间
        lqw.eq(request.getCreateTime() != null, SignInRecord::getCreateTime, request.getCreateTime());
        // 创建时间 start
        lqw.ge(request.getCreateTimeStart() != null, SignInRecord::getCreateTime, request.getCreateTimeStart());
        // 创建时间 end
        lqw.le(request.getCreateTimeEnd() != null, SignInRecord::getCreateTime, request.getCreateTimeEnd());
        // 更新者
        lqw.eq(StringUtils.isNotBlank(request.getUpdateBy()), SignInRecord::getUpdateBy, request.getUpdateBy());
        // 更新时间
        lqw.eq(request.getUpdateTime() != null, SignInRecord::getUpdateTime, request.getUpdateTime());
        // 更新时间 start
        lqw.ge(request.getUpdateTimeStart() != null, SignInRecord::getUpdateTime, request.getUpdateTimeStart());
        // 更新时间 end
        lqw.le(request.getUpdateTimeEnd() != null, SignInRecord::getUpdateTime, request.getUpdateTimeEnd());
        return signInRecordMapper.selectPage(request, lqw);
    }

}