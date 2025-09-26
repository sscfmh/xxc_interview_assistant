package com.xxc.xia.dto.useraccount;

import com.xxc.xia.common.request.BaseRequest;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * desc..
 *
 * @author xxc
 * @version 2025/1/12 15:33
 */
@Data
public class LoginRequest extends BaseRequest {

    @NotBlank
    private String userId;

    @NotBlank
    private String password;
}
