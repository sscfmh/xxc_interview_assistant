package com.xxc.xia.dto.useraccount;

import com.xxc.xia.common.request.BaseRequest;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * desc..
 *
 * @author xxc
 * @version 2025/1/12 15:37
 */
@Data
public class RegisterRequest extends BaseRequest {

    @NotBlank
    private String nickName;

    @NotBlank
    private String password;

    private String avatar;

    private String sex;

    private String phoneNumber;

    private String email;
}
