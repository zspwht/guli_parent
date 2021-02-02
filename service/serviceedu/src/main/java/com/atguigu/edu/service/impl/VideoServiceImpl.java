package com.atguigu.edu.service.impl;

import com.atguigu.edu.dto.VideoInfoFrom;
import com.atguigu.edu.entity.Video;
import com.atguigu.edu.mapper.VideoMapper;
import com.atguigu.edu.service.VideoService;
import com.atguigu.servicebase.handle.MyException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2021-01-27
 */
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements VideoService {

    @Override
    public boolean getCountByChapterId(String id) {
        QueryWrapper<Video> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("chapter_id",id);
        Integer integer = baseMapper.selectCount(queryWrapper);
        return null!=integer&&integer>0;
    }

    @Override
    public Boolean saveVideo(VideoInfoFrom videoInfoFrom) {
        Video video = new Video();
        BeanUtils.copyProperties(videoInfoFrom,video);
        boolean result = this.save(video);
        if(!result){
            throw new MyException(20001,"课时信息保存失败");
        }
        return result;
    }

    @Override
    public VideoInfoFrom getViedoById(String id) {
        Video video = this.getById(id);
        if(video==null){
            throw new MyException(20001,"数据不存在");
        }
        VideoInfoFrom videoInfoFrom = new VideoInfoFrom();
        BeanUtils.copyProperties(video,videoInfoFrom);
        return videoInfoFrom;
    }

    @Override
    public Boolean updateVideById(VideoInfoFrom videoInfoFrom) {
        Video video = new Video();
        BeanUtils.copyProperties(videoInfoFrom,video);
        boolean result = this.updateById(video);
        if(!result){
            throw new MyException(20001,"课时信息保存失败");
        }
        return result;
    }

    @Override
    public Boolean deleteById(String id) {
        Integer count = baseMapper.deleteById(id);
        return null!=count&&count>0;
    }

    @Override
    public void removeVideoByCourseId(String id) {
        QueryWrapper<Video> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id",id);
        baseMapper.delete(queryWrapper);
    }
}
