package com.atguigu.edu.service;

import com.atguigu.edu.dto.CoursInfoForm;
import com.atguigu.edu.entity.Course;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author atguigu
 * @since 2021-01-15
 */
public interface CourseService extends IService<Course> {

    String saveCourseInfo(CoursInfoForm coursInfoForm);

    CoursInfoForm findCourseById(String id);
}
