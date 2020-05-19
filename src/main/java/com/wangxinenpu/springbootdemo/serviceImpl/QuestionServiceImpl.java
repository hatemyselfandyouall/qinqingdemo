package com.wangxinenpu.springbootdemo.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wangxinenpu.springbootdemo.dao.QuestionMapper;
import com.wangxinenpu.springbootdemo.service.QuestionFacade;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;
import org.springframework.beans.BeanUtils;

import java.util.Date;
import java.util.List;
import com.wangxinenpu.springbootdemo.dataobject.Question;
import com.wangxinenpu.springbootdemo.dataobject.vo.Question.QuestionDeleteVO;
import com.wangxinenpu.springbootdemo.dataobject.vo.Question.QuestionDetailVO;
import com.wangxinenpu.springbootdemo.dataobject.vo.Question.QuestionListVO;
import com.wangxinenpu.springbootdemo.dataobject.vo.Question.QuestionSaveVO;

public class QuestionServiceImpl implements QuestionFacade {

    @Autowired
    QuestionMapper questionMapper;

    @Override
    public PageInfo<Question> getQuestionList(QuestionListVO questionListVO) {
        if (questionListVO==null||questionListVO.getPageNum()==null||questionListVO.getPageSize()==null) {
            return null;
        }
        PageHelper.startPage(questionListVO.getPageNum().intValue(),questionListVO.getPageSize().intValue());
        Question exampleObeject=new Question();
        List<Question> questionList=questionMapper.select(exampleObeject);
        PageInfo<Question> questionPageInfo=new PageInfo<>(questionList);
        return questionPageInfo;
    }

    @Override
    public Question getQuestionDetail(QuestionDetailVO questionDetailVO) {
        if (questionDetailVO==null||questionDetailVO.getId()==null) {
            return null;
        };
        Question question=questionMapper.selectByPrimaryKey(questionDetailVO.getId());
        return question;
    }

    @Override
    public Integer saveQuestion(QuestionSaveVO questionSaveVO) {
        if (questionSaveVO==null){
            return 0;
        }
        Question question= new Question();
        BeanUtils.copyProperties(questionSaveVO,question);
        if (question.getId()==null){
            return questionMapper.insertSelective(question);
        }else {
            question.setModifyTime(new Date());
            Example example=new Example(Question.class);
            example.createCriteria().andEqualTo("id",question.getId());
            return questionMapper.updateByExampleSelective(question,example);
        }
    }

    @Override
    public Integer deleteQuestion(QuestionDeleteVO questionDeleteVO) {
        if (questionDeleteVO==null||questionDeleteVO.getId()==null){
            return 0;
        }
        Question question=new Question();
        question.setModifyTime(new Date());
        Example example=new Example(Question.class);
        example.createCriteria().andEqualTo("id",questionDeleteVO.getId());
        return questionMapper.updateByExampleSelective(question,example);
    }
}
