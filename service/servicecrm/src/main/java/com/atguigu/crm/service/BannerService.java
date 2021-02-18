package com.atguigu.crm.service;

import com.atguigu.crm.entity.Banner;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务类
 * </p>
 *
 * @author atguigu
 * @since 2021-02-05
 */
public interface BannerService extends IService<Banner> {

    void pageBanner(Page<Banner> pageParame, Object o);

    List<Banner> selectIndexList();
}
