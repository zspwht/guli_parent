package com.atguigu.edu.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel("course查询对象")
@Data
public class CourseQuery implements Serializable {
   private static final Long seriaVersionUID = 1L;
   @ApiModelProperty("课程名称")
   private String title;
   @ApiModelProperty("讲师id")
   private String teacherId;
   @ApiModelProperty("一级分类ID")
   private String subjectParentId;
   @ApiModelProperty("二级分类id")
   private String subjectId;
}
