package com.atguigu.uncenterservice.service;

import com.atguigu.uncenterservice.entity.UcenterMember;
import com.atguigu.uncenterservice.vo.LoginVo;
import com.atguigu.uncenterservice.vo.RegisterVo;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author atguigu
 * @since 2021-02-27
 */
public interface UcenterMemberService extends IService<UcenterMember> {

    String login(LoginVo loginVo);

    void register(RegisterVo registerVo);
}
