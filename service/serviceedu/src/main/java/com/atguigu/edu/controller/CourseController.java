package com.atguigu.edu.controller;


import com.atguigu.commonutils.Result;
import com.atguigu.edu.dto.CoursInfoForm;
import com.atguigu.edu.service.CourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

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
}

