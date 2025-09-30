package com.xxc.xia.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxc.xia.common.utils.AssertUtils;
import com.xxc.xia.common.wrapper.PageWrapper;
import com.xxc.xia.convert.ParamConfigConvert;
import com.xxc.xia.dto.paramconfig.ParamConfigCreateRequest;
import com.xxc.xia.dto.paramconfig.ParamConfigPageRequest;
import com.xxc.xia.dto.paramconfig.ParamConfigUpdateRequest;
import com.xxc.xia.entity.ParamConfig;
import com.xxc.xia.mapper.ParamConfigMapper;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * 参数配置表 serviceImpl
 *
 * @author xxc
 * @create 2025-09-24 01:06:55
 */
@Service
public class ParamConfigServiceImpl extends ServiceImpl<ParamConfigMapper, ParamConfig> {

    @Resource
    private ParamConfigMapper paramConfigMapper;

    /**
     * 创建ParamConfig
     *
     * @param request
     * @return
     */
    public Long createParamConfig(ParamConfigCreateRequest request) {
        // 插入
        ParamConfig paramConfig = ParamConfigConvert.convert(request);
        paramConfig.setCreateTime(new Date());
        paramConfig.setUpdateTime(new Date());
        paramConfigMapper.insert(paramConfig);
        // 返回
        return paramConfig.getId();
    }

    /**
     * 更新ParamConfig
     *
     * @param request
     * @return
     */
    public void updateParamConfig(ParamConfigUpdateRequest request) {
        // 校验存在
        checkParamConfigExists(request.getId());
        // 更新
        ParamConfig updateObj = ParamConfigConvert.convert(request);
        updateObj.setUpdateTime(new Date());
        paramConfigMapper.updateById(updateObj);
    }

    /**
     * 删除ParamConfig
     *
     * @param id
     * @return
     */
    public void deleteParamConfig(Long id) {
        // 校验存在
        checkParamConfigExists(id);
        // 删除
        paramConfigMapper.deleteById(id);
    }

    /**
     * 校验ParamConfig是否存在
     *
     * @param id
     */
    private void checkParamConfigExists(Long id) {
        ParamConfig paramConfig = paramConfigMapper.selectById(id);
        AssertUtils.notNull(paramConfig, String.format("不存在id=%d的记录", id));
    }

    /**
     * 获取ParamConfig
     *
     * @param id
     * @return
     */
    public ParamConfig getParamConfig(Long id) {
        return paramConfigMapper.selectById(id);
    }

    /**
     * 获取ParamConfig列表
     *
     * @param ids
     * @return
     */
    public List<ParamConfig> getParamConfigList(Collection<Long> ids) {
        return paramConfigMapper.selectBatchIds(ids);
    }

    /**
     * 分页获取ParamConfig列表
     *
     * @param request
     * @return
     */
    public PageWrapper<ParamConfig> getParamConfigPage(ParamConfigPageRequest request) {
        LambdaQueryWrapper<ParamConfig> lqw = new LambdaQueryWrapper<>();
        // 主键ID
        lqw.eq(request.getId() != null, ParamConfig::getId, request.getId());
        // 参数类型
        lqw.eq(StringUtils.isNotBlank(request.getParamType()), ParamConfig::getParamType,
            request.getParamType());
        // 参数key
        lqw.eq(StringUtils.isNotBlank(request.getParamKey()), ParamConfig::getParamKey,
            request.getParamKey());
        // 参数value
        lqw.eq(StringUtils.isNotBlank(request.getParamValue()), ParamConfig::getParamValue,
            request.getParamValue());
        // value类型
        lqw.eq(StringUtils.isNotBlank(request.getValueType()), ParamConfig::getValueType,
            request.getValueType());
        // 是否公开
        lqw.eq(StringUtils.isNotBlank(request.getPubFlag()), ParamConfig::getPubFlag,
            request.getPubFlag());
        // 扩展信息
        lqw.eq(StringUtils.isNotBlank(request.getExtendInfo()), ParamConfig::getExtendInfo,
            request.getExtendInfo());
        // 创建人
        lqw.eq(StringUtils.isNotBlank(request.getCreateBy()), ParamConfig::getCreateBy,
            request.getCreateBy());
        // 创建时间
        lqw.eq(request.getCreateTime() != null, ParamConfig::getCreateTime,
            request.getCreateTime());
        // 创建时间 start
        lqw.ge(request.getCreateTimeStart() != null, ParamConfig::getCreateTime,
            request.getCreateTimeStart());
        // 创建时间 end
        lqw.le(request.getCreateTimeEnd() != null, ParamConfig::getCreateTime,
            request.getCreateTimeEnd());
        // 修改人
        lqw.eq(StringUtils.isNotBlank(request.getUpdateBy()), ParamConfig::getUpdateBy,
            request.getUpdateBy());
        // 修改时间
        lqw.eq(request.getUpdateTime() != null, ParamConfig::getUpdateTime,
            request.getUpdateTime());
        // 修改时间 start
        lqw.ge(request.getUpdateTimeStart() != null, ParamConfig::getUpdateTime,
            request.getUpdateTimeStart());
        // 修改时间 end
        lqw.le(request.getUpdateTimeEnd() != null, ParamConfig::getUpdateTime,
            request.getUpdateTimeEnd());
        lqw.orderByDesc(ParamConfig::getUpdateTime, ParamConfig::getId);
        return paramConfigMapper.selectPage(request, lqw);
    }

}