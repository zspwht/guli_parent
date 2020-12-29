package com.atguigu.edu.controller;


import com.atguigu.commonutils.Result;
import com.atguigu.edu.entity.Teacher;
import com.atguigu.edu.query.TeacherQuery;
import com.atguigu.edu.service.TeacherService;
import com.atguigu.servicebase.handle.MyException;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2020-12-07
 */
@Api("讲师管理")
@RestController
@RequestMapping("/admin/edu/teacher")
@CrossOrigin
public class TeacherController {
    @Autowired
    private TeacherService teacherService;
    @ApiOperation("所有讲师列表")
    @GetMapping
    public Result list(){
        List<Teacher> list = teacherService.list(null);
        return Result.ok().data("items",list);
    }
    @ApiOperation("根据id删除讲师")
    @DeleteMapping("{id}")
    public Result deleteById(@ApiParam(name = "id",value = "讲师id",required = true)
                                  @PathVariable String id){
        boolean result = teacherService.removeById(id);
        if(result){
            return Result.ok();
        }else{
            return Result.error().message("删除失败");
        }

    }

    //分页查询
    @ApiOperation("分页讲师列表")
    @GetMapping ("{page}/{limit}")
    public Result queryList(@PathVariable Long page,
                            @PathVariable Long limit,
                            TeacherQuery teacherQuery){
        Page<Teacher> pageParam = new Page<>(page,limit);
        teacherService.pageQuery(pageParam,teacherQuery);
        List<Teacher> records = pageParam.getRecords();
        long total = pageParam.getTotal();
        return  Result.ok().data("total",total).data("rows",records);
    }

    //新增讲师
    @ApiOperation(value = "新增讲师")
    @PostMapping("/add")
    public Result save(@RequestBody Teacher teacher){
        teacherService.save(teacher);
        return Result.ok().message("添加成功");
    }
    //根据id查询

    @ApiOperation(value = "根据id查询讲师")
    @GetMapping("{id}")
    public Result findById(@PathVariable String id){
        Teacher teacher = teacherService.getById(id);
        return  Result.ok().data("row",teacher);
    }
    //更新
    @ApiOperation(value = "更新讲师")
    @PutMapping("{id}")
    public Result updateById(@PathVariable String id,@RequestBody Teacher teacher){
        teacher.setId(id);
        teacherService.updateById(teacher);
        return Result.ok();
    }
}

