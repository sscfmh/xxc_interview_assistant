package com.xxc.xia.controller;

import com.xxc.xia.common.annotation.RequirePermissions;
import com.xxc.xia.common.request.ListRequest;
import com.xxc.xia.common.result.Result;
import com.xxc.xia.common.result.ResultFactory;
import com.xxc.xia.common.wrapper.PageWrapper;
import com.xxc.xia.convert.TagConvert;
import com.xxc.xia.dto.tag.TagCreateRequest;
import com.xxc.xia.dto.tag.TagPageRequest;
import com.xxc.xia.dto.tag.TagResult;
import com.xxc.xia.dto.tag.TagUpdateRequest;
import com.xxc.xia.entity.Tag;
import com.xxc.xia.service.impl.TagServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 标签 controller
 *
 * @author xxc
 * @create 2025-10-07 17:58:50
 */
@io.swagger.v3.oas.annotations.tags.Tag(name = "标签")
@RestController
@RequestMapping("/api/tag")
public class TagController {

    @Autowired
    private TagServiceImpl tagService;

    /**
     * 创建一个
     *
     * @param request
     * @return
     */
    @Operation(summary = "创建一个")
    @RequirePermissions(value = { "tag:create" })
    @PostMapping("/createTag")
    public Result<String> createTag(@RequestBody @Validated TagCreateRequest request) {
        Long id = tagService.createTag(request);
        return ResultFactory.success(String.valueOf(id));
    }

    /**
     * 删除一个
     *
     * @param id
     * @return
     */
    @Operation(summary = "删除一个")
    @RequirePermissions(value = { "tag:delete" })
    @PostMapping("/deleteTagById/{id}")
    public Result<Boolean> deleteTagById(@PathVariable("id") Long id) {
        tagService.deleteTag(id);
        return ResultFactory.success(true);
    }

    /**
     * 修改一个
     *
     * @param request
     * @return
     */
    @Operation(summary = "修改一个")
    @RequirePermissions(value = { "tag:update" })
    @PostMapping("/updateTagById")
    public Result<Boolean> updateTagById(@RequestBody @Validated TagUpdateRequest request) {
        tagService.updateTag(request);
        return ResultFactory.success(true);
    }

    /**
     * 查询一个
     *
     * @param id
     * @return
     */
    @Operation(summary = "查询一个")
//    @RequirePermissions(value = { "tag:query" })
    @GetMapping("/queryTagById/{id}")
    public Result<TagResult> queryTagById(@PathVariable("id") Long id) {
        Tag tag = tagService.getTag(id);
        return ResultFactory.success(TagConvert.convert(tag));
    }

    /**
     * 列表查询
     *
     * @param request
     * @return
     */
    @Operation(summary = "列表查询")
//    @RequirePermissions(value = { "tag:query" })
    @PostMapping("/listQueryTagByIds")
    public Result<List<TagResult>> listQueryTagByIds(@RequestBody ListRequest request) {
        List<Long> ids = request.getIds();
        List<Tag> source = null;
        if (CollectionUtils.isEmpty(ids)) {
            source = tagService.list();
        } else {
            source = tagService.getTagList(ids);
            ;
        }
        List<TagResult> data = TagConvert.convertList(source);
        return ResultFactory.success(data);
    }

    /**
     * 分页查询
     *
     * @param request
     * @return
     */
    @Operation(summary = "分页查询")
//    @RequirePermissions(value = { "tag:query" })
    @PostMapping("/pageQueryTag")
    public Result<PageWrapper<TagResult>> pageQueryTag(@RequestBody @Validated TagPageRequest request) {
        PageWrapper<Tag> pageWrapper = tagService.getTagPage(request);
        return ResultFactory.success(TagConvert.convertPage(pageWrapper));
    }
}
