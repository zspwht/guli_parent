package com.atguigu.uncenterservice.controller;


import com.atguigu.commonutils.Result;
import com.atguigu.uncenterservice.service.UcenterMemberService;
import com.atguigu.uncenterservice.vo.LoginVo;
import com.atguigu.uncenterservice.vo.RegisterVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2021-02-27
 */
@RestController
@RequestMapping("/uncenterservice/apimember")
@CrossOrigin
public class UcenterMemberController {
    @Autowired
    private UcenterMemberService ucenterMemberService;

    @ApiOperation("会员登录")
    @PostMapping("/login")
    public Result login(@RequestBody LoginVo loginVo){
        String token = ucenterMemberService.login(loginVo);
        return Result.ok().data("token",token);
    }

    @ApiOperation("会员注册")
    @PostMapping("/register")
    public Result register(@RequestBody RegisterVo registerVo){
        ucenterMemberService.register(registerVo);
        return Result.ok();
    }
}

