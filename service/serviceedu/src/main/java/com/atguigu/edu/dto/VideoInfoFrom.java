package com.atguigu.edu.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("课时基本信息")
@Data
public class VideoInfoFrom {
    @ApiModelProperty("视频id")
    private String id;
    @ApiModelProperty("视频标题")
    private String title;
    @ApiModelProperty("课程id")
    private String courseId;
    @ApiModelProperty("章节id")
    private String chapterId;
    @ApiModelProperty("视频来源id")
    private String videoSourceId;
    @ApiModelProperty("视频排序")
    private Integer sort;
    @ApiModelProperty("是否可以试听  0：默认 1：免费")
    private Integer isFree;
}
