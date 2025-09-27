package com.xxc.xia.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxc.xia.common.wrapper.PageWrapper;
import com.xxc.xia.common.utils.AssertUtils;
import com.xxc.xia.convert.QuestionCollectionConvert;
import com.xxc.xia.dto.questioncollection.*;
import com.xxc.xia.entity.QuestionCollection;
import com.xxc.xia.mapper.QuestionCollectionMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * 题集 serviceImpl
 *
 * @author xxc
 * @create 2025-09-27 12:09:35
 */
@Service
public class QuestionCollectionServiceImpl extends ServiceImpl<QuestionCollectionMapper, QuestionCollection> {

    @Resource
    private QuestionCollectionMapper questionCollectionMapper;

    /**
     * 创建QuestionCollection
     *
     * @param request
     * @return
     */
    public Long createQuestionCollection(QuestionCollectionCreateRequest request) {
        // 插入
        QuestionCollection questionCollection = QuestionCollectionConvert.convert(request);
        questionCollection.setCreateTime(new Date());
        questionCollection.setUpdateTime(new Date());
        questionCollectionMapper.insert(questionCollection);
        // 返回
        return questionCollection.getId();
    }

    /**
     * 更新QuestionCollection
     *
     * @param request
     * @return
     */
    public void updateQuestionCollection(QuestionCollectionUpdateRequest request) {
        // 校验存在
        checkQuestionCollectionExists(request.getId());
        // 更新
        QuestionCollection updateObj =  QuestionCollectionConvert.convert(request);
        updateObj.setUpdateTime(new Date());
        questionCollectionMapper.updateById(updateObj);
    }

    /**
     * 删除QuestionCollection
     *
     * @param id
     * @return
     */
    public void deleteQuestionCollection(Long id) {
        // 校验存在
        checkQuestionCollectionExists(id);
        // 删除
        questionCollectionMapper.deleteById(id);
    }

    /**
     * 校验QuestionCollection是否存在
     *
     * @param id
     */
    private void checkQuestionCollectionExists(Long id) {
        QuestionCollection questionCollection = questionCollectionMapper.selectById(id);
        AssertUtils.notNull(questionCollection, String.format("不存在id=%d的记录", id));
    }

    /**
     * 获取QuestionCollection
     *
     * @param id
     * @return
     */
    public QuestionCollection getQuestionCollection(Long id) {
        return questionCollectionMapper.selectById(id);
    }

    /**
     * 获取QuestionCollection列表
     *
     * @param ids
     * @return
     */
    public List<QuestionCollection> getQuestionCollectionList(Collection<Long> ids) {
        return questionCollectionMapper.selectBatchIds(ids);
    }

    /**
     * 分页获取QuestionCollection列表
     *
     * @param request
     * @return
     */
    public PageWrapper<QuestionCollection> getQuestionCollectionPage(QuestionCollectionPageRequest request) {
        LambdaQueryWrapper<QuestionCollection> lqw = new LambdaQueryWrapper<>();
        // 主键ID
        lqw.eq(request.getId() != null, QuestionCollection::getId, request.getId());
        // 标题
        lqw.eq(StringUtils.isNotBlank(request.getTitle()), QuestionCollection::getTitle, request.getTitle());
        // 摘要
        lqw.eq(StringUtils.isNotBlank(request.getOutline()), QuestionCollection::getOutline, request.getOutline());
        // 用户ID
        lqw.eq(StringUtils.isNotBlank(request.getUserId()), QuestionCollection::getUserId, request.getUserId());
        // 封面图片
        lqw.eq(StringUtils.isNotBlank(request.getImgUrl()), QuestionCollection::getImgUrl, request.getImgUrl());
        // 收藏量
        lqw.eq(request.getFavCnt() != null, QuestionCollection::getFavCnt, request.getFavCnt());
        // 创建来源
        lqw.eq(StringUtils.isNotBlank(request.getCreateSource()), QuestionCollection::getCreateSource, request.getCreateSource());
        // 标签
        lqw.eq(StringUtils.isNotBlank(request.getTags()), QuestionCollection::getTags, request.getTags());
        // 扩展信息
        lqw.eq(StringUtils.isNotBlank(request.getExtendInfo()), QuestionCollection::getExtendInfo, request.getExtendInfo());
        // 创建人
        lqw.eq(StringUtils.isNotBlank(request.getCreateBy()), QuestionCollection::getCreateBy, request.getCreateBy());
        // 创建时间
        lqw.eq(request.getCreateTime() != null, QuestionCollection::getCreateTime, request.getCreateTime());
        // 创建时间 start
        lqw.ge(request.getCreateTimeStart() != null, QuestionCollection::getCreateTime, request.getCreateTimeStart());
        // 创建时间 end
        lqw.le(request.getCreateTimeEnd() != null, QuestionCollection::getCreateTime, request.getCreateTimeEnd());
        // 修改人
        lqw.eq(StringUtils.isNotBlank(request.getUpdateBy()), QuestionCollection::getUpdateBy, request.getUpdateBy());
        // 修改时间
        lqw.eq(request.getUpdateTime() != null, QuestionCollection::getUpdateTime, request.getUpdateTime());
        // 修改时间 start
        lqw.ge(request.getUpdateTimeStart() != null, QuestionCollection::getUpdateTime, request.getUpdateTimeStart());
        // 修改时间 end
        lqw.le(request.getUpdateTimeEnd() != null, QuestionCollection::getUpdateTime, request.getUpdateTimeEnd());
        return questionCollectionMapper.selectPage(request, lqw);
    }

}