package com.atguigu.edu.controller;

import com.atguigu.commonutils.Result;
import com.atguigu.edu.entity.Course;
import com.atguigu.edu.entity.Teacher;
import com.atguigu.edu.service.CourseService;
import com.atguigu.edu.service.TeacherService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.ibatis.io.ResolverUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.management.Query;
import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/eduservice/index")
public class IndexController {
    @Autowired
    private CourseService courseService;
    @Autowired
    private TeacherService teacherService;

    @GetMapping("")
    public Result index(){
        //查询前八条热门课程
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        queryWrapper.last("limit 8");
        List<Course> courses = courseService.list(queryWrapper);

        //查询前四条名师
        QueryWrapper<Teacher> queryTeacher = new QueryWrapper<>();
        queryTeacher.orderByDesc("id");
        queryTeacher.last("limit 4");
        List<Teacher> teachers = teacherService.list(queryTeacher);
        return Result.ok().data("courseList",courses).data("teacherList",teachers);
    }
}
