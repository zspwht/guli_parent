package com.atguigu.edu.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.read.listener.ReadListener;
import com.atguigu.edu.entity.ExcelSubjectData;
import com.atguigu.edu.service.SubjectService;
import com.atguigu.servicebase.handle.MyException;

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

    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
