package com.wangxinenpu.springbootdemo.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wangxinenpu.springbootdemo.dao.ChoiceMapper;
import com.wangxinenpu.springbootdemo.service.ChoiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;
import org.springframework.beans.BeanUtils;

import java.util.Date;
import java.util.List;
import com.wangxinenpu.springbootdemo.dataobject.Choice;
import com.wangxinenpu.springbootdemo.dataobject.vo.Choice.ChoiceDeleteVO;
import com.wangxinenpu.springbootdemo.dataobject.vo.Choice.ChoiceDetailVO;
import com.wangxinenpu.springbootdemo.dataobject.vo.Choice.ChoiceListVO;
import com.wangxinenpu.springbootdemo.dataobject.vo.Choice.ChoiceSaveVO;

public class ChoiceServiceImpl implements ChoiceFacade {

    @Autowired
    ChoiceMapper choiceMapper;

    @Override
    public PageInfo<Choice> getChoiceList(ChoiceListVO choiceListVO) {
        if (choiceListVO==null||choiceListVO.getPageNum()==null||choiceListVO.getPageSize()==null) {
            return null;
        }
        PageHelper.startPage(choiceListVO.getPageNum().intValue(),choiceListVO.getPageSize().intValue());
        Choice exampleObeject=new Choice();
        List<Choice> choiceList=choiceMapper.select(exampleObeject);
        PageInfo<Choice> choicePageInfo=new PageInfo<>(choiceList);
        return choicePageInfo;
    }

    @Override
    public Choice getChoiceDetail(ChoiceDetailVO choiceDetailVO) {
        if (choiceDetailVO==null||choiceDetailVO.getId()==null) {
            return null;
        };
        Choice choice=choiceMapper.selectByPrimaryKey(choiceDetailVO.getId());
        return choice;
    }

    @Override
    public Integer saveChoice(ChoiceSaveVO choiceSaveVO) {
        if (choiceSaveVO==null){
            return 0;
        }
        Choice choice= new Choice();
        BeanUtils.copyProperties(choiceSaveVO,choice);
        if (choice.getId()==null){
            return choiceMapper.insertSelective(choice);
        }else {
            Example example=new Example(Choice.class);
            example.createCriteria().andEqualTo("id",choice.getId());
            return choiceMapper.updateByExampleSelective(choice,example);
        }
    }

    @Override
    public Integer deleteChoice(ChoiceDeleteVO choiceDeleteVO) {
        if (choiceDeleteVO==null||choiceDeleteVO.getId()==null){
            return 0;
        }
        Choice choice=new Choice();
        Example example=new Example(Choice.class);
        example.createCriteria().andEqualTo("id",choiceDeleteVO.getId());
        return choiceMapper.updateByExampleSelective(choice,example);
    }
}
