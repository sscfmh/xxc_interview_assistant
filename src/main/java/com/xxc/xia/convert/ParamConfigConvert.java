package com.xxc.xia.convert;

import com.xxc.xia.entity.ParamConfig;
import com.xxc.xia.dto.paramconfig.*;
import com.xxc.xia.common.wrapper.PageWrapper;

import java.util.*;

/**
 * 参数配置表 convert
 *
 * @author xxc
 * @create 2025-09-24 01:06:55
 */
public class ParamConfigConvert {

    public static ParamConfig convert(ParamConfigCreateRequest paramConfigCreateRequest) {
        if (paramConfigCreateRequest == null) {
            return null;
        }
        ParamConfig paramConfig = new ParamConfig();
        paramConfig.setParamType(paramConfigCreateRequest.getParamType());
        paramConfig.setParamKey(paramConfigCreateRequest.getParamKey());
        paramConfig.setParamValue(paramConfigCreateRequest.getParamValue());
        paramConfig.setValueType(paramConfigCreateRequest.getValueType());
        paramConfig.setPubFlag(paramConfigCreateRequest.getPubFlag());
        paramConfig.setExtendInfo(paramConfigCreateRequest.getExtendInfo());
        paramConfig.setCreateBy(paramConfigCreateRequest.getCreateBy());
        paramConfig.setUpdateBy(paramConfigCreateRequest.getUpdateBy());
        return paramConfig;
    }

    public static ParamConfig convert(ParamConfigUpdateRequest paramConfigUpdateRequest) {
        if (paramConfigUpdateRequest == null) {
            return null;
        }
        ParamConfig paramConfig = new ParamConfig();
        paramConfig.setParamType(paramConfigUpdateRequest.getParamType());
        paramConfig.setParamKey(paramConfigUpdateRequest.getParamKey());
        paramConfig.setParamValue(paramConfigUpdateRequest.getParamValue());
        paramConfig.setValueType(paramConfigUpdateRequest.getValueType());
        paramConfig.setPubFlag(paramConfigUpdateRequest.getPubFlag());
        paramConfig.setExtendInfo(paramConfigUpdateRequest.getExtendInfo());
        paramConfig.setCreateBy(paramConfigUpdateRequest.getCreateBy());
        paramConfig.setUpdateBy(paramConfigUpdateRequest.getUpdateBy());
        return paramConfig;
    }

    public static ParamConfigResult convert(ParamConfig entity) {
        if (entity == null) {
            return null;
        }
        ParamConfigResult paramConfigResult = new ParamConfigResult();
        paramConfigResult.setId(entity.getId());
        paramConfigResult.setParamType(entity.getParamType());
        paramConfigResult.setParamKey(entity.getParamKey());
        paramConfigResult.setParamValue(entity.getParamValue());
        paramConfigResult.setValueType(entity.getValueType());
        paramConfigResult.setPubFlag(entity.getPubFlag());
        paramConfigResult.setExtendInfo(entity.getExtendInfo());
        paramConfigResult.setCreateBy(entity.getCreateBy());
        paramConfigResult.setCreateTime(entity.getCreateTime());
        paramConfigResult.setUpdateBy(entity.getUpdateBy());
        paramConfigResult.setUpdateTime(entity.getUpdateTime());
        return paramConfigResult;
    }

    public static List<ParamConfigResult> convertList(List<ParamConfig> entityList) {
        if (entityList == null) {
            return null;
        }
        List<ParamConfigResult> paramConfigResultList = new ArrayList<>();
        for (ParamConfig entity : entityList) {
            ParamConfigResult paramConfigResult = convert(entity);
            paramConfigResultList.add(paramConfigResult);
        }
        return paramConfigResultList;
    }

    public static PageWrapper<ParamConfigResult> convertPage(PageWrapper<ParamConfig> pw) {
        if (pw == null) {
            return null;
        }
        PageWrapper<ParamConfigResult> resultPw = PageWrapper.build(pw.getTotal(), convertList(pw.getData()));
        return resultPw;
    }

}