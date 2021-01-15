package com.atguigu.edu.service.impl;

import com.alibaba.excel.EasyExcel;
import com.atguigu.edu.dto.ExcelSubjectData;
import com.atguigu.edu.entity.Subject;
import com.atguigu.edu.listener.SubjectExcelListener;
import com.atguigu.edu.mapper.SubjectMapper;
import com.atguigu.edu.service.SubjectService;
import com.atguigu.edu.vo.SubjectNestedVo;
import com.atguigu.edu.vo.SubjectVo;
import com.atguigu.servicebase.handle.MyException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2021-01-08
 */
@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject> implements SubjectService {

    @Override
    public void importSubjectData(MultipartFile file, SubjectService subjectService) {
        try {
            InputStream inputStream = file.getInputStream();
            EasyExcel.read(inputStream, ExcelSubjectData.class,new SubjectExcelListener(subjectService))
            .sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
            throw new MyException(20002,"添加课程分类失败");
        }
    }

    @Override
    public List<SubjectNestedVo> nestedList() {
        //最终要得到的数据列表
        List<SubjectNestedVo> subjectNestedVoList = new ArrayList<>();
        //获取一级分类数据
        QueryWrapper<Subject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id",0);
        queryWrapper.orderByAsc("sort","id");
        List<Subject> subjects = baseMapper.selectList(queryWrapper);
        //获取二级分类数据
        QueryWrapper<Subject> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.ne("parent_id",0);
        queryWrapper2.orderByAsc("sort","id");
        List<Subject> subSubjects = baseMapper.selectList(queryWrapper2);
        //填充一级分类vo数据
        int count = subjects.size();
        for (int i = 0; i < count; i++) {
            Subject subject = subjects.get(i);
            SubjectNestedVo subjectNestedVo = new SubjectNestedVo();
            BeanUtils.copyProperties(subject,subjectNestedVo);
            subjectNestedVoList.add(subjectNestedVo);

            //填充二级分类vo数据
            ArrayList<SubjectVo> subjectVos = new ArrayList<>();
            int count2 = subSubjects.size();
            for (int j = 0; j < count2; j++) {
                Subject subject1 = subSubjects.get(j);
                if(subject.getId().equals(subject1.getParentId())){
                    //创建二级类别vo对象
                    SubjectVo subjectVo = new SubjectVo();
                    BeanUtils.copyProperties(subject1,subjectVo);
                    subjectVos.add(subjectVo);
                }
            }
            subjectNestedVo.setChildren(subjectVos);
        }
        return subjectNestedVoList;
    }
}
