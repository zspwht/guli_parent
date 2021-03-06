package com.atguigu.edu.controller;


import com.atguigu.commonutils.Result;
import com.atguigu.edu.dto.CoursInfoForm;
import com.atguigu.edu.entity.Course;
import com.atguigu.edu.query.CourseQuery;
import com.atguigu.edu.service.CourseService;
import com.atguigu.edu.vo.CoursePublicVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2021-01-15
 */
@Api(value = "课程管理")
@CrossOrigin //跨域处理
@RestController
@RequestMapping("/admin/edu/course")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @ApiOperation(value = "新增课程")
    @PostMapping("saveCourseInfo")
    public Result saveCourseInfo(@ApiParam(name = "CourseInfoForm",value = "课程基本信息",required = true)
                                 @RequestBody CoursInfoForm coursInfoForm){
        String courseId = courseService.saveCourseInfo(coursInfoForm);
        if(!StringUtils.isEmpty(courseId)){
            return Result.ok().data("courseId",courseId);
        }else{
            return Result.error().message("保存失败");
        }
    }

    /**
     * 根据id查询课程
     * @param id
     * @return
     */
    @ApiOperation("根据id查询课程")
    @GetMapping("courseInfo/{id}")
    public Result searchCourseById(@PathVariable("id") String id){
        CoursInfoForm courseInfo = courseService.findCourseById(id);
        return Result.ok().data("item",courseInfo);
    }

    @ApiOperation("更新课程")
    @PostMapping("courseInfo/{id}")
    public Result updateCourseInfo(@PathVariable String id,@RequestBody CoursInfoForm coursInfoForm){
        courseService.updateCorseInfo(coursInfoForm);
        return Result.ok().data("courseId",id);
    }

    @ApiOperation("根据课程id查找发布信息")
    @GetMapping("coursePubicInfo/{id}")
    public Result getCoursePublishById(@PathVariable String id){
        CoursePublicVo coursePublicInfo = courseService.searchCoursePublishById(id);
        return Result.ok().data("item",coursePublicInfo);
    }

    @ApiOperation("根据id发布课程")
    @PostMapping("publishCourse/{id}")
    public Result publishCourseById(@PathVariable String id){
        courseService.publishCourseById(id);
        return Result.ok();
    }

    @ApiOperation("分页课程列表")
    @PostMapping("{page}/{limit}")
    public Result queryCourseByPage(@PathVariable Long page, @PathVariable Long limit, @RequestBody CourseQuery courseQuery){
        Page<Course> pageParam = new Page<>(page,limit);
        courseService.queryByPage(pageParam,courseQuery);
        List<Course> records = pageParam.getRecords();
        long total = pageParam.getTotal();
        return Result.ok().data("total",total).data("rows",records);
    }

    @ApiOperation("根据id删除课程信息")
    @DeleteMapping("{id}")
    public Result deleteCourseById(@PathVariable String id){
        Boolean result = courseService.removeCourseById(id);
        if(!result){
            return Result.error().message("删除失败");
        }
        return Result.ok();
    }
}

