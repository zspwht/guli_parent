package com.atguigu.edu.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

@ApiModel("课程发布信息")
@Data
public class CoursePublicVo implements Serializable {
    private static final long seriaVersionUID = 1L;
    private String title;
    private String cover;
    private Integer lessonNum;
    private String subjectLeaveOne;
    private String subjectLeaveTwo;
    private String teacherName;
    private String price;  //只用于显示
}
