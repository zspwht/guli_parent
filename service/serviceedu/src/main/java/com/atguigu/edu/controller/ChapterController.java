package com.atguigu.edu.controller;


import com.atguigu.commonutils.Result;
import com.atguigu.edu.entity.Chapter;
import com.atguigu.edu.service.ChapterService;
import com.atguigu.edu.service.VideoService;
import com.atguigu.edu.vo.ChapterVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2021-01-27
 */
@Api("课程章节管理")
@CrossOrigin
@RestController
@RequestMapping("/admin/edu/chapter")
public class ChapterController {
    @Autowired
    private ChapterService chapterService;

    @ApiOperation("嵌套章节数据列表")
    @GetMapping("nestedList/{courseId}")
    public Result nestedListByCourseId(@PathVariable String courseId){
        List<ChapterVo> chapterVoList = chapterService.nestedList(courseId);
        return Result.ok().data("items",chapterVoList);
    }

    @ApiOperation("新增章节")
    @PostMapping("saveChapter")
    public Result saveChapter(@RequestBody Chapter chapter){
        boolean save = chapterService.save(chapter);
        return Result.ok();
    }

    @ApiOperation("根据id修改章节")
    @PostMapping("{id}")
    public Result updateById(@PathVariable String id,@RequestBody Chapter chapter){
        chapter.setId(id);
        boolean b = chapterService.updateById(chapter);
        System.out.println(b);
        if(b){
            return Result.ok();
        }else {
            return Result.error().message("更新失败");
        }

    }

    @ApiOperation("根据id查询章节")
    @GetMapping("{id}")
    public Result getById(@PathVariable String id){
        Chapter chapter = chapterService.getById(id);
        return Result.ok().data("item",chapter);
    }

    @ApiOperation("根据id删除章节")
    @DeleteMapping("{id}")
    public Result deleteById(@PathVariable String id) {
        boolean result = chapterService.removeChapterById(id);
        if (result) {
            return Result.ok();
        } else {
            return Result.error().message("删除失败");
        }
    }
}

