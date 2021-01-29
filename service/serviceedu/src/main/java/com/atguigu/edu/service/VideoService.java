package com.atguigu.edu.service;

import com.atguigu.edu.dto.VideoInfoFrom;
import com.atguigu.edu.entity.Video;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author atguigu
 * @since 2021-01-27
 */
public interface VideoService extends IService<Video> {

    boolean getCountByChapterId(String id);

    Boolean saveVideo(VideoInfoFrom videoInfoFrom);

    VideoInfoFrom getViedoById(String id);

    Boolean updateVideById(VideoInfoFrom videoInfoFrom);

    Boolean deleteById(String id);
}
