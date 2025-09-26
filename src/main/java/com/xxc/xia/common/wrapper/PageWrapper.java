package com.xxc.xia.common.wrapper;

import lombok.Data;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * Desc...
 *
 * @Author xxc
 * @Date 2023/2/19 19:26
 */
@Data
public class PageWrapper<T> implements Serializable {

    private Integer total;

    private List<T> data;

    public static <T> PageWrapper<T> buildNull() {
        return build(0, Collections.emptyList());
    }

    public static <T> PageWrapper<T> build(Integer total, List<T> data) {
        PageWrapper<T> ans = new PageWrapper<>();
        ans.total = total != null ? total : 0;
        ans.data = data;
        return ans;
    }
}
