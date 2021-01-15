package com.atguigu.edu.service.impl;

import com.atguigu.edu.dto.CoursInfoForm;
import com.atguigu.edu.entity.Course;
import com.atguigu.edu.entity.CourseDescription;
import com.atguigu.edu.mapper.CourseMapper;
import com.atguigu.edu.service.CourseDescriptionService;
import com.atguigu.edu.service.CourseService;
import com.atguigu.servicebase.handle.MyException;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2021-01-15
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {
    @Autowired
    private CourseDescriptionService courseDescriptionService;
    @Override
    public String saveCourseInfo(CoursInfoForm coursInfoForm) {
        //保存课程基本信息
        Course course = new Course();
        course.setStatus(Course.COURSE_DRAFT);
        BeanUtils.copyProperties(coursInfoForm,course);
        boolean resultCourseInfo = this.save(course);
        if(!resultCourseInfo){
            throw new MyException(20001,"课程信息保存失败");
        }
        //保存课程详细信息
        CourseDescription courseDescription = new CourseDescription();
        courseDescription.setDescription(coursInfoForm.getDescription());
        courseDescription.setId(course.getId());
        boolean resultCourseDescrption = courseDescriptionService.save(courseDescription);
        if(!resultCourseDescrption){
            throw new MyException(20001,"课程详细信息保存失败");
        }
        return course.getId();
    }
}