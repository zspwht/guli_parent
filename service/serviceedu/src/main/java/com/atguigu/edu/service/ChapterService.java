package com.atguigu.edu.service;

import com.atguigu.edu.entity.Chapter;
import com.atguigu.edu.vo.ChapterVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author atguigu
 * @since 2021-01-27
 */
public interface ChapterService extends IService<Chapter> {

    List<ChapterVo> nestedList(String courseId);

    boolean removeChapterById(String id);
}
