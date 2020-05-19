package com.wangxinenpu.springbootdemo.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wangxinenpu.springbootdemo.dao.AnwswerMapper;
import com.wangxinenpu.springbootdemo.service.AnwswerFacade;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;
import com.wangxinenpu.springbootdemo.dataobject.Anwswer;
import com.wangxinenpu.springbootdemo.dataobject.vo.Anwswer.AnwswerDeleteVO;
import com.wangxinenpu.springbootdemo.dataobject.vo.Anwswer.AnwswerDetailVO;
import com.wangxinenpu.springbootdemo.dataobject.vo.Anwswer.AnwswerListVO;
import com.wangxinenpu.springbootdemo.dataobject.vo.Anwswer.AnwswerSaveVO;

public class AnwswerServiceImpl implements AnwswerFacade {

    @Autowired
    AnwswerMapper anwswerMapper;

    @Override
    public PageInfo<Anwswer> getAnwswerList(AnwswerListVO anwswerListVO) {
        if (anwswerListVO==null||anwswerListVO.getPageNum()==null||anwswerListVO.getPageSize()==null) {
            return null;
        }
        PageHelper.startPage(anwswerListVO.getPageNum().intValue(),anwswerListVO.getPageSize().intValue());
        Anwswer exampleObeject=new Anwswer();
        List<Anwswer> anwswerList=anwswerMapper.select(exampleObeject);
        PageInfo<Anwswer> anwswerPageInfo=new PageInfo<>(anwswerList);
        return anwswerPageInfo;
    }

    @Override
    public Anwswer getAnwswerDetail(AnwswerDetailVO anwswerDetailVO) {
        if (anwswerDetailVO==null||anwswerDetailVO.getId()==null) {
            return null;
        };
        Anwswer anwswer=anwswerMapper.selectByPrimaryKey(anwswerDetailVO.getId());
        return anwswer;
    }

    @Override
    public Integer saveAnwswer(AnwswerSaveVO anwswerSaveVO) {
        if (anwswerSaveVO==null){
            return 0;
        }
        Anwswer anwswer= new Anwswer();
        BeanUtils.copyProperties(anwswerSaveVO,anwswer);
        if (anwswer.getId()==null){
            return anwswerMapper.insertSelective(anwswer);
        }else {
            Example example=new Example(Anwswer.class);
            example.createCriteria().andEqualTo("id",anwswer.getId());
            return anwswerMapper.updateByExampleSelective(anwswer,example);
        }
    }

    @Override
    public Integer deleteAnwswer(AnwswerDeleteVO anwswerDeleteVO) {
        if (anwswerDeleteVO==null||anwswerDeleteVO.getId()==null){
            return 0;
        }
        Anwswer anwswer=new Anwswer();
        Example example=new Example(Anwswer.class);
        example.createCriteria().andEqualTo("id",anwswerDeleteVO.getId());
        return anwswerMapper.updateByExampleSelective(anwswer,example);
    }
}
