package com.atguigu.edu.query;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
@ApiModel(value = "Teacher查询对象",description = "讲师对象查询封装")
@Data
public class TeacherQuery implements Serializable {
    private static final long seriaVersionUID = 1L;
    @ApiModelProperty(value = "教师名称，模糊查询")
    private String name;
    @ApiModelProperty(value = "头衔 1、高级讲师 2、首席讲师")
    private Integer level;
    @ApiModelProperty(value = "查询开始时间")
    private String beginTime;
    @ApiModelProperty(value = "查询结束时间")
    private String endTime;
}
