package com.atguigu.edu.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.atguigu.edu.dto.ExcelSubjectData;
import com.atguigu.edu.entity.Subject;
import com.atguigu.edu.service.SubjectService;
import com.atguigu.servicebase.handle.MyException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import java.util.Map;

public class SubjectExcelListener extends AnalysisEventListener<ExcelSubjectData> {
    private  SubjectService subjectService;
    public SubjectExcelListener(){}
    public SubjectExcelListener(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @Override
    public void invoke(ExcelSubjectData excelSubjectData, AnalysisContext analysisContext) {
        if(excelSubjectData==null){
            throw new MyException(20001,"添加失败");
        }
        //添加一级分类
        Subject existOneSubject = this.existOneSubject(subjectService,excelSubjectData.getOneSubjectName());
        if(existOneSubject==null){
            existOneSubject = new Subject();
            existOneSubject.setTitle(excelSubjectData.getOneSubjectName());
            existOneSubject.setParentId("0");
            subjectService.save(existOneSubject);
        }

        //获取一级分类id
        String pid = existOneSubject.getId();

        //添加二级分类
        Subject exitTwoSubject = this.existTwoSubject(subjectService,excelSubjectData.getTwoSubjectName(),pid);
        if(exitTwoSubject==null){
            exitTwoSubject.setTitle(excelSubjectData.getTwoSubjectName());
            exitTwoSubject.setParentId(pid);
            subjectService.save(exitTwoSubject);
        }
    }
    //获取表头信息

    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println("表头信息："+headMap);
    }

    //判断二级分类是否重复
    private Subject existTwoSubject(SubjectService subjectService, String twoSubjectName, String pid) {
        QueryWrapper<Subject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("title",twoSubjectName);
        queryWrapper.eq("parent_id",pid);
        Subject subject = subjectService.getOne(queryWrapper);
        return subject;
    }

    //判断一级分类是否重复
    private Subject existOneSubject(SubjectService subjectService, String oneSubjectName) {
        QueryWrapper<Subject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("title",oneSubjectName);
        queryWrapper.eq("parent_id",0);
        Subject subject = subjectService.getOne(queryWrapper);
        return subject;
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
