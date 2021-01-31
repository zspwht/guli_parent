package com.atguigu.edu.service.impl;

import com.atguigu.edu.dto.CoursInfoForm;
import com.atguigu.edu.entity.Course;
import com.atguigu.edu.entity.CourseDescription;
import com.atguigu.edu.mapper.CourseMapper;
import com.atguigu.edu.service.CourseDescriptionService;
import com.atguigu.edu.service.CourseService;
import com.atguigu.edu.vo.CoursePublicVo;
import com.atguigu.servicebase.handle.MyException;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
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

    @Override
    public CoursInfoForm findCourseById(String id) {
        Course course = this.getById(id);
        if(course==null){
            throw new MyException(20001,"数据不存在");
        }
        CoursInfoForm coursInfoForm = new CoursInfoForm();
        BeanUtils.copyProperties(course,coursInfoForm);
        CourseDescription description = courseDescriptionService.getById(id);
        if(description!=null){
            coursInfoForm.setDescription(description.getDescription());
        }
        return coursInfoForm;
    }

    @Override
    public void updateCorseInfo(CoursInfoForm coursInfoForm) {
        //保存课程基本信息
        Course course = new Course();
        BeanUtils.copyProperties(coursInfoForm,course);
        boolean isSuccess = this.updateById(course);
        if(!isSuccess){
            throw new MyException(20001,"课程信息保存失败");
        }
        //保存课程详细信息
        CourseDescription courseDescription = new CourseDescription();
        courseDescription.setId(course.getId());
        courseDescription.setDescription(coursInfoForm.getDescription());
        boolean resultCD = courseDescriptionService.updateById(courseDescription);
        if(!resultCD){
            throw  new MyException(20001,"课程详细信息保存失败");
        }
    }

    /**
     * 根据课程id查找课程发布信息
     * @param id
     * @return
     */
    @Override
    public CoursePublicVo searchCoursePublishById(String id) {
        return baseMapper.getCoursePublishById(id);
    }

    /**
     * 根据id发布课程
     * @param id
     */
    @Override
    public void publishCourseById(String id) {
        Course course = new Course();
        course.setId(id);
        course.setStatus("Normal");
        int i = baseMapper.updateById(course);
        log.info("是否成功：{}",i);
    }
}
