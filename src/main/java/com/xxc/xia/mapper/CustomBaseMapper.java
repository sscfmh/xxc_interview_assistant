package com.xxc.xia.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xxc.xia.common.request.PageRequest;
import com.xxc.xia.common.wrapper.PageWrapper;
import org.apache.ibatis.annotations.Param;

/**
 * desc..
 *
 * @author xxc
 * @version 2025/1/11 20:31
 */
public interface CustomBaseMapper<T> extends BaseMapper<T> {
    default PageWrapper<T> selectPage(PageRequest pageRequest,
                                      @Param("ew") Wrapper<T> queryWrapper) {
        // MyBatis Plus 查询
        // 页码 + 数量
        Page<T> mpPage = new Page<>(pageRequest.getPage(), pageRequest.getPageSize());
        selectPage(mpPage, queryWrapper);
        // 转换返回
        return PageWrapper.build((int) mpPage.getTotal(), mpPage.getRecords());
    }
}
