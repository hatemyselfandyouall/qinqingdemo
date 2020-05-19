package com.wangxinenpu.springbootdemo.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wangxinenpu.springbootdemo.dao.ExamMapper;
import com.wangxinenpu.springbootdemo.service.ExamFacade;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;
import org.springframework.beans.BeanUtils;

import java.util.Date;
import java.util.List;
import com.wangxinenpu.springbootdemo.dataobject.Exam;
import com.wangxinenpu.springbootdemo.dataobject.vo.Exam.ExamDeleteVO;
import com.wangxinenpu.springbootdemo.dataobject.vo.Exam.ExamDetailVO;
import com.wangxinenpu.springbootdemo.dataobject.vo.Exam.ExamListVO;
import com.wangxinenpu.springbootdemo.dataobject.vo.Exam.ExamSaveVO;

public class ExamServiceImpl implements ExamFacade {

    @Autowired
    ExamMapper examMapper;

    @Override
    public PageInfo<Exam> getExamList(ExamListVO examListVO) {
        if (examListVO==null||examListVO.getPageNum()==null||examListVO.getPageSize()==null) {
            return null;
        }
        PageHelper.startPage(examListVO.getPageNum().intValue(),examListVO.getPageSize().intValue());
        Exam exampleObeject=new Exam();
        List<Exam> examList=examMapper.select(exampleObeject);
        PageInfo<Exam> examPageInfo=new PageInfo<>(examList);
        return examPageInfo;
    }

    @Override
    public Exam getExamDetail(ExamDetailVO examDetailVO) {
        if (examDetailVO==null||examDetailVO.getId()==null) {
            return null;
        };
        Exam exam=examMapper.selectByPrimaryKey(examDetailVO.getId());
        return exam;
    }

    @Override
    public Integer saveExam(ExamSaveVO examSaveVO) {
        if (examSaveVO==null){
            return 0;
        }
        Exam exam= new Exam();
        BeanUtils.copyProperties(examSaveVO,exam);
        if (exam.getId()==null){
            return examMapper.insertSelective(exam);
        }else {
            exam.setModifyTime(new Date());
            Example example=new Example(Exam.class);
            example.createCriteria().andEqualTo("id",exam.getId());
            return examMapper.updateByExampleSelective(exam,example);
        }
    }

    @Override
    public Integer deleteExam(ExamDeleteVO examDeleteVO) {
        if (examDeleteVO==null||examDeleteVO.getId()==null){
            return 0;
        }
        Exam exam=new Exam();
        exam.setModifyTime(new Date());
        Example example=new Example(Exam.class);
        example.createCriteria().andEqualTo("id",examDeleteVO.getId());
        return examMapper.updateByExampleSelective(exam,example);
    }
}
