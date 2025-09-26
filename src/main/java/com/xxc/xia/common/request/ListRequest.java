package com.xxc.xia.common.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * desc..
 *
 * @author xxc
 * @version 2025/9/26 23:22
 */
@Data
public class ListRequest extends BaseRequest {

    @Schema(description = "id数组")
    private List<Long> ids;
}
