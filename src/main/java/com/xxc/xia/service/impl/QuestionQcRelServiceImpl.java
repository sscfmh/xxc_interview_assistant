package com.xxc.xia.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxc.xia.common.wrapper.PageWrapper;
import com.xxc.xia.common.utils.AssertUtils;
import com.xxc.xia.convert.QuestionQcRelConvert;
import com.xxc.xia.dto.questionqcrel.*;
import com.xxc.xia.entity.QuestionQcRel;
import com.xxc.xia.mapper.QuestionQcRelMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * 题目题集关联 serviceImpl
 *
 * @author xxc
 * @create 2025-09-27 12:09:35
 */
@Service
public class QuestionQcRelServiceImpl extends ServiceImpl<QuestionQcRelMapper, QuestionQcRel> {

    @Resource
    private QuestionQcRelMapper questionQcRelMapper;

    /**
     * 创建QuestionQcRel
     *
     * @param request
     * @return
     */
    public Long createQuestionQcRel(QuestionQcRelCreateRequest request) {
        // 插入
        QuestionQcRel questionQcRel = QuestionQcRelConvert.convert(request);
        questionQcRel.setCreateTime(new Date());
        questionQcRel.setUpdateTime(new Date());
        questionQcRelMapper.insert(questionQcRel);
        // 返回
        return questionQcRel.getId();
    }

    /**
     * 更新QuestionQcRel
     *
     * @param request
     * @return
     */
    public void updateQuestionQcRel(QuestionQcRelUpdateRequest request) {
        // 校验存在
        checkQuestionQcRelExists(request.getId());
        // 更新
        QuestionQcRel updateObj =  QuestionQcRelConvert.convert(request);
        updateObj.setUpdateTime(new Date());
        questionQcRelMapper.updateById(updateObj);
    }

    /**
     * 删除QuestionQcRel
     *
     * @param id
     * @return
     */
    public void deleteQuestionQcRel(Long id) {
        // 校验存在
        checkQuestionQcRelExists(id);
        // 删除
        questionQcRelMapper.deleteById(id);
    }

    /**
     * 校验QuestionQcRel是否存在
     *
     * @param id
     */
    private void checkQuestionQcRelExists(Long id) {
        QuestionQcRel questionQcRel = questionQcRelMapper.selectById(id);
        AssertUtils.notNull(questionQcRel, String.format("不存在id=%d的记录", id));
    }

    /**
     * 获取QuestionQcRel
     *
     * @param id
     * @return
     */
    public QuestionQcRel getQuestionQcRel(Long id) {
        return questionQcRelMapper.selectById(id);
    }

    /**
     * 获取QuestionQcRel列表
     *
     * @param ids
     * @return
     */
    public List<QuestionQcRel> getQuestionQcRelList(Collection<Long> ids) {
        return questionQcRelMapper.selectBatchIds(ids);
    }

    /**
     * 分页获取QuestionQcRel列表
     *
     * @param request
     * @return
     */
    public PageWrapper<QuestionQcRel> getQuestionQcRelPage(QuestionQcRelPageRequest request) {
        LambdaQueryWrapper<QuestionQcRel> lqw = new LambdaQueryWrapper<>();
        // 主键ID
        lqw.eq(request.getId() != null, QuestionQcRel::getId, request.getId());
        // 题目ID
        lqw.eq(StringUtils.isNotBlank(request.getQuestionId()), QuestionQcRel::getQuestionId, request.getQuestionId());
        // 题集ID
        lqw.eq(StringUtils.isNotBlank(request.getQcId()), QuestionQcRel::getQcId, request.getQcId());
        // 扩展信息
        lqw.eq(StringUtils.isNotBlank(request.getExtendInfo()), QuestionQcRel::getExtendInfo, request.getExtendInfo());
        // 创建人
        lqw.eq(StringUtils.isNotBlank(request.getCreateBy()), QuestionQcRel::getCreateBy, request.getCreateBy());
        // 创建时间
        lqw.eq(request.getCreateTime() != null, QuestionQcRel::getCreateTime, request.getCreateTime());
        // 创建时间 start
        lqw.ge(request.getCreateTimeStart() != null, QuestionQcRel::getCreateTime, request.getCreateTimeStart());
        // 创建时间 end
        lqw.le(request.getCreateTimeEnd() != null, QuestionQcRel::getCreateTime, request.getCreateTimeEnd());
        // 修改人
        lqw.eq(StringUtils.isNotBlank(request.getUpdateBy()), QuestionQcRel::getUpdateBy, request.getUpdateBy());
        // 修改时间
        lqw.eq(request.getUpdateTime() != null, QuestionQcRel::getUpdateTime, request.getUpdateTime());
        // 修改时间 start
        lqw.ge(request.getUpdateTimeStart() != null, QuestionQcRel::getUpdateTime, request.getUpdateTimeStart());
        // 修改时间 end
        lqw.le(request.getUpdateTimeEnd() != null, QuestionQcRel::getUpdateTime, request.getUpdateTimeEnd());
        return questionQcRelMapper.selectPage(request, lqw);
    }

}