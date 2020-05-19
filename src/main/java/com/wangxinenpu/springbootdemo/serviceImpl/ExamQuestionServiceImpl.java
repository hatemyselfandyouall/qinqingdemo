package com.wangxinenpu.springbootdemo.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wangxinenpu.springbootdemo.dao.ExamQuestionMapper;
import com.wangxinenpu.springbootdemo.service.ExamQuestionFacade;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;
import org.springframework.beans.BeanUtils;

import java.util.Date;
import java.util.List;
import com.wangxinenpu.springbootdemo.dataobject.ExamQuestion;
import com.wangxinenpu.springbootdemo.dataobject.vo.ExamQuestion.ExamQuestionDeleteVO;
import com.wangxinenpu.springbootdemo.dataobject.vo.ExamQuestion.ExamQuestionDetailVO;
import com.wangxinenpu.springbootdemo.dataobject.vo.ExamQuestion.ExamQuestionListVO;
import com.wangxinenpu.springbootdemo.dataobject.vo.ExamQuestion.ExamQuestionSaveVO;

public class ExamQuestionServiceImpl implements ExamQuestionFacade {

    @Autowired
    ExamQuestionMapper examQuestionMapper;

    @Override
    public PageInfo<ExamQuestion> getExamQuestionList(ExamQuestionListVO examQuestionListVO) {
        if (examQuestionListVO==null||examQuestionListVO.getPageNum()==null||examQuestionListVO.getPageSize()==null) {
            return null;
        }
        PageHelper.startPage(examQuestionListVO.getPageNum().intValue(),examQuestionListVO.getPageSize().intValue());
        ExamQuestion exampleObeject=new ExamQuestion();
        List<ExamQuestion> examQuestionList=examQuestionMapper.select(exampleObeject);
        PageInfo<ExamQuestion> examQuestionPageInfo=new PageInfo<>(examQuestionList);
        return examQuestionPageInfo;
    }

    @Override
    public ExamQuestion getExamQuestionDetail(ExamQuestionDetailVO examQuestionDetailVO) {
        if (examQuestionDetailVO==null||examQuestionDetailVO.getId()==null) {
            return null;
        };
        ExamQuestion examQuestion=examQuestionMapper.selectByPrimaryKey(examQuestionDetailVO.getId());
        return examQuestion;
    }

    @Override
    public Integer saveExamQuestion(ExamQuestionSaveVO examQuestionSaveVO) {
        if (examQuestionSaveVO==null){
            return 0;
        }
        ExamQuestion examQuestion= new ExamQuestion();
        BeanUtils.copyProperties(examQuestionSaveVO,examQuestion);
        if (examQuestion.getId()==null){
            return examQuestionMapper.insertSelective(examQuestion);
        }else {
            Example example=new Example(ExamQuestion.class);
            example.createCriteria().andEqualTo("id",examQuestion.getId());
            return examQuestionMapper.updateByExampleSelective(examQuestion,example);
        }
    }

    @Override
    public Integer deleteExamQuestion(ExamQuestionDeleteVO examQuestionDeleteVO) {
        if (examQuestionDeleteVO==null||examQuestionDeleteVO.getId()==null){
            return 0;
        }
        ExamQuestion examQuestion=new ExamQuestion();

        Example example=new Example(ExamQuestion.class);
        example.createCriteria().andEqualTo("id",examQuestionDeleteVO.getId());
        return examQuestionMapper.updateByExampleSelective(examQuestion,example);
    }
}
