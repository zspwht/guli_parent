package com.atguigu.crm.controller;


import com.atguigu.commonutils.Result;
import com.atguigu.crm.entity.Banner;
import com.atguigu.crm.service.BannerService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2021-02-05
 */
@Api("首页banner管理")
@RestController
@RequestMapping("/eduService/banner")
@CrossOrigin //解决跨域问题
public class BannerController {
    @Autowired
    private BannerService bannerService;

    //获取banner分页列表
    @ApiOperation("获取banner分页列表")
    @GetMapping("{page}/{limit}")
    public Result listByPage(@PathVariable Long page,@PathVariable Long limit){
        Page<Banner> pageParame = new Page<>(page,limit);
        bannerService.pageBanner(pageParame,null);
        return Result.ok().data("items",pageParame.getRecords()).data("total",pageParame.getTotal());
    }
    //新增banner
    @ApiOperation("新增banner")
    @PostMapping("addBanner")
    @CacheEvict(value = "banner",allEntries = true)
    public Result addBaner(@RequestBody Banner banner){
        boolean result = bannerService.save(banner);
        if(!result){
            return Result.error().message("保存失败");
        }
        return Result.ok();
    }
    //根据id获取bannner
    @ApiOperation("根据id获取banner")
    @GetMapping("{id}")
    public Result getBannerById(@PathVariable String id){
        Banner banner = bannerService.getById(id);
        return Result.ok().data("item",banner);
    }
    //更新banner
    @ApiOperation("更新banner")
    @PostMapping("{id}")
    @CacheEvict(value = "banner",allEntries = true)
    public Result updateBanner(@PathVariable String id,@RequestBody  Banner banner){
        banner.setId(id);
        boolean result = bannerService.updateById(banner);
        if(!result){
            return Result.error().message("更新失败");
        }
        return Result.ok();
    }
    //删除banner
    @ApiOperation("根据id删除banner")
    @DeleteMapping("{id}")
    @CacheEvict(value = "banner",allEntries = true)
    public Result deleteBanner(@PathVariable String id){
        boolean b = bannerService.removeById(id);
        if(!b){
            return Result.error().message("删除失败");
        }
        return Result.ok();
    }

    /**
     * 网站首页banner列表
     */
    @ApiOperation("获取首页banner")
    @GetMapping("getAllBanner")
    public Result getAllBanner(){
        List<Banner> banners =  bannerService.selectIndexList();
        return Result.ok().data("items",banners);
    }
}

