 
package com.wangxinenpu.springbootdemo.service;

import com.github.pagehelper.PageInfo;
import com.wangxinenpu.springbootdemo.dataobject.Question;
import com.wangxinenpu.springbootdemo.dataobject.vo.Question.QuestionDeleteVO;
import com.wangxinenpu.springbootdemo.dataobject.vo.Question.QuestionDetailVO;
import com.wangxinenpu.springbootdemo.dataobject.vo.Question.QuestionListVO;
import com.wangxinenpu.springbootdemo.dataobject.vo.Question.QuestionSaveVO;


public interface QuestionFacade{

	PageInfo<Question> getQuestionList(QuestionListVO listVO);

    Question getQuestionDetail(QuestionDetailVO detailVO);

    Integer saveQuestion(QuestionSaveVO saveVO);

    Integer deleteQuestion(QuestionDeleteVO deleteVO);

	 

}

 
