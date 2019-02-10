package com.shiren.sjwt.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("userVo")
@Data
public class UserVo {

    @ApiModelProperty("userName")
    private String userName;

    @ApiModelProperty("userPwd")
    private String userPwd;

}

