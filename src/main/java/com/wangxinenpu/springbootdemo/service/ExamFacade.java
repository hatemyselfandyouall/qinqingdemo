 
package com.wangxinenpu.springbootdemo.service;

import com.github.pagehelper.PageInfo;
import com.wangxinenpu.springbootdemo.dataobject.Exam;
import com.wangxinenpu.springbootdemo.dataobject.vo.Exam.ExamDeleteVO;
import com.wangxinenpu.springbootdemo.dataobject.vo.Exam.ExamDetailVO;
import com.wangxinenpu.springbootdemo.dataobject.vo.Exam.ExamListVO;
import com.wangxinenpu.springbootdemo.dataobject.vo.Exam.ExamSaveVO;


public interface ExamFacade{

	PageInfo<Exam> getExamList(ExamListVO listVO);

    Exam getExamDetail(ExamDetailVO detailVO);

    Integer saveExam(ExamSaveVO saveVO);

    Integer deleteExam(ExamDeleteVO deleteVO);

	 

}

 
