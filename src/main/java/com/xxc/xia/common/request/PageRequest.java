package com.xxc.xia.common.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * desc..
 *
 * @author xxc
 * @version 2025/1/11 14:14
 */
@Data
public class PageRequest extends BaseRequest {
    private static final Integer PAGE_NO   = 1;
    private static final Integer PAGE_SIZE = 10;

    @Schema(description = "页码，从 1 开始")
    @NotNull(message = "页码不能为空")
    @Min(value = 1, message = "页码最小值为 1")
    private Integer              page      = PAGE_NO;

    @Schema(description = "每页条数，最大值为 100")
    @NotNull(message = "每页条数不能为空")
    @Min(value = 1, message = "每页条数最小值为 1")
    @Max(value = 100, message = "每页条数最大值为 100")
    private Integer              pageSize  = PAGE_SIZE;
}
