package com.xxc.xia.convert;

import com.xxc.xia.common.wrapper.PageWrapper;
import com.xxc.xia.dto.signinrecord.SignInRecordCreateRequest;
import com.xxc.xia.dto.signinrecord.SignInRecordResult;
import com.xxc.xia.dto.signinrecord.SignInRecordUpdateRequest;
import com.xxc.xia.entity.SignInRecord;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 签到记录 convert
 *
 * @author xxc
 * @create 2025-09-27 12:09:35
 */
public class SignInRecordConvert {

    public static SignInRecord convert(SignInRecordCreateRequest signInRecordCreateRequest) {
        if (signInRecordCreateRequest == null) {
            return null;
        }
        SignInRecord signInRecord = new SignInRecord();
        signInRecord.setBizType(signInRecordCreateRequest.getBizType());
        signInRecord.setBizId(signInRecordCreateRequest.getBizId());
        signInRecord.setYm(signInRecordCreateRequest.getYm());
        signInRecord.setMark(signInRecordCreateRequest.getMark());
        signInRecord.setExtendInfo(signInRecordCreateRequest.getExtendInfo());
        signInRecord.setCreateBy(signInRecordCreateRequest.getCreateBy());
        signInRecord.setUpdateBy(signInRecordCreateRequest.getUpdateBy());
        return signInRecord;
    }

    public static SignInRecord convert(SignInRecordUpdateRequest signInRecordUpdateRequest) {
        if (signInRecordUpdateRequest == null) {
            return null;
        }
        SignInRecord signInRecord = new SignInRecord();
        signInRecord.setId(signInRecordUpdateRequest.getId());
        signInRecord.setBizType(signInRecordUpdateRequest.getBizType());
        signInRecord.setBizId(signInRecordUpdateRequest.getBizId());
        signInRecord.setYm(signInRecordUpdateRequest.getYm());
        signInRecord.setMark(signInRecordUpdateRequest.getMark());
        signInRecord.setExtendInfo(signInRecordUpdateRequest.getExtendInfo());
        signInRecord.setCreateBy(signInRecordUpdateRequest.getCreateBy());
        signInRecord.setUpdateBy(signInRecordUpdateRequest.getUpdateBy());
        return signInRecord;
    }

    public static SignInRecordResult convert(SignInRecord entity) {
        if (entity == null) {
            return null;
        }
        SignInRecordResult signInRecordResult = new SignInRecordResult();
        signInRecordResult.setId(entity.getId());
        signInRecordResult.setBizType(entity.getBizType());
        signInRecordResult.setBizId(entity.getBizId());
        signInRecordResult.setYm(entity.getYm());
        signInRecordResult.setMark(entity.getMark());
        signInRecordResult.setMarkDateList(parseMark(entity.getYm(), entity.getMark()));
        signInRecordResult.setExtendInfo(entity.getExtendInfo());
        signInRecordResult.setCreateBy(entity.getCreateBy());
        signInRecordResult.setCreateTime(entity.getCreateTime());
        signInRecordResult.setUpdateBy(entity.getUpdateBy());
        signInRecordResult.setUpdateTime(entity.getUpdateTime());
        return signInRecordResult;
    }

    public static List<SignInRecordResult> convertList(List<SignInRecord> entityList) {
        if (entityList == null) {
            return null;
        }
        List<SignInRecordResult> signInRecordResultList = new ArrayList<>();
        for (SignInRecord entity : entityList) {
            SignInRecordResult signInRecordResult = convert(entity);
            signInRecordResultList.add(signInRecordResult);
        }
        return signInRecordResultList;
    }

    public static PageWrapper<SignInRecordResult> convertPage(PageWrapper<SignInRecord> pw) {
        if (pw == null) {
            return null;
        }
        PageWrapper<SignInRecordResult> resultPw = PageWrapper.build(pw.getTotal(),
            convertList(pw.getData()));
        return resultPw;
    }

    private static List<String> parseMark(String ym, Integer mark) {
        if (StringUtils.isBlank(ym) || mark == null) {
            return Collections.emptyList();
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        List<String> res = new ArrayList<>();
        int dn = 1;
        while (mark > 0) {
            if ((mark & 1) == 1) {
                res.add(ym + StringUtils.leftPad(dn + "", 2, '0'));
            }
            dn++;
            mark >>= 1;
        }
        return res;
    }

}