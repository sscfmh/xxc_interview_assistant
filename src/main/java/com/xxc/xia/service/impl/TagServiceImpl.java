package com.xxc.xia.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxc.xia.common.wrapper.PageWrapper;
import com.xxc.xia.common.utils.AssertUtils;
import com.xxc.xia.convert.TagConvert;
import com.xxc.xia.dto.tag.*;
import com.xxc.xia.entity.Tag;
import com.xxc.xia.mapper.TagMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * 标签 serviceImpl
 *
 * @author xxc
 * @create 2025-10-07 17:58:50
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> {

    @Resource
    private TagMapper tagMapper;

    /**
     * 创建Tag
     *
     * @param request
     * @return
     */
    public Long createTag(TagCreateRequest request) {
        // 插入
        Tag tag = TagConvert.convert(request);
        tag.setCreateTime(new Date());
        tag.setUpdateTime(new Date());
        tagMapper.insert(tag);
        // 返回
        return tag.getId();
    }

    /**
     * 更新Tag
     *
     * @param request
     * @return
     */
    public void updateTag(TagUpdateRequest request) {
        // 校验存在
        checkTagExists(request.getId());
        // 更新
        Tag updateObj =  TagConvert.convert(request);
        updateObj.setUpdateTime(new Date());
        tagMapper.updateById(updateObj);
    }

    /**
     * 删除Tag
     *
     * @param id
     * @return
     */
    public void deleteTag(Long id) {
        // 校验存在
        checkTagExists(id);
        // 删除
        tagMapper.deleteById(id);
    }

    /**
     * 校验Tag是否存在
     *
     * @param id
     */
    private void checkTagExists(Long id) {
        Tag tag = tagMapper.selectById(id);
        AssertUtils.notNull(tag, String.format("不存在id=%d的记录", id));
    }

    /**
     * 获取Tag
     *
     * @param id
     * @return
     */
    public Tag getTag(Long id) {
        return tagMapper.selectById(id);
    }

    /**
     * 获取Tag列表
     *
     * @param ids
     * @return
     */
    public List<Tag> getTagList(Collection<Long> ids) {
        return tagMapper.selectBatchIds(ids);
    }

    /**
     * 分页获取Tag列表
     *
     * @param request
     * @return
     */
    public PageWrapper<Tag> getTagPage(TagPageRequest request) {
        LambdaQueryWrapper<Tag> lqw = new LambdaQueryWrapper<>();
        // ID
        lqw.eq(request.getId() != null, Tag::getId, request.getId());
        // 名称
        lqw.eq(StringUtils.isNotBlank(request.getTagName()), Tag::getTagName, request.getTagName());
        // 介绍
        lqw.eq(StringUtils.isNotBlank(request.getTagIntroduce()), Tag::getTagIntroduce, request.getTagIntroduce());
        // tag头图地址
        lqw.eq(StringUtils.isNotBlank(request.getTagImgUrl()), Tag::getTagImgUrl, request.getTagImgUrl());
        // 扩展信息
        lqw.eq(StringUtils.isNotBlank(request.getExtendInfo()), Tag::getExtendInfo, request.getExtendInfo());
        // 创建者
        lqw.eq(StringUtils.isNotBlank(request.getCreateBy()), Tag::getCreateBy, request.getCreateBy());
        // 创建时间
        lqw.eq(request.getCreateTime() != null, Tag::getCreateTime, request.getCreateTime());
        // 创建时间 start
        lqw.ge(request.getCreateTimeStart() != null, Tag::getCreateTime, request.getCreateTimeStart());
        // 创建时间 end
        lqw.le(request.getCreateTimeEnd() != null, Tag::getCreateTime, request.getCreateTimeEnd());
        // 更新者
        lqw.eq(StringUtils.isNotBlank(request.getUpdateBy()), Tag::getUpdateBy, request.getUpdateBy());
        // 更新时间
        lqw.eq(request.getUpdateTime() != null, Tag::getUpdateTime, request.getUpdateTime());
        // 更新时间 start
        lqw.ge(request.getUpdateTimeStart() != null, Tag::getUpdateTime, request.getUpdateTimeStart());
        // 更新时间 end
        lqw.le(request.getUpdateTimeEnd() != null, Tag::getUpdateTime, request.getUpdateTimeEnd());
        // 排序
        lqw.orderByDesc(Tag::getId);
        return tagMapper.selectPage(request, lqw);
    }

}