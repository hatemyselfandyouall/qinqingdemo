 
package com.wangxinenpu.springbootdemo.service;

import com.github.pagehelper.PageInfo;
import com.wangxinenpu.springbootdemo.dataobject.ExamQuestion;
import com.wangxinenpu.springbootdemo.dataobject.vo.ExamQuestion.ExamQuestionDeleteVO;
import com.wangxinenpu.springbootdemo.dataobject.vo.ExamQuestion.ExamQuestionDetailVO;
import com.wangxinenpu.springbootdemo.dataobject.vo.ExamQuestion.ExamQuestionListVO;
import com.wangxinenpu.springbootdemo.dataobject.vo.ExamQuestion.ExamQuestionSaveVO;


public interface ExamQuestionFacade{

	PageInfo<ExamQuestion> getExamQuestionList(ExamQuestionListVO listVO);

    ExamQuestion getExamQuestionDetail(ExamQuestionDetailVO detailVO);

    Integer saveExamQuestion(ExamQuestionSaveVO saveVO);

    Integer deleteExamQuestion(ExamQuestionDeleteVO deleteVO);

	 

}

 
