package com.atguigu.edu.controller;


import com.atguigu.commonutils.Result;
import com.atguigu.edu.dto.VideoInfoFrom;
import com.atguigu.edu.entity.Video;
import com.atguigu.edu.service.VideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2021-01-27
 */
@Api("课时管理")
@CrossOrigin
@RestController
@RequestMapping("/admin/edu/video")
public class VideoController {
    @Autowired
    private VideoService videoService;

    @ApiOperation("新增课时")
    @PostMapping("saveVideo")
    public Result saveVideo(@RequestBody VideoInfoFrom videoInfoFrom){
        Boolean result = videoService.saveVideo(videoInfoFrom);
        if(result){
            return Result.ok();
        }else{
            return Result.error().message("保存失败");
        }
    }

    @ApiOperation("根据id查询课时")
    @GetMapping("{id}")
    public Result findVideoById(@PathVariable String id){
        VideoInfoFrom videoInfoFrom = videoService.getViedoById(id);
        return Result.ok().data("item",videoInfoFrom);
    }

    @ApiOperation("根据id更新课时")
    @PostMapping("{id}")
    public Result updataVideo(@PathVariable String id,@RequestBody VideoInfoFrom videoInfoFrom){
        Boolean result = videoService.updateVideById(videoInfoFrom);
        if(!result){
            return Result.error().message("更新失败");
        }
        return Result.ok();
    }

    @ApiOperation("根据id删除课时")
    @DeleteMapping("{id}")
    public Result deleteVideById(@PathVariable String id){
        Boolean result = videoService.deleteById(id);
        if(!result){
            return Result.error().message("删除失败");
        }
        return Result.ok();
    }
}

