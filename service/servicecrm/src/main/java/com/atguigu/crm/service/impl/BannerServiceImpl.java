package com.atguigu.crm.service.impl;

import com.atguigu.crm.entity.Banner;
import com.atguigu.crm.mapper.BannerMapper;
import com.atguigu.crm.service.BannerService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2021-02-05
 */
@Service
public class BannerServiceImpl extends ServiceImpl<BannerMapper, Banner> implements BannerService {

    @Override
    public void pageBanner(Page<Banner> pageParame, Object o) {
        baseMapper.selectPage(pageParame,null);
    }
    @Cacheable(value = "banner",key = "'selectIndexList'")
    @Override
    public List<Banner> selectIndexList() {
        List<Banner> banners = baseMapper.selectList(new QueryWrapper<Banner>().orderByDesc("sort"));
        return banners;
    }
}
