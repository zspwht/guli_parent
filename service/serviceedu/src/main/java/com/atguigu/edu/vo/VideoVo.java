package com.atguigu.edu.vo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
@ApiModel("课时信息")
@Data
public class VideoVo implements Serializable {
    private static final Long seriaVersionUID = 1L;
    private String id;
    private String title;
    private String free;
}
