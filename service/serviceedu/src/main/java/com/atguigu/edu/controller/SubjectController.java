package com.atguigu.edu.controller;


import com.atguigu.commonutils.Result;
import com.atguigu.edu.service.SubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2021-01-08
 */
@RestController
@Api("课程分类管理")
@CrossOrigin
@RequestMapping("/edu/subject")
public class SubjectController {
    @Autowired
    private SubjectService subjectService;

    //添加课程分类
    @ApiOperation("Excel批量导入")
    @PostMapping("addSubject")
    public Result addSubject(MultipartFile file){
        subjectService.importSubjectData(file,subjectService);
        return Result.ok();
    }
}

