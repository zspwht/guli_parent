package com.atguigu.edu.controller;

import com.atguigu.commonutils.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("adminLogin/user")
@CrossOrigin
public class LoginController {
    @ApiOperation("登录")
    @PostMapping("/login")
    public Result login(){
        return Result.ok().data("token","admin-token");
    }
    @ApiOperation("获取信息")
    @GetMapping("/info")
    public Result info(){
        return Result.ok().data("roles","[admin]");
    }
}
