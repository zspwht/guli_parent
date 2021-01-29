package com.atguigu.edu.service.impl;

import com.atguigu.edu.entity.Chapter;
import com.atguigu.edu.entity.Video;
import com.atguigu.edu.mapper.ChapterMapper;
import com.atguigu.edu.service.ChapterService;
import com.atguigu.edu.service.VideoService;
import com.atguigu.edu.vo.ChapterVo;
import com.atguigu.edu.vo.VideoVo;
import com.atguigu.servicebase.handle.MyException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.gson.internal.$Gson$Preconditions;
import io.swagger.models.auth.In;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2021-01-27
 */
@Service
public class ChapterServiceImpl extends ServiceImpl<ChapterMapper, Chapter> implements ChapterService {
    @Autowired
    private VideoService videoService;
    @Override
    public List<ChapterVo> nestedList(String courseId) {
        ArrayList<ChapterVo> chapterVos = new ArrayList<>();
        //获取章节信息
        QueryWrapper<Chapter> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id",courseId);
        queryWrapper.orderByDesc("sort","id");
        List<Chapter> chapters = baseMapper.selectList(queryWrapper);
        //获取课时信息
        QueryWrapper<Video> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("course_id",courseId);
        queryWrapper1.orderByDesc("sort","id");
        List<Video> videos = videoService.list(queryWrapper1);
        //填充章节vo数据
        for (int i = 0; i < chapters.size(); i++) {
            Chapter chapter = chapters.get(i);
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(chapter,chapterVo);
            chapterVos.add(chapterVo);
            //填充课时vo数据
            ArrayList<VideoVo> videoVoArrayList = new ArrayList<>();
            for (int j = 0; j < videos.size(); j++) {
                Video video = videos.get(j);
                if(chapter.getId().equals(video.getChapterId())){
                    VideoVo videoVo = new VideoVo();
                    BeanUtils.copyProperties(video,videoVo);
                    videoVoArrayList.add(videoVo);
                }
            }
            chapterVo.setChildren(videoVoArrayList);
        }
        return chapterVos;
    }

    @Override
    public boolean removeChapterById(String id) {
        //根据id查询该chapter下是否有video
        if(videoService.getCountByChapterId(id)){
            throw new MyException(20001,"该章节下存在视频，请先删除视频");
        }
        Integer count = baseMapper.deleteById(id);
        return count!=null&&count>0;
    }

}
