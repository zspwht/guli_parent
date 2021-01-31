package com.atguigu.edu.mapper;

import com.atguigu.edu.entity.Course;
import com.atguigu.edu.vo.CoursePublicVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author atguigu
 * @since 2021-01-15
 */
public interface CourseMapper extends BaseMapper<Course> {

    CoursePublicVo getCoursePublishById(String id);
}
