package com.xxc.xia.dto.signinrecord;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xxc.xia.common.request.BaseRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * desc..
 *
 * @author xxc
 * @version 2025/10/25 13:57
 */
@Data
public class QueryUserAQSignInRequest extends BaseRequest {
    @Schema(description = "查询时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date queryTime;

}
