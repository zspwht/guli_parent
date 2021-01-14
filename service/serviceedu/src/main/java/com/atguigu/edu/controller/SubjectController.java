package com.atguigu.edu.controller;


import com.atguigu.commonutils.Result;
import com.atguigu.edu.service.SubjectService;
import com.atguigu.edu.vo.SubjectNestedVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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
@RequestMapping("/admin/edu/subject")
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
    @ApiOperation("嵌套数据列表")
    @GetMapping("")
    public Result nestedList(){
        List<SubjectNestedVo> subjectNestedVoList =  subjectService.nestedList();
        return Result.ok().data("items",subjectNestedVoList);
    }
}

