package com.atguigu.edu.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ApiModel("章节信息")
@Data
public class ChapterVo implements Serializable {
    private static final Long seriaVersionUID = 1L;
    private String id;
    private String title;
    private List<VideoVo> children = new ArrayList<>();
}
