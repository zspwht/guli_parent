package com.atguigu.uncenterservice.service.impl;

import com.atguigu.servicebase.handle.MyException;
import com.atguigu.uncenterservice.entity.UcenterMember;
import com.atguigu.uncenterservice.mapper.UcenterMemberMapper;
import com.atguigu.uncenterservice.service.UcenterMemberService;
import com.atguigu.uncenterservice.utils.MD5;
import com.atguigu.uncenterservice.vo.LoginVo;
import com.atguigu.uncenterservice.vo.RegisterVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2021-02-27
 */
@Service
public class UcenterMemberServiceImpl extends ServiceImpl<UcenterMemberMapper, UcenterMember> implements UcenterMemberService {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    /**
     * 会员登录
     * @param loginVo
     * @return
     */
    @Override
    public String login(LoginVo loginVo) {
        String mobile = loginVo.getMobile();
        String password = loginVo.getPassword();

        //校验参数
        if(StringUtils.isEmpty(mobile)||StringUtils.isEmpty(password)){
            throw new MyException(20001,"error");
        }
        //获取会员
        UcenterMember member = baseMapper.selectOne(new QueryWrapper<UcenterMember>().eq("mobile", mobile));
        if(null==member){
            throw new MyException(20001,"error");
        }
        //校验密码
        if(!MD5.encrypt(password).equals(member.getPassword())){
            throw new MyException(20001,"error");
        }
        //校验是否被禁用
        if(member.getIsDisabled()){
            throw new MyException(20001,"error");
        }

        //使用jwt生成token字符串 todo
        String token = "";
        return token;
    }

    /**
     * 会员注册
     * @param registerVo
     * @return
     */
    @Override
    public void register(RegisterVo registerVo) {
        //获取注册信息，进行校验
        String nickname = registerVo.getNickname();
        String mobiel = registerVo.getMobiel();
        String password = registerVo.getPassword();
        String code = registerVo.getCode();

        if(StringUtils.isEmpty(nickname)||StringUtils.isEmpty(mobiel)
                ||StringUtils.isEmpty(password)||StringUtils.isEmpty(code)){
            throw new MyException(20001,"error");
        }

        //从redis获取发送的验证码
        String mobileCode = redisTemplate.opsForValue().get(mobiel);
        if(!code.equals(mobileCode)){
            throw new MyException(20001,"error");
        }

        //查看数据库是否存在相同的手机号码

        Integer count = baseMapper.selectCount(new QueryWrapper<UcenterMember>().eq("mobile", mobiel));
        if(count.intValue()>0){
            throw new MyException(20001,"error");
        }

        //添加注册信息
        UcenterMember ucenterMember = new UcenterMember();
        ucenterMember.setNickname(nickname);
        ucenterMember.setMobile(mobiel);
        ucenterMember.setPassword(MD5.encrypt(password));
        ucenterMember.setIsDisabled(false);
        ucenterMember.setAvatar("http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoj0hHXhgJNOTSOFsS4uZs8x1ConecaVOB8eIl115xmJZcT4oCicvia7wMEufibKtTLqiaJeanU2Lpg3w/132");
        this.save(ucenterMember);
    }
}
