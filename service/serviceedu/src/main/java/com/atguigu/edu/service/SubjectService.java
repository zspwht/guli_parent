package com.atguigu.edu.service;

import com.atguigu.edu.entity.Subject;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author atguigu
 * @since 2021-01-08
 */
public interface SubjectService extends IService<Subject> {

    void importSubjectData(MultipartFile file, SubjectService subjectService);
}
