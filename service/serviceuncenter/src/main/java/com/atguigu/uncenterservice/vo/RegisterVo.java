package com.atguigu.uncenterservice.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "注册对象",description = "注册对象")
public class RegisterVo {
    @ApiModelProperty("昵称")
    private String nickname;
    @ApiModelProperty("手机号")
    private String mobiel;
    @ApiModelProperty("密码")
    private String password;
    @ApiModelProperty("验证码")
    private String code;
}
