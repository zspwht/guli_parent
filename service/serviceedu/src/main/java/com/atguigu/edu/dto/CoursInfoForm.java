package com.atguigu.edu.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
@ApiModel(value = "课程基本信息",description = "编辑课程基本信息的表单对象")
@Data
public class CoursInfoForm implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "课程Id")
    private String id;
    @ApiModelProperty(value = "课程讲师Id")
    private String teacherId;
    @ApiModelProperty(value = "课程专业id")
    private String subjectId;
    @ApiModelProperty(value = "课程标题")
    private String title;
    @ApiModelProperty(value = "课程销售价格，设置为0则可以免费观看")
    private BigDecimal price;
    @ApiModelProperty(value = "总课时")
    private Integer lessonNum;
    @ApiModelProperty(value = "课程封面图片路径")
    private String cover;
    @ApiModelProperty(value = "课程简介")
    private String description;
}
